package ie.mtu.mtu_2025_demintia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// manages the database connection
// only one instance of connection is used in the medicalUsers
public class DatabaseConnection {
    // singleton instance
    private static DatabaseConnection instance;

    // jdbc connection object
    private Connection connection;

    // databse
    private static final String URL = "jdbc:mysql://localhost:3306/MedicalLogin"; // final as it doesnt change
    private static final String USER = "root";
    private static final String PASSWORD = "Mousey251130";

    // -----------------------------------------------------------------------------------
    // private constructor (prevents direct instantation)
    // starts connection to database
    private DatabaseConnection() {
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e){
            System.out.println("Database connection failed");
        }
    }

    // -----------------------------------------------------------------------------------
    // provides acess to singleton instance
    // if none exists or there is no connection a new one is created
    public static DatabaseConnection getInstance(){
        if (instance == null || instance.getConnection() == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // -----------------------------------------------------------------------------------
    // gets the current database connection
    // if connection is closed, a new one is made
    public Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        }
        catch (SQLException e){
            System.out.println("Error reestablishing DB connection");
        }
        return connection;
    }

}
