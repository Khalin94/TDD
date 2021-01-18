package chap07;

public class RegisterResult {

    private boolean success;
    private CardValidity validity;
    public RegisterResult(boolean success, CardValidity validity){
        this.success = success;
        this.validity = validity;
    }

    public static RegisterResult error(CardValidity validity){
        return new RegisterResult(false, validity);
    }

    public static RegisterResult success(){
        return new RegisterResult(true, CardValidity.VALID);
    }

    public CardValidity getValid() {
        return this.validity;
    }
}
