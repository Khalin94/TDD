package chap07;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private MemoryUserRepository fakeRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, MemoryUserRepository fakeRepository, EmailNotifier emailNotifier){
        this.passwordChecker = passwordChecker;
        this.fakeRepository = fakeRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String password, String email){

        if(passwordChecker.checkPasswordWeak(password)){
            throw new WeakPasswordException();
        }

        /* 상수 */
        //throw new WeakPasswordException();
        User user = fakeRepository.findById(id);

        if(user != null){
            throw new DupIdException();
        }

        fakeRepository.save(new User(id, password, email));
        /* 상수 */
        //fakeRepository.save(new User("id", "pw", "Email"));

        emailNotifier.sendRegisterEmail(email);

    }
}
