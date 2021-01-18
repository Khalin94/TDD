package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.THEFT;
import static chap07.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 외부 요인에 의존적인 테스트 코드
public class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    void setUp(){
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard(){
        // 업체에서 받은 테스트용 유효 카드번호
        AutoDebitReq req = new AutoDebitReq("user1", "123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValid());
    }

    @Test
    void theftCard(){
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValid());
    }
}
