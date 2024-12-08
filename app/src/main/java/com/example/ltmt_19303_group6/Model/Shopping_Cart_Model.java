package com.example.ltmt_19303_group6.Model;

public class Shopping_Cart_Model {
    private int id_Cart;
    private int quantity;
    private double unit_Price;
    private int id_Product;
    private double subtotal;

    // Constructor

    public Shopping_Cart_Model(int id_Cart, int quantity, double unit_Price, int id_Product, double subtotal) {
        this.id_Cart = id_Cart;
        this.quantity = quantity;
        this.unit_Price = unit_Price;
        this.id_Product = id_Product;
        this.subtotal = subtotal;
    }

    public int getId_Cart() {
        return id_Cart;
    }

    public void setId_Cart(int id_Cart) {
        this.id_Cart = id_Cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_Price() {
        return unit_Price;
    }

    public void setUnit_Price(double unit_Price) {
        this.unit_Price = unit_Price;
    }

    public int getId_Product() {
        return id_Product;
    }

    public void setId_Product(int id_Product) {
        this.id_Product = id_Product;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
