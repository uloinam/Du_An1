package com.example.ltmt_19303_group6.Model;

import java.io.Serializable;

public class User_Model implements Serializable {
    private Integer Id_User;
    private  Integer Id_Profile, Status;
    private String User_Name, User_Password, User_Posion;

    //
    private String name, email, phone_number;
    private Integer age;
    private byte[] avatar;

    public User_Model(Integer id_User, Integer id_Profile, Integer status, String user_Name, String user_Posion, String name, String email, String phone_number, Integer age, byte[] avatar) {
        Id_User = id_User;
        Id_Profile = id_Profile;
        Status = status;
        User_Name = user_Name;
        User_Posion = user_Posion;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    // Contrustor user
    public User_Model(Integer id_User, Integer id_Profile, Integer status, String user_Name, String user_Password, String user_Posion) {
        Id_User = id_User;
        Id_Profile = id_Profile;
        Status = status;
        User_Name = user_Name;
        User_Password = user_Password;
        User_Posion = user_Posion;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getId_User() {
        return Id_User;
    }

    public void setId_User(Integer id_User) {
        Id_User = id_User;
    }

    public Integer getId_Profile() {
        return Id_Profile;
    }

    public void setId_Profile(Integer id_Profile) {
        Id_Profile = id_Profile;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public String getUser_Posion() {
        return User_Posion;
    }

    public void setUser_Posion(String user_Posion) {
        User_Posion = user_Posion;
    }
}
