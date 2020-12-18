package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCaculator {

    public LocalDate calculateExpireDate(LocalDate billingDate, int payment){
//        return LocalDate.of(2019,4,1);
        return billingDate.plusMonths(1);
    }

    public LocalDate calculateExpireDate(PayData payData){
        int addedMonths = payData.getPayment() / 10_000;
        if(payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()){
                if(YearMonth.from(candidateExp).lengthOfMonth() < payData.getFirstBillingDate().getDayOfMonth()){
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }

        }

        return payData.getBillingDate().plusMonths(addedMonths);
    }
}