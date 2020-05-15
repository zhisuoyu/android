package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import utils.Lg;

public class MysqlHelper {

    private static final MysqlHelper Instance = new MysqlHelper();

    private MysqlHelper() {
    }

    public static MysqlHelper getInstance() {
        return Instance;
    }

    public static void main(String[] args) {
        MysqlHelper op = new MysqlHelper();
        long startMs = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            op.queryUserInfo();
        }
        long endMs = System.currentTimeMillis();
        System.out.println("disMs:" + (endMs - startMs));
    }


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/android";
    static final String USER = "root";
    static final String PASS = "123456";

    private Connection conn;
    private Statement stmt;

    public void prepare() throws ClassNotFoundException, SQLException {
        if (conn == null || conn.isClosed() || stmt == null || stmt.isClosed()) {
            close();
            connect();
        }
    }


    private void connect() throws ClassNotFoundException, SQLException {
    	Lg.i("mysql connect");
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }

    public void close() {
        Lg.i("mysql close");
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stmt = null;
        conn = null;
    }



    public boolean execute(String sql) throws SQLException, ClassNotFoundException {
        prepare();
        return stmt.execute(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
        prepare();
        return stmt.executeQuery(sql);
    }


    public int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        prepare();
        return stmt.executeUpdate(sql);
    }

    private String queryUserInfo() {
        String userInfo = "";
        try {
            prepare();
            String sql = "SELECT id, name,pwd FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("pwd");
                userInfo += name + "," + url + ";";
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public String query() {
        String info = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT id, name,pwd FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("pwd");
                info += name + "," + url + ";";
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return info;
    }


    static void test() {

        final MysqlHelper op = new MysqlHelper();
        final Random random = new Random();
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        int sec = random.nextInt(5) + 2;
                        System.out.println("sleep:" + sec);
                        TimeUnit.SECONDS.sleep(sec);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("userInfos:" + op.query());
                }
            }
        }.start();
    }
}
