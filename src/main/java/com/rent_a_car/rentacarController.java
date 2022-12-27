package com.rent_a_car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class rentacarController implements Initializable {
    @FXML
    private TextField txtCarId,txtCarPlate,txtCarBrand,txtCarModel,txtCarPrice,txtCustomerId,txtCustomerName,txtCustomerLastname,txtCustomerPhone,txtCustomerMaill,txtCustomerBalance,txtCustomerTc;
    @FXML
    private TextField KTxtOrderId;
    @FXML
    private TextField KTxtCarId;
    @FXML
    private TextField KTxtCustomerId;
    @FXML
    private TextField KOrderTotalPrice;
    @FXML
    private DatePicker KDateRentalStart ,KDateRentalEnd;
    @FXML
    private ComboBox cmbCarFuelType,cmbCarGear;
    @FXML
    private TableView<Car> tableCars,TableOrderCar;
    @FXML
    private TableView<Customer> tableCustomer,TableOrderCustomer;
    @FXML
    private TableView<Order> TableOrder;
    @FXML
    private TableColumn<Car, String> colCarId;
    @FXML
    private TableColumn<Car, String> colCarBrand;
    @FXML
    private TableColumn<Car, String> colCarModel;
    @FXML
    private TableColumn<Car, String> ColOCarId,ColOCarBrand,ColOCarModel,ColOCarGear,ColOCarPrice,ColOCarPlate,ColOCarFuelType;
    @FXML
    private TableColumn<Customer, String> ColOCustomerId, ColOCustomerName, ColOCustomerLastname ,ColOCustomerPhone, ColOCustomerMail, ColOCustomerBalance, ColOCustomerTc;
   @FXML
    private TableColumn<Order, String> colOrderId,ColOrderCustomerId,ColOrderCarId;
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
    int selectedCarPrice;
    int orderCarPrice;
    int xOrderTotalPrice;
    Date rentalDateStart;
    Date rentalDateEnd;
    public void initialize(URL url, ResourceBundle resourceBundle) {
       getCarToTable();
         getCustomerToTable();
        getOrderToTable();
        getOrderCarToTable();
        getOrderCustomerToTable();
        tableCars.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> carTableToTextField(newValue));
        tableCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> customerTableToTextField(newValue));
        TableOrder.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> orderableToTextField(newValue));
        TableOrderCar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> OrdercarTableToTextField(newValue));
        TableOrderCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> OrderCustomerTableToTextField(newValue));
        KDateRentalStart.valueProperty().addListener((ov, oldValue, newValue) -> {
            rentalDateStart= Date.valueOf(KDateRentalStart.getValue());
            orderPriceCalculate();
        });
        KDateRentalEnd.valueProperty().addListener((ov, oldValue, newValue) -> {
            rentalDateEnd= Date.valueOf(KDateRentalEnd.getValue());
            orderPriceCalculate();
        });
    }
    public void orderPriceCalculate(){
       if(rentalDateStart!=null&&rentalDateEnd!=null){
           long difference_In_Time =rentalDateEnd.getTime()-rentalDateStart.getTime();
           long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
           int xorderdays=Integer.parseInt(String.valueOf(difference_In_Days));
           if(xorderdays==0) xorderdays=1;
           xOrderTotalPrice=xorderdays*selectedCarPrice;
           KOrderTotalPrice.setText(Integer.toString(xOrderTotalPrice ));
       }



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

    // Müşteri işlemleri bitiş  --------------------------------------------------------------------
    // Kiralama işlemleri  --------------------------------------------------------------------

    public void OrderAdd()throws SQLException {
        try {
            dbcon.insertOrderDb(
                    KTxtCustomerId.getText(),
                    KTxtCarId.getText(),
                    rentalDateStart,
                    rentalDateEnd,
                    xOrderTotalPrice
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        getOrderToTable();
    }
    public void OrderUpdate()throws SQLException {
        try {
            dbcon.updateOrderDb(
                    Integer.parseInt( KTxtCustomerId.getText()),
                    Integer.parseInt( KTxtCarId.getText()),
                    rentalDateStart,
                    rentalDateEnd,
                    Integer.parseInt( KOrderTotalPrice.getText()),
                    Integer.parseInt( KTxtOrderId.getText())
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        getOrderToTable();
    }
    public void  OrderDelete(){
        System.out.println("hiç");

        try{
            System.out.println("hi222ç");
            dbcon.deleteOrderDb(
                    Integer.parseInt( KTxtOrderId.getText())

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
        getOrderToTable();
    }


    public void  getOrderToTable(){ // Arabalar listesindeki arabaları tabloya ekler
        orderList=(ObservableList<Order>)dbcon.getDbOrder();
        colOrderId.setCellValueFactory(new PropertyValueFactory<Order,String>("orderId"));
        ColOrderCustomerId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderCustomer"));
        ColOrderCarId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderCar"));
        TableOrder.setItems(orderList);
        TableOrder.refresh();
    }

    public void orderableToTextField(Order xorder){
        if(xorder!=null){
            KTxtOrderId.setText(Integer.toString(xorder.getOrderId()));
            KTxtCustomerId.setText(Integer.toString(xorder.getOrderCustomer()));
            KTxtCarId.setText(Integer.toString(xorder.getOrderCar()));
            KDateRentalStart.setValue(xorder.getRentalStart());
            KDateRentalEnd.setValue(xorder.getRentalEnd());
            KOrderTotalPrice.setText(Integer.toString(xorder.getOrderTotalPrice()));

        }
    }
    public void  getOrderCarToTable(){ // Arabalar listesindeki arabaları tabloya ekler
        carsList=(ObservableList<Car>)dbcon.getDbCar();
        ColOCarId.setCellValueFactory(new PropertyValueFactory<Car,String>("carId"));
        ColOCarBrand.setCellValueFactory(new PropertyValueFactory<Car, String>("carBrand"));
        ColOCarModel.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
        ColOCarGear.setCellValueFactory(new PropertyValueFactory<Car, String>("carGear"));
        ColOCarPrice.setCellValueFactory(new PropertyValueFactory<Car, String>("carPrice"));
        ColOCarPlate.setCellValueFactory(new PropertyValueFactory<Car, String>("carPlate"));
        ColOCarFuelType.setCellValueFactory(new PropertyValueFactory<Car, String>("carFuelType"));
        TableOrderCar.setItems(carsList);
        TableOrderCar.refresh();
    }
    public void OrdercarTableToTextField(Car xcar){
        if(xcar!=null){
            KTxtCarId.setText(Integer.toString(xcar.getCarId()) );
            selectedCarPrice= xcar.getCarPrice();
            orderPriceCalculate();
        }
    }
    public void  getOrderCustomerToTable(){ // Arabalar listesindeki arabaları tabloya ekler
        customerList=(ObservableList<Customer>)dbcon.getDbCustomer();
        ColOCustomerId.setCellValueFactory(new PropertyValueFactory<Customer,String>("customerId"));
        ColOCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        ColOCustomerLastname.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerLastname"));
        ColOCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        ColOCustomerMail.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerMail"));
        ColOCustomerBalance.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerBalance"));
        ColOCustomerTc.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerTc"));
        TableOrderCustomer.setItems(customerList);
        TableOrderCustomer.refresh();
    }
    public void OrderCustomerTableToTextField(Customer xcustomer){
        if(xcustomer!=null){
            KTxtCustomerId.setText(Integer.toString(xcustomer.getCustomerId()) );
        }
    }

}
