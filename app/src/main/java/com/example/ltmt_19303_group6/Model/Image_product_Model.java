package com.example.ltmt_19303_group6.Model;

public class Image_product_Model {
    Integer id_image, id_Product;
    byte[] image;

    public Image_product_Model(Integer id_image, Integer id_Product, byte[] image) {
        this.id_image = id_image;
        this.id_Product = id_Product;
        this.image = image;
    }

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }

    public Integer getId_Product() {
        return id_Product;
    }

    public void setId_Product(Integer id_Product) {
        this.id_Product = id_Product;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
