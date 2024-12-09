package com.example.ltmt_19303_group6.Model;

public class Bill_Model {
    private int id_Bill;
    private String date_Bill;
    private double total_Price;
    private int id_Customer;
    private int id_Employee;

    public Bill_Model(int id_Bill, String date_Bill, double total_Price, int id_Customer, int id_Employee) {
        this.id_Bill = id_Bill;
        this.date_Bill = date_Bill;
        this.total_Price = total_Price;
        this.id_Customer = id_Customer;
        this.id_Employee = id_Employee;
    }

    public int getId_Bill() {
        return id_Bill;
    }

    public void setId_Bill(int id_Bill) {
        this.id_Bill = id_Bill;
    }

    public String getDate_Bill() {
        return date_Bill;
    }

    public void setDate_Bill(String date_Bill) {
        this.date_Bill = date_Bill;
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
