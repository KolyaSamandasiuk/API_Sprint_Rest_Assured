package api.POJO;

public class SuccessfulRegistrationData {
    private Integer id;
    public String token;

    public SuccessfulRegistrationData(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessfulRegistrationData() {
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
