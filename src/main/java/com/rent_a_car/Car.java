package com.rent_a_car;

public class Car {
    private int carId;
    private String carBrand;
    private String carModel;
    private String carGear;
    private int carAvailable;
    private int carPrice;

    public Car(int carId, String carBrand, String carModel,String carGear, int carPrice) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carGear=carGear;
        this.carPrice = carPrice;
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

    public int getCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(int carAvailable) {
        this.carAvailable = carAvailable;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarGear() {
        return carGear;
    }

    public void setCarGear(String carGear) {
        this.carGear = carGear;
    }
}
