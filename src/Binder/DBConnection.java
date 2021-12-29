package Binder;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String USERNAME = "root";
    private  static final String PASSWORD = "";
    private static  final String CONN = "jdbc:mysql://localhost/";

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(CONN,USERNAME,PASSWORD);
    }
}
