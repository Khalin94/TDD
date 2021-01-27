package chap07;

public class User {
    private String id;
    private String password;
    private String email;

    public User(String id, String password, String email){
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }
}
