package com.rent_a_car;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private int orderId;
    private int orderTotalPrice;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    private int orderCustomer;
    private int orderCar;

    public Order(int orderId, int orderCustomer, int orderCar, LocalDate rentalStart, LocalDate rentalEnd,  int orderTotalPrice) {
        this.orderId = orderId;
        this.orderTotalPrice = orderTotalPrice;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.orderCustomer = orderCustomer;
        this.orderCar = orderCar;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public LocalDate getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(LocalDate rentalStart) {
        this.rentalStart = rentalStart;
    }

    public LocalDate getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(LocalDate rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public int getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(int orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public int getOrderCar() {
        return orderCar;
    }

    public void setOrderCar(int orderCar) {
        this.orderCar = orderCar;
    }
}
