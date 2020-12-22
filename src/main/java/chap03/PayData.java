package chap03;

import java.time.LocalDate;

public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payment;

    private PayData(){}

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payment){
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payment = payment;
    }

    public LocalDate getFirstBillingDate(){
        return firstBillingDate;
    }

    public LocalDate getBillingDate(){
        return billingDate;
    }

    public int getPayment(){
        return payment;
    }

    //builder pattern
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private PayData data = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate){
            data.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate){
            data.billingDate = billingDate;
            return this;
        }

        public Builder payment(int payment){
            data.payment = payment;
            return this;
        }

        public PayData build(){
            return data;
        }
    }
}
