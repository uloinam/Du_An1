package com.example.ltmt_19303_group6.Model;

public class Bill_Detail_Model {
    private Integer idOrderDetail; // Primary Key
    private int quantity;
    private double pricePerUnit;

    public Bill_Detail_Model(Integer idOrderDetail, int quantity, Integer pricePerUnit) {
        this.idOrderDetail = idOrderDetail;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public int getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(int idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
