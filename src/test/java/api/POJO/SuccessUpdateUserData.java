package api.POJO;

public class SuccessUpdateUserData extends UpdateUserData {
    private String updatedAt;

    public SuccessUpdateUserData(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public SuccessUpdateUserData(){}

    public String getUpdatedAt() {
        return updatedAt;
    }
}
