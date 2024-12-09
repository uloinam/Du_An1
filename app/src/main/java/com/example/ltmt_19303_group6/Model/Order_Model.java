package com.example.ltmt_19303_group6.Model;

public class Order_Model {
    private Integer idOrder;
    private String dateOrder;
    private Integer totalPrice;
    private Integer idCustomer;
    private Integer idEmployee;
    private Integer idOrderDetail;

    public Order_Model(Integer idOrder, String dateOrder, Integer totalPrice, Integer idCustomer, Integer idEmployee, Integer idOrderDetail) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.totalPrice = totalPrice;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.idOrderDetail = idOrderDetail;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Integer idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }
}
