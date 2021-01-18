package chap07;

/*
외부 의존을 피하기 위해 스텁을 만들어줌.
실제 카드 번호를 검증하지는 않고 단순한 구현으로 실제 구현을 대체함.
*/
public class StubCardNumberValidator extends CardNumberValidator{
    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo){
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo){
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validate(String cardNumber) {
        if(invalidNo != null && invalidNo.equals(cardNumber)){
            return CardValidity.INVALID;
        }
        if(theftNo != null && theftNo.equals(cardNumber)){
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }

}
