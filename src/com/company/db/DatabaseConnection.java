package src.com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {


    private static Connection openConnection =null;


    public static Connection getConnection() {
        try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/szkola_programowania", "root", "coderslab")){
            return newConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getEfficientConnection() throws SQLException {

        if (openConnection == null || openConnection.isClosed()) {

            openConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/szkola_programowania", "root", "coderslab");

        }
        return openConnection;

    }
}