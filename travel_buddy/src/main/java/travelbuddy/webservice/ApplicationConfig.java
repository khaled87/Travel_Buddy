
package travelbuddy.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Technical class needed by JAX-RS
 * @author hajo
 */
@javax.ws.rs.ApplicationPath("api/v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(travelbuddy.webservice.AuthFilter.class);
        resources.add(travelbuddy.webservice.AuthService.class);
        resources.add(travelbuddy.webservice.ProductCatalogueResource.class);
        resources.add(travelbuddy.webservice.TravelResource.class);
    }
    
}
