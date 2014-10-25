package travelbuddy.common;
/**
 *
 * @author Stefan Spasov
 */
public class BankResponse {
    String err;
    String ok;
    
    public BankResponse(String err, String ok) {
        this.err = err;
        this.ok = ok;
    }
}
