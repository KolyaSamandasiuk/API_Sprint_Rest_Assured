package api.POJO;

public class UserForRegistrationData {
    private String email;
    private String password;

    public UserForRegistrationData() {
    }

    public UserForRegistrationData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
