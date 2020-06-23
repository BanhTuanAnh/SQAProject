/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBConnection {

    private static Connection conn = null;
    public static String hostName = "localhost";
    public static String sqlInstanceName = "BTANH2020";
    public static String database = "PTIT_SQA_EMS";
    public static String userName = "";
    public static String password = "";

    // Kết nối vào SQLServer.
    // (Sử dụng thư viện điều khiển SQLJDBC)
    public static Connection getSQLServerConnection() {
        try {
            if (conn == null) {
                return getSQLServerConnection(hostName, sqlInstanceName,
                        database);
            } else {
                return conn;
            }
        } catch (Exception e) {
        }
        return null;
    }

    // Trường hợp sử dụng SQLServer.
    // Và thư viện SQLJDBC.
    public static Connection getSQLServerConnection(String hostName,
            String sqlInstanceName, String database) throws ClassNotFoundException, SQLException {
        if (conn == null) {
            // Khai báo class Driver cho DB SQLServer
            // Việc này cần thiết với Java 5
            // Java6 tự động tìm kiếm Driver thích hợp.
            // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Cấu trúc URL Connection dành cho SQLServer
            // Ví dụ:
            // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
            String connectionURL
                    = "jdbc:sqlserver://" + hostName + ":3495"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + database + ";integratedSecurity=true";

            conn = DriverManager.getConnection(connectionURL);
        }

        return conn;
    }

    public static Connection getSQLServerConnection(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {
        if (conn == null) {
            // Khai báo class Driver cho DB SQLServer
            // Việc này cần thiết với Java 5
            // Java6 tự động tìm kiếm Driver thích hợp.
            // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Cấu trúc URL Connection dành cho SQLServer
            // Ví dụ:
            // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
            String connectionURL = "jdbc:sqlserver://" + hostName + ":3495"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + database;
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(DBConnection.getSQLServerConnection());
    }

}
