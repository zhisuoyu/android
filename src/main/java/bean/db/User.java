package bean.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import tools.MysqlHelper;

public class User {


    public static void main(String[] args) {
        System.out.println(queryById(3));
        System.out.println(queryById(4));
        System.out.println(queryById(5));
    }

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PWD = "pwd";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_TIMESTAMP = "timestamp";

    private int id;
    private String name;
    private String pwd;
    private int level;
    private Timestamp timestamp;


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

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", level=" + level +
                ", timestamp=" + timestamp +
                '}';
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static User queryById(int id) {
        User user = null;
        try {
            ResultSet rs = MysqlHelper.getInstance().executeQuery("select * from user where id =" + id);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(KEY_ID));
                user.setName(rs.getString(KEY_NAME));
                user.setPwd(rs.getString(KEY_PWD));
                user.setLevel(rs.getInt(KEY_LEVEL));
                user.setTimestamp(rs.getTimestamp(KEY_TIMESTAMP));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
