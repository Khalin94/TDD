package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCaculatorTest {

    @Test
    void paidManwonExpireAMonthLater(){
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payment = 10_000;

//        ExpiryDateCaculator cal = new ExpiryDateCaculator();
//        LocalDate expireDate = cal.calculateExpireDate(billingDate, payment);
//        assertEquals(LocalDate.of(2019,4,1), expireDate);

        // PayData로 대체
        //assertExpiryDate(billingDate, payment, LocalDate.of(2019, 4, 1));
        //PayData 클래스를 만들어준 후 테스트
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1)).payment(10_000).build(), LocalDate.of(2019, 4, 1));

//        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
//        int payment2 = 10000;
//        ExpiryDateCaculator cal2 = new ExpiryDateCaculator();
//        LocalDate expireDate2 = cal2.calculateExpireDate(billingDate2, payment2);
//        assertEquals(LocalDate.of(2019, 6, 5), expireDate2);

        // PayData로 대체
        //  assertExpiryDate(LocalDate.of(2019, 5, 5), 10_000, LocalDate.of(2019, 6, 5));

        //PayData 클래스를 만들어준 후 테스트
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 5)).payment(10_000).build(),
                LocalDate.of(2019, 6, 5));

    }

//    private void assertExpiryDate(LocalDate billingDate, int payment, LocalDate expiryDate){
//        ExpiryDateCaculator cal = new ExpiryDateCaculator();
//        LocalDate realExpiryDate = cal.calculateExpireDate(billingDate, payment);
//        assertEquals(expiryDate, realExpiryDate);
//    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCaculator cal = new ExpiryDateCaculator();
        LocalDate realExpiryDate = cal.calculateExpireDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @DisplayName("납부일의 한 달 뒤 날짜가 같지 않음")
    @Test
    void notEqualsExpiryDayAndbillingDay(){
        //assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));
        //assertExpiryDate(LocalDate.of(2019, 5, 31), 10_000, LocalDate.of(2019, 6, 30));
        //assertExpiryDate(LocalDate.of(2020, 1, 31), 10_000, LocalDate.of(2020, 2, 29));

        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 1, 31)).payment(10_000).build(), LocalDate.of(2019, 2, 28));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 31)).payment(10_000).build(), LocalDate.of(2019, 6, 30));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2020, 1, 31)).payment(10_000).build(), LocalDate.of(2020, 2, 29));

    }

    @Test
    void expireManwonWhenFirstBillingDateAndExpiryDate(){
        PayData payData = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 31)).billingDate(LocalDate.of(2019, 2, 28)).payment(10_000).build();
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 30)).billingDate(LocalDate.of(2019, 2, 28)).payment(10_000).build();
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder().firstBillingDate(LocalDate.of(2019, 5, 31)).billingDate(LocalDate.of(2019, 6, 30)).payment(10_000).build();
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @DisplayName("이만원 이상 납부")
    @Test
    void paymentMoreThenYiManwon(){
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1))
                .payment(20_000).build(), LocalDate.of(2019, 5, 1));

        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3,1 )).payment(30_000).build(),
                LocalDate.of(2019, 6, 1));
    }

    @DisplayName("첫 납부일의 일자와 만료일의 일자가 다를 때 이만원 이상 납부")
    @Test
    void paymentMorThenYiManwonWhenDifferentFirstBillingDayAndExpiryDay(){
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payment(20_000).build()
                , LocalDate.of(2019, 4, 30));
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31)).billingDate(LocalDate.of(2019, 2, 28))
                .payment(40_000).build()
                , LocalDate.of(2019, 6, 30));

        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 3, 31))
                .billingDate(LocalDate.of(2019, 4, 30))
                .payment(30_000).build()
                , LocalDate.of(2019, 7 ,31));
    }

    @Test
    void paidTenManwonIsAYearService(){
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 1, 28)).payment(100_000).build(), LocalDate.of(2020, 1, 28));
    }

    @DisplayName("윤달")
    @Test
    void leafMonth(){
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2020, 2, 29)).payment(100_000).build(), LocalDate.of(2021, 2, 28));
    }

    @Test
    void moreThen100_000(){
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2020, 1, 15)).payment(130_000).build(), LocalDate.of(2021, 4, 15));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 30)).payment(260_000).build(), LocalDate.of(2021, 11, 30));
    }


}