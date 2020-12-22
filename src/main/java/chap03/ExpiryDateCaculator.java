package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCaculator {

    public LocalDate calculateExpireDate(PayData payData){
        int addedMonths = monthCalculator(payData.getPayment());

        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }else{
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths){
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

          if(isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)){
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

            if(dayLenOfCandiMon < dayOfFirstBilling){
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidate){
        return firstBillingDate.getDayOfMonth() != candidate.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate cadidateLastDayOfMonth){
        return YearMonth.from(cadidateLastDayOfMonth).lengthOfMonth();
    }

    private int monthCalculator(int payment){
        int result = 0;

        while(payment >= 100_000){
            result += 12;
            payment -= 100_000;
        }
        result += payment / 10_000;

        return result;
    }
}