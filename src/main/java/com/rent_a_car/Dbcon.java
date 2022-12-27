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
    private static final String INSERTCARQ="INSERT INTO cartable (carBrand, carModel, carGear,carPrice,carPlate,carFuelType) VALUES (?, ? ,? ,? , ? , ?)";
    private static final String UPDATECARQ="UPDATE cartable SET carBrand=?, carModel=?, carGear=?,carPrice=?,carPlate=?,carFuelType=? WHERE carid=? ";
    private static final String DELETECARQ="DELETE FROM cartable WHERE carid=? ";
    private static final String SELECTCARQ = "SELECT carid, carBrand, carModel, carGear, carPrice, carPlate, carFuelType FROM cartable";
    private static final String SELECTQUERY = "SELECT customerid,customerName,customerLastname,customerPhone,customerMail,customerBalance,customerTc FROM customerTable";
     private static final String INSERTCUSTOMERQ="INSERT INTO customertable (customerName, customerLastname, customerPhone,customerMail,customerBalance,customerTc) VALUES (?, ? ,? ,? , ? , ?)";
    private static final String UPDATECUSTOMERQ = "UPDATE customertable SET customerName=?,customerLastname=?,customerPhone=?,customerMail=?,customerBalance=?,customerTc=? WHERE customerid=? ";
    private static final String DELETECUSTOMERQ="DELETE FROM customertable WHERE customerid=? ";
    private static final String SELECTORDERQ = "SELECT  orderid,orderCustomerid,orderCarrid,orderRentalStart,orderRentalEnd,orderTotalPrice FROM ordertable";
    private static final String INSERTORDERQ="INSERT INTO ordertable (orderCustomerid,orderCarrid,orderRentalStart,orderRentalEnd,orderTotalPrice) VALUES (?, ? ,? ,? , ? )";
    private static final String UPDATEORDERQ = "UPDATE ordertable SET orderCustomerid=?,orderCarrid=?,orderRentalStart=?,orderRentalEnd=?,orderTotalPrice=? WHERE orderid=? ";
    private static final String DELETEORDERQ="DELETE FROM ordertable WHERE orderid=? ";

    private ObservableList<Customer> customerList= FXCollections.observableArrayList(); // Müşteri listesi
    private ObservableList<Car> carList= FXCollections.observableArrayList(); // Müşteri listesi
    private ObservableList<Order> orderList= FXCollections.observableArrayList(); // Müşteri listesi

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
    public void instertCarDb(String carBrand, String carModel, String carGear, int carPrice, String carPlate, String carFuelType) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERTCARQ)) {
            preparedStatement.setString(1, carBrand);
            preparedStatement.setString(2, carModel);
            preparedStatement.setString(3, carGear);
            preparedStatement.setInt(4, carPrice);
            preparedStatement.setString(5, carPlate);
            preparedStatement.setString(6, carFuelType);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void updataCarDb(String carBrand, String carModel, String carGear, int carPrice, String carPlate, String carFuelType,int carid) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATECARQ)) {
            preparedStatement.setString(1, carBrand);
            preparedStatement.setString(2, carModel);
            preparedStatement.setString(3, carGear);
            preparedStatement.setInt(4, carPrice);
            preparedStatement.setString(5, carPlate);
            preparedStatement.setString(6, carFuelType);
            preparedStatement.setInt(7, carid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void deleteCarDb(int carid) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETECARQ)) {
            preparedStatement.setInt(1, carid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public List<Car> getDbCar(){
        // Open a connection
        carList.clear();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECTCARQ);) {
            while(rs.next()){
                carList.add(new Car(rs.getInt("carid"),rs.getString("carBrand"),rs.getString("carModel"),rs.getString("carGear"),rs.getInt("carPrice"),rs.getString("carPlate"),rs.getString("carFuelType") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }
    public List<Customer> getDbCustomer(){
        // Open a connection
        customerList.clear();;
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECTQUERY);) {
            while(rs.next()){
                customerList.add(new Customer(rs.getInt("customerid"),rs.getString("customerName"),rs.getString("customerLastname"),rs.getString("customerPhone"),rs.getString("customerMail"),rs.getInt("customerBalance"),rs.getString("customerTc") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void insertCustomerDb(String customerName, String customerLastname, String customerPhone, String customerMail, int customerBalance, String customerTc)throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERTCUSTOMERQ)) {
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerLastname);
            preparedStatement.setString(3, customerPhone );
            preparedStatement.setString(4, customerMail);
            preparedStatement.setInt(5, customerBalance);
            preparedStatement.setString(6, customerTc);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void UpdateCustomerDb(String customerName, String customerLastname, String customerPhone, String customerMail, int customerBalance, String customerTc,int customerid) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATECUSTOMERQ)) {
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerLastname);
            preparedStatement.setString(3, customerPhone );
            preparedStatement.setString(4, customerMail);
            preparedStatement.setInt(5, customerBalance);
            preparedStatement.setString(6, customerTc);
            preparedStatement.setInt(7, customerid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void deleteCustomerDb(int customerid) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETECUSTOMERQ)) {
            preparedStatement.setInt(1, customerid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    //    private static final String SELECTORDERQ = "SELECT  orderid,orderCustomerid,orderCarrid,orderRentalStart,orderRentalEnd,orderTotalPrice FROM ordertable";
    public List<Order> getDbOrder(){
        // Open a connection
        orderList.clear();;
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECTORDERQ);) {
            while(rs.next()){
                orderList.add(new Order(rs.getInt("orderid"),rs.getInt("orderCustomerid"),rs.getInt("orderCarrid"),rs.getDate("orderRentalStart").toLocalDate(),rs.getDate("orderRentalEnd").toLocalDate(),rs.getInt("orderTotalPrice") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public void insertOrderDb(String orderCustomerid, String orderCarrid, Date orderRentalStart, Date orderRentalEnd, int orderTotalPrice) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTORDERQ)) {
            preparedStatement.setString(1, orderCustomerid);
            preparedStatement.setString(2, orderCarrid);
            preparedStatement.setDate(3, orderRentalStart );
            preparedStatement.setDate(4, orderRentalEnd);
            preparedStatement.setInt(5, orderTotalPrice);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void updateOrderDb(int orderCustomerid, int orderCarrid, Date orderRentalStart, Date orderRentalEnd, int orderTotalPrice,int orderid) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATEORDERQ)) {
            preparedStatement.setInt(1, orderCustomerid);
            preparedStatement.setInt(2, orderCarrid);
            preparedStatement.setDate(3, orderRentalStart );
            preparedStatement.setDate(4, orderRentalEnd);
            preparedStatement.setInt(5, orderTotalPrice);
            preparedStatement.setInt(6, orderid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void deleteOrderDb(int orderid) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETEORDERQ)) {
            preparedStatement.setInt(1, orderid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

}
