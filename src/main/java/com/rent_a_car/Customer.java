package com.rent_a_car;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerLastname;
    private  String customerPhone;
    private  String customerMail;
    private  int customerBalance;
    private  String customerTc;

    public Customer(int customerId, String customerName, String customerLastname, String customerPhone, String customerMail, int customerBalance, String customerTc) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLastname = customerLastname;
        this.customerPhone = customerPhone;
        this.customerMail = customerMail;
        this.customerBalance = customerBalance;
        this.customerTc = customerTc;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public int getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(int customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getCustomerTc() {
        return customerTc;
    }

    public void setCustomerTc(String customerTc) {
        this.customerTc = customerTc;
    }
}
