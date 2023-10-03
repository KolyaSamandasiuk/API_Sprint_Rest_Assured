package api.POJO;

public class UpdateUserData {
    private String name;
    private String job;

    public UpdateUserData() {
    }

    public UpdateUserData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
