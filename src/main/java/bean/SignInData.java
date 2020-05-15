package bean;

public class SignInData {

    private int id;
    private String name;
    private String picUrl;

    public SignInData() {
    }

    public SignInData(int id, String name, String picUrl) {
        this.id = id;
        this.name = name;
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "SignInData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}