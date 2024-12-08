package com.example.ltmt_19303_group6.Model;

public class Order_Model {
    private int id_Order;
    private String date_Order;
    private double total_Price;
    private int id_Customer;
    private int id_Employee;

    public Order_Model(int id_Order, String date_Order, double total_Price, int id_Customer, int id_Employee) {
        this.id_Order = id_Order;
        this.date_Order = date_Order;
        this.total_Price = total_Price;
        this.id_Customer = id_Customer;
        this.id_Employee = id_Employee;
    }

    public int getId_Order() {
        return id_Order;
    }

    public void setId_Order(int id_Order) {
        this.id_Order = id_Order;
    }

    public String getDate_Order() {
        return date_Order;
    }

    public void setDate_Order(String date_Order) {
        this.date_Order = date_Order;
    }

    public double getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(double total_Price) {
        this.total_Price = total_Price;
    }

    public int getId_Customer() {
        return id_Customer;
    }

    public void setId_Customer(int id_Customer) {
        this.id_Customer = id_Customer;
    }

    public int getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(int id_Employee) {
        this.id_Employee = id_Employee;
    }
}
