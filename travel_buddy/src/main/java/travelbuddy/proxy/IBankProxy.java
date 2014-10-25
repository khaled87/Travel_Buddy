package travelbuddy.proxy;
import javax.ejb.Local;
import travelbuddy.common.BankResponse;
import travelbuddy.common.PaymentInfo;
/**
 *
 * @author Stefan Spasov
 */
@Local
public interface IBankProxy {
    BankResponse verify(PaymentInfo pi);
}
