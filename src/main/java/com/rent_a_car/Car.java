package com.rent_a_car;

public class Car {
    private int carId;
    private String carBrand;
    private String carModel;
    private String carGear;
    private int carPrice;
    private String carPlate;
    private String carFuelType;

    public Car(int carId, String carBrand, String carModel, String carGear, int carPrice, String carPlate, String carFuelType) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carGear = carGear;
        this.carPrice = carPrice;
        this.carPlate = carPlate;
        this.carFuelType = carFuelType;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarGear() {
        return carGear;
    }

    public void setCarGear(String carGear) {
        this.carGear = carGear;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }
}
