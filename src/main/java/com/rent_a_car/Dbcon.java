package com.rent_a_car;
import com.rent_a_car.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class Dbcon {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/rentacar?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root1234";
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";
    private static final String SELECTQUERY = "SELECT customerid,customerName,customerLastname,customerPhone,customerMail,customerBalance,customerTc FROM customerTable";
    private static final String SELECTCARQ = "SELECT carid, carBrand, carModel, carGear, carPrice, carPlate, carFuelType FROM cartable";
    private ObservableList<Customer> customerList= FXCollections.observableArrayList(); // Müşteri listesi
    private ObservableList<Car> carList= FXCollections.observableArrayList(); // Müşteri listesi


    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public List<Customer> getDbCustomer(){
        // Open a connection
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECTQUERY);) {
            while(rs.next()){


                customerList.add(new Customer(rs.getInt("customerid"),rs.getString("customerName"),rs.getString("customerLastname"),rs.getString("customerPhone"),rs.getString("customerMail"),rs.getInt("customerBalance"),rs.getString("customerTc") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public List<Car> getDbCar(){
        // Open a connection
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECTCARQ);) {
            while(rs.next()){

                carList.add(new Car(rs.getInt("carid"),rs.getString("carBrand"),rs.getString("carModel"),rs.getString("carGear"),rs.getInt("carPrice"),rs.getString("carPlate"),rs.getString("carFuelType")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

}
