package com.example.ltmt_19303_group6.Model;

public class Product_Model {
    Integer id_productl, quantity_Product, price_product, height, weight, status, wight, ID_Brand, ID_Category;
    String name, describle, created, updated, Supplier;
    byte[] image;
    public Product_Model(Integer id_productl, Integer quantity_Product, Integer price_product, Integer height, Integer weight, Integer status, Integer wight, Integer ID_Brand, Integer ID_Category, String name, String describle, String created, String updated, String supplier) {
        this.id_productl = id_productl;
        this.quantity_Product = quantity_Product;
        this.price_product = price_product;
        this.height = height;
        this.weight = weight;
        this.status = status;
        this.wight = wight;
        this.ID_Brand = ID_Brand;
        this.ID_Category = ID_Category;
        this.name = name;
        this.describle = describle;
        this.created = created;
        this.updated = updated;
        Supplier = supplier;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product_Model(Integer id_productl, Integer quantity_Product, Integer price_product, Integer height, Integer weight, Integer status, Integer wight, Integer ID_Brand, Integer ID_Category, String name, String describle, String created, String updated, String supplier, byte[] image) {
        this.id_productl = id_productl;
        this.quantity_Product = quantity_Product;
        this.price_product = price_product;
        this.height = height;
        this.weight = weight;
        this.status = status;
        this.wight = wight;
        this.ID_Brand = ID_Brand;
        this.ID_Category = ID_Category;
        this.name = name;
        this.describle = describle;
        this.created = created;
        this.updated = updated;
        Supplier = supplier;
        this.image = image;
    }

    public Integer getId_productl() {
        return id_productl;
    }

    public void setId_productl(Integer id_productl) {
        this.id_productl = id_productl;
    }

    public Integer getQuantity_Product() {
        return quantity_Product;
    }

    public void setQuantity_Product(Integer quantity_Product) {
        this.quantity_Product = quantity_Product;
    }

    public Integer getPrice_product() {
        return price_product;
    }

    public void setPrice_product(Integer price_product) {
        this.price_product = price_product;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWight() {
        return wight;
    }

    public void setWight(Integer wight) {
        this.wight = wight;
    }

    public Integer getID_Brand() {
        return ID_Brand;
    }

    public void setID_Brand(Integer ID_Brand) {
        this.ID_Brand = ID_Brand;
    }

    public Integer getID_Category() {
        return ID_Category;
    }

    public void setID_Category(Integer ID_Category) {
        this.ID_Category = ID_Category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }
}
