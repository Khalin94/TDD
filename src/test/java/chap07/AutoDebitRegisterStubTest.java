package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.INVALID;
import static chap07.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterStubTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
//    private StubAutoDebitInfoRepository stubRepository;
    private AutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp(){
        stubValidator = new StubCardNumberValidator();
        stubRepository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard(){
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("uer1", "111122223333");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValid());
    }

    @Test
    void theftCard(){
        stubValidator.setTheftNo("123412341234");

        AutoDebitReq req = new AutoDebitReq("user1", "123412341234");
        RegisterResult result = register.register(req);

        assertEquals(THEFT, result.getValid());
    }
}
