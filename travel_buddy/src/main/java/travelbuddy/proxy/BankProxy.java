package travelbuddy.proxy;
import javax.ejb.Stateless;
import travelbuddy.common.BankResponse;
import travelbuddy.common.PaymentInfo;

@Stateless
public class BankProxy implements IBankProxy{
    
    @Override
    public BankResponse verify(PaymentInfo pi) {    
        String ok = "";
        String err = "";
        if("111".equals(pi.getAccount()) )
        {
            ok = "okay";
        }
        else if (pi.getPrice() > 6000){
             err = "moneyErr";  
        } 
        else {
            err = "accountErr";
        }
        
        BankResponse br = new BankResponse(err, ok);
        return br;
    }

    public BankProxy() {
    }
}
