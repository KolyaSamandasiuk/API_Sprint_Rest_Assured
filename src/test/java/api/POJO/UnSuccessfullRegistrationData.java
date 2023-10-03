package api.POJO;

public class UnSuccessfullRegistrationData {
    private String error;

    public UnSuccessfullRegistrationData(String error) {
        this.error = error;
    }

    public UnSuccessfullRegistrationData() {
    }

    public String getError() {
        return error;
    }
}
