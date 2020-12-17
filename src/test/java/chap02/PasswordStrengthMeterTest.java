package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String pw, PasswordStrength expStr){
        PasswordStrength result = meter.meter(pw);
        assertEquals(expStr, result);
    }

    //모두 충족하면 강도 strong
    @Test
    void meetAllCriteriaThenString(){
//        meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("ab12!@AB");
//        assertEquals(PasswordStrength.STRONG, result);
//        PasswordStrength result2 = meter.meter("abc1!Add");
//        assertEquals(PasswordStrength.STRONG, result2);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    //password의 길이가 8자 보다 적으면 강도 normal
    @Test
    void meetsOtherCriteriaExceptForLengthThenNormal(){
//        meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("ab12!@A");
//        assertEquals(PasswordStrength.NORMAL, result);
//        PasswordStrength result2 = meter.meter("Ab12!c");
//        assertEquals(PasswordStrength.NORMAL, result2);
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    //password에 숫자가 없으면 강도 normal
    @Test
    void meetsOtherCriteriaExceptForNumberThenNormal(){
//        meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("AB!CdaEasd");
//        assertEquals(PasswordStrength.NORMAL, result);
        assertStrength("AB!CdaEads", PasswordStrength.NORMAL);
    }

    @Test
    void nullInputThenInvalid(){
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInputThenInvalid(){
        assertStrength("", PasswordStrength.INVALID);
    }

    //Password에 대문자가 들어가지 않은 경우
    @Test
    void meetsOthersCriteriaExceptForUppercaseThenNormal(){
        assertStrength("ab12!@asd", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteriaThenWeak(){
        assertStrength("asdfasdfass", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteriaThenWeak(){
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUppercaseCriteriaThenWeak(){
        assertStrength("ASDBCD", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteriaThenWeak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }

    @Test
    void meetsSpaceEmptyCrieriaThenInvalid(){
        assertStrength(" ", PasswordStrength.INVALID);
    }
}
