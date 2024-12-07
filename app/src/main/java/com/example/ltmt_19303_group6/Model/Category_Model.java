package com.example.ltmt_19303_group6.Model;

public class Category_Model {
    Integer id;
    String name_category;
    Integer id_Group_Product;

    public Category_Model(Integer id, String name_category, Integer id_Group_Product) {
        this.id = id;
        this.name_category = name_category;
        this.id_Group_Product = id_Group_Product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public Integer getId_Group_Product() {
        return id_Group_Product;
    }

    public void setId_Group_Product(Integer id_Group_Product) {
        this.id_Group_Product = id_Group_Product;
    }
}
