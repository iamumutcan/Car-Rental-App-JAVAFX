package com.rent_a_car;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private int orderTotalPrice;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    private Customer orderCustomer;
    private Car orderCar;
    private String orderDetail;

    public Order(int orderId, int orderTotalPrice, LocalDate rentalStart, LocalDate rentalEnd, Customer orderCustomer, Car orderCar) {
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

    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Car getOrderCar() {
        return orderCar;
    }

    public void setOrderCar(Car orderCar) {
        this.orderCar = orderCar;
    }

    public String getOrderDetail() {
        this.orderDetail="Araba Modeli: "+orderCar.getCarModel()+"Müşteri: "+orderCustomer.getCustomerName();
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }
}
