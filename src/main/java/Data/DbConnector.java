package main.java.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbConnector {

    private static DbConnector instance; //Singleton instance

    private static final String RESOURCE = "dbConfig.xml";

    private static String driver;
    private static String user;
    private static String pass;
    private static String url;

    private int connections = 0;
    private Connection conn = null;

    static{
        Properties prop = new Properties();
        try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE)){
            prop.loadFromXML(in);
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.username");
            pass = prop.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DbConnector() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DbConnector getInstance() {
        if (instance == null) {
            instance = new DbConnector();
        }
        return instance;
    }

    public Connection getConn() {
        try {
            if(conn == null || conn.isClosed()) {
                conn=DriverManager.getConnection(url, user, pass);
                connections = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connections++;
        return conn;
    }

    public void releaseConn() {
        connections--;
        try {
            if (connections <= 0) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
