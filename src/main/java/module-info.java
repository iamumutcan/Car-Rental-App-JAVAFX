module com.rent_a_car {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rent_a_car to javafx.fxml;
    exports com.rent_a_car;
}