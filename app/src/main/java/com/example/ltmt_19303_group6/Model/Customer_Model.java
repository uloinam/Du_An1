package com.example.ltmt_19303_group6.Model;

public class Customer_Model {
    private Integer id_Customer;
    private String name_Customer;
    private int age_Customer;
    private String address_Customer;
    private String phoneNumber_Customer;

    // Constructor

    public Customer_Model(Integer id_Customer, String name_Customer, int age_Customer, String address_Customer, String phoneNumber_Customer) {
        this.id_Customer = id_Customer;
        this.name_Customer = name_Customer;
        this.age_Customer = age_Customer;
        this.address_Customer = address_Customer;
        this.phoneNumber_Customer = phoneNumber_Customer;
    }

    public int getId_Customer() {
        return id_Customer;
    }

    public void setId_Customer(int id_Customer) {
        this.id_Customer = id_Customer;
    }

    public String getName_Customer() {
        return name_Customer;
    }

    public void setName_Customer(String name_Customer) {
        this.name_Customer = name_Customer;
    }

    public int getAge_Customer() {
        return age_Customer;
    }

    public void setAge_Customer(int age_Customer) {
        this.age_Customer = age_Customer;
    }

    public String getAddress_Customer() {
        return address_Customer;
    }

    public void setAddress_Customer(String address_Customer) {
        this.address_Customer = address_Customer;
    }

    public String getPhoneNumber_Customer() {
        return phoneNumber_Customer;
    }

    public void setPhoneNumber_Customer(String phoneNumber_Customer) {
        this.phoneNumber_Customer = phoneNumber_Customer;
    }
}
