package main.java.Data;
import java.sql.*;

public class DbConnector {

    private Connection conn=null;

    private DbConnector() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DbConnector getInstancia() {
        if (instancia == null) {
            instancia = new DbConnector();
        }
        return instancia;
    }

    public Connection getConn() {
        try {
            if(conn==null || conn.isClosed()) {
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
