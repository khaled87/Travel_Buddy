package travelbuddy.common;
/**
 *
 * @author Stefan Spasov
 */
public class BankResponse {
    String err;
    String ok;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
    
    public BankResponse()
    {
        
    }
    
    public BankResponse(String err, String ok) {
        this.err = err;
        this.ok = ok;
    }
}
