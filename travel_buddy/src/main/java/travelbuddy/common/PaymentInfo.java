package travelbuddy.common;

import java.util.Date;

/**
 *
 * @author Stefan Spasov
 */
public class PaymentInfo {
    private String account;
    private double price;
    private String ccv;
    private String holder;

    public PaymentInfo(String account, double price, String ccv, String holder) {
        this.account = account;
        this.price = price;
        this.ccv = ccv;
        this.holder = holder;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String accountNumber) {
        this.account = accountNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
    
}
