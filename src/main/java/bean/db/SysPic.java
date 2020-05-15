package bean.db;

public class SysPic {

    private int picId;
    private String name;

    public SysPic() {
    }

    public SysPic(int picId, String name) {
        this.picId = picId;
        this.name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysPic{" +
                ", picId=" + picId +
                ", name='" + name + '\'' +
                '}';
    }
}
