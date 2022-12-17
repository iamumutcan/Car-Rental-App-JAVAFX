package com.rent_a_car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class rentacarController implements Initializable {
    @FXML
    private TextField txtCarId,txtCarBrand,txtCarModel,txtCarPrice,txtCarGear;
    @FXML
    private TableView<Car> tableCars;
    @FXML
    private TableColumn<Car, String> colCarId;
    @FXML
    private TableColumn<Car, String> colCarBrand;
    @FXML
    private TableColumn<Car, String> colCarModel;
    @FXML
    private ObservableList<Car> carsList= FXCollections.observableArrayList(); // Araba listesi
    @FXML
    private ObservableList<Customer>  customerList= FXCollections.observableArrayList(); // Müşteri listesi
    @FXML
    private ObservableList<Order> orderList= FXCollections.observableArrayList(); // Sipariş listesi
    public void  getCarToTable(){ // Arabalar listesindeki arabaları tabloya ekler
        colCarId.setCellValueFactory(new PropertyValueFactory<Car,String>("carId"));
        colCarBrand.setCellValueFactory(new PropertyValueFactory<Car, String>("carBrand"));
        colCarModel.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
        tableCars.setItems(carsList);
        tableCars.refresh();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        carBuilder();
        getCarToTable();
        customerBuilder();
        orderBuilder();
        // carsList.get(4).getCarModel())
        tableCars.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> carTableToTextField(newValue));
        System.out.println("Sipariş"+orderList.get(0).getOrderDetail());

    }

    // +++ ilk açılışta  oluşacak veriler  +++
    public void carBuilder(){ // araba nesnesi oluşturup arabalar listesine ekler
        carsList.add(new Car(001,"Ford","Focus", "Otomatik",350));
        carsList.add(new Car(002,"Ford","Fiesta", "Otomatik",200));
        carsList.add(new Car(003,"Ford","Mustang","Otomatik", 500));
        carsList.add(new Car(004,"Toyota","Corolla","Otomatik",325));
        carsList.add(new Car(005,"Tofaş","Serçe", "Manuel",75));
        carsList.add(new Car(006,"Tofaş","Şahin", "Manuel",80));
    }
    public void customerBuilder(){ // müşteri nesnesi oluşturup müşteriler listesine ekler
        customerList.add(new Customer("005","Umut"));
        customerList.add(new Customer("006","Ali"));
        customerList.add(new Customer("007","Mustafa"));
    }
    public void orderBuilder(){ // sipariş nesnesi oluşturup siparişler listesine ekler
        orderList.add(new Order(05,800,
                LocalDate.of(2022,12,10),
                LocalDate.of(2022,12,13),
                customerList.get(1),
                carsList.get(4)
        ));
    }
    // --- ilk açılışta  oluşacak veriler  ---

    // Araba işlemleri başlangıç

    public void  carFormClear(){
        txtCarId.setText("");
        txtCarBrand.setText("");
        txtCarModel.setText("");
        txtCarGear.setText("");
        txtCarPrice.setText("");

    }
    public void carAdd(){
        carsList.add(new Car(
          Integer.parseInt( txtCarId.getText()),txtCarBrand.getText(),txtCarModel.getText(),txtCarGear.getText(),Integer.parseInt(txtCarPrice.getText())
        ));
        getCarToTable();
    }
    public void  carUpdate(){
        try{
            int selectCar=tableCars.getSelectionModel().getSelectedIndex();
            if(selectCar!=-1){
                carsList.get(selectCar).setCarId(Integer.parseInt( txtCarId.getText()));
                carsList.get(selectCar).setCarBrand(txtCarBrand.getText());
                carsList.get(selectCar).setCarModel(txtCarModel.getText());
                carsList.get(selectCar).setCarGear(txtCarGear.getText());
                carsList.get(selectCar).setCarPrice(Integer.parseInt( txtCarPrice.getText()));
                getCarToTable();
            }
        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        }

    }
    public void  carDelete(){
        try{
            int selectCar=tableCars.getSelectionModel().getSelectedIndex();
            if(selectCar!=-1){
                carsList.remove(selectCar);
                getCarToTable();
            }
        }
        catch (NumberFormatException hata ){
            Alert uyari= new Alert(Alert.AlertType.ERROR);
            uyari.setTitle("Hata");
            uyari.setHeaderText("Lütfen daha sonra tekrar deneyiniz");
            uyari.setContentText(hata.toString());
            uyari.showAndWait();
        }

    }
    public void carTableToTextField(Car xcar){
        if(xcar!=null){
            txtCarId.setText(Integer.toString(xcar.getCarId()) );
            txtCarBrand.setText(xcar.getCarBrand());
            txtCarModel.setText(xcar.getCarModel());
            txtCarGear.setText(xcar.getCarGear());
            txtCarPrice.setText(Integer.toString(xcar.getCarPrice()) );


        }
    }



    // Araba işlemleri bitiş

}
