package com.rent_a_car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class rentacarController implements Initializable {
    @FXML
    private TextField txtCarId,txtCarPlate,txtCarBrand,txtCarModel,txtCarPrice,txtCustomerId,txtCustomerName,txtCustomerLastname,txtCustomerPhone,txtCustomerMaill,txtCustomerBalance,txtCustomerTc;
    @FXML
    private ComboBox cmbCarFuelType,cmbCarGear;
    @FXML
    private TableView<Car> tableCars;
    @FXML
    private TableColumn<Car, String> colCarId;
    @FXML
    private TableColumn<Car, String> colCarBrand;
    @FXML
    private TableColumn<Car, String> colCarModel;
    @FXML
    private TableView<Customer> tableCustomer;
    @FXML
    private TableColumn<Customer, String> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;
    @FXML
    private ObservableList<Car> carsList= FXCollections.observableArrayList(); // Araba listesi
    @FXML
    private ObservableList<Customer>  customerList= FXCollections.observableArrayList(); // Müşteri listesi
    @FXML
    private ObservableList<Order> orderList= FXCollections.observableArrayList(); // Sipariş listesi
    Dbcon dbcon = new Dbcon();



    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  carBuilder();

       getCarToTable();
         getCustomerToTable();
        //orderBuilder();

       // carsList.get(4).getCarModel()
        tableCars.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> carTableToTextField(newValue));
        tableCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> customerTableToTextField(newValue));

        //  System.out.println("Sipariş"+orderList.get(0).getOrderDetail());

    }

    public void orderBuilder(){ // sipariş nesnesi oluşturup siparişler listesine ekler
        orderList.add(new Order(05,800,
                LocalDate.of(2022,12,10),
                LocalDate.of(2022,12,13),
                customerList.get(0),
                carsList.get(0)
        ));
    }

    // Araba işlemleri başlangıç --------------------------------------------------------------------

    public void  getCarToTable(){ // Arabalar listesindeki arabaları tabloya ekler
        carsList=(ObservableList<Car>)dbcon.getDbCar();
        colCarId.setCellValueFactory(new PropertyValueFactory<Car,String>("carId"));
        colCarBrand.setCellValueFactory(new PropertyValueFactory<Car, String>("carBrand"));
        colCarModel.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
        tableCars.setItems(carsList);
        tableCars.refresh();
    }

    public void  carFormClear(){
        txtCarId.setText("");
        txtCarBrand.setText("");
        txtCarModel.setText("");
        txtCarPrice.setText("");

    }
    public void carAdd() throws SQLException {
        try {
            dbcon.instertCarDb(
                    txtCarBrand.getText(),
                    txtCarModel.getText(),
                    (String) cmbCarGear.getValue(),
                    Integer.parseInt(txtCarPrice.getText()),
                    txtCarPlate.getText(),
                    (String) cmbCarFuelType.getValue()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        getCarToTable();
    }
    public void  carUpdate(){
        try{
            dbcon.updataCarDb(
                    txtCarBrand.getText(),
                    txtCarModel.getText(),
                    (String) cmbCarGear.getValue(),
                    Integer.parseInt(txtCarPrice.getText()),
                    txtCarPlate.getText(),
                    (String) cmbCarFuelType.getValue(),
                    Integer.parseInt(txtCarId.getText())
            );
        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCarToTable();
    }
    public void  carDelete(){
        try{
            dbcon.deleteCarDb(Integer.parseInt(txtCarId.getText()));
        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCarToTable();

    }
    public void carTableToTextField(Car xcar){
        if(xcar!=null){
            txtCarId.setText(Integer.toString(xcar.getCarId()) );
            txtCarBrand.setText(xcar.getCarBrand());
            txtCarModel.setText(xcar.getCarModel());
            txtCarPrice.setText(Integer.toString(xcar.getCarPrice()) );
            txtCarPlate.setText(xcar.getCarPlate());
            cmbCarGear.setValue(xcar.getCarGear());
            cmbCarFuelType.setValue(xcar.getCarFuelType());

        }
    }
    public void customerTableToTextField(Customer xcustomer){
        if(xcustomer!=null){
            txtCustomerId.setText(Integer.toString(xcustomer.getCustomerId()));
            txtCustomerName.setText(xcustomer.getCustomerName());
            txtCustomerLastname.setText(xcustomer.getCustomerLastname());
            txtCustomerPhone.setText(xcustomer.getCustomerPhone());
            txtCustomerMaill.setText(xcustomer.getCustomerMail());
            txtCustomerBalance.setText(Integer.toString(xcustomer.getCustomerBalance()));
            txtCustomerTc.setText(xcustomer.getCustomerTc());

        }
    }
    // Araba işlemleri bitiş  --------------------------------------------------------------------
    // Müşteri işlemleri  --------------------------------------------------------------------

    public void  getCustomerToTable(){ // Müşteriler listesindeki arabaları tabloya ekler
        customerList=(ObservableList<Customer>)dbcon.getDbCustomer();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer,String>("customerTc"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        tableCustomer.setItems(customerList);
        tableCustomer.refresh();
    }

//    public (String customerName, String customerLastname, String customerMail, String customerPhone, int customerBalance, String customerTc,int customerid) throws SQLException {
    public void  customerUpdate(){
        try{
            
            dbcon.UpdateCustomerDb(
                    txtCustomerName.getText(),
                    txtCustomerLastname.getText(),
                    txtCustomerMaill.getText(),
                    txtCustomerPhone.getText(),
                    Integer.parseInt( txtCustomerBalance.getText()),
                    txtCustomerTc.getText(),
                    Integer.parseInt( txtCustomerId.getText())

            );

        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCustomerToTable();
    }
    public void  customerAdd(){
        try{

            dbcon.insertCustomerDb(
                    txtCustomerName.getText(),
                    txtCustomerLastname.getText(),
                    txtCustomerMaill.getText(),
                    txtCustomerPhone.getText(),
                    Integer.parseInt( txtCustomerBalance.getText()),
                    txtCustomerTc.getText()
            );

        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCustomerToTable();
    }
    public void  customerDelete(){
        try{
            dbcon.deleteCustomerDb(
                    Integer.parseInt( txtCustomerId.getText())

            );
        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCustomerToTable();
    }
}
