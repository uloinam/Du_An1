package com.example.ltmt_19303_group6.Model;

import android.widget.ImageView;

public class Shop_Cart_Model {
    private Integer idCart;       // ID_Cart - Primary Key
    private int quantity;     // Quantity
    private int unitPrice; // Unit_Price
    private int idProduct;    // FK - ID_Product
    private int subtotal;
    private Integer id_Order;
    private String name_Product;
    Integer price_product;
    byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Shop_Cart_Model(Integer idCart, int quantity, int unitPrice, int idProduct, int subtotal, Integer id_Order ) {
        this.idCart = idCart;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.idProduct = idProduct;
        this.subtotal = subtotal;
        this.id_Order = id_Order;
    }

    public Shop_Cart_Model(Integer idCart, int quantity, int unitPrice, int idProduct, int subtotal, String name_Product, Integer price_product, byte[] image) {
        this.idCart = idCart;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.idProduct = idProduct;
        this.subtotal = subtotal;
        this.name_Product = name_Product;
        this.price_product = price_product;
        this.image = image;
    }

    public String getName_Product() {
        return name_Product;
    }

    public void setName_Product(String name_Product) {
        this.name_Product = name_Product;
    }

    public Integer getPrice_product() {
        return price_product;
    }

    public void setPrice_product(Integer price_product) {
        this.price_product = price_product;
    }


    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
