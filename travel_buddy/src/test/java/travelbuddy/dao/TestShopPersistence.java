package travelbuddy.dao;

import travelbuddy.entity.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing the persistence layer
 *
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database) GlassFish not needed using embedded
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestShopPersistence {

    @EJB
    private IProductCatalogue productCatalogue;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Add all classes
                .addPackage("travelbuddy.dao")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }

    @Test
    public void testPersistAProduct() throws Exception {
        Product p = new Product("aaa", 999, null, null);
        productCatalogue.create(p);
        List<Product> ps = productCatalogue.findAll();
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }

    @Test
    public void testUpdateAProduct() throws Exception {
        Product p = new Product("aaa", 999, null, null);
        productCatalogue.create(p);
        List<Product> ps = productCatalogue.findAll();
        assertTrue(ps.size() == 1);
        assertTrue(ps.get(0).getName().equals(p.getName()));

        Product p2 = new Product(p.getId(), "bbb", 888, null, null);
        productCatalogue.update(p2);
        ps = productCatalogue.findAll();
        assertTrue(ps.size() == 1);
        assertTrue(ps.get(0).getName().equals(p2.getName()));
        assertTrue(ps.get(0).getPrice() == p2.getPrice());
    }

    @Test
    public void testDeleteAProduct() throws Exception {
        Product p = new Product("aaa", 999, null, null);
        productCatalogue.create(p);
        List<Product> ps = productCatalogue.findAll();
        assertTrue(ps.size() == 1);
        assertTrue(ps.get(0).getName().equals(p.getName()));

        productCatalogue.delete(p.getId());
        ps = productCatalogue.findAll();
        assertTrue(ps.isEmpty());
    }

    @Test
    public void testProductGetByName() throws Exception {
        Product p = new Product("aaa", 999, null, null);
        productCatalogue.create(p);
        List<Product> ps = productCatalogue.getByName("aaa");
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }

    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    @PersistenceContext(unitName = "travelbuddy_test_pu")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {
        utx.begin();
        em.joinTransaction();
        em.createQuery("delete from PurchaseOrder").executeUpdate();
        em.createQuery("delete from Customer").executeUpdate();
        em.createQuery("delete from Product").executeUpdate();
        utx.commit();
    }

}
