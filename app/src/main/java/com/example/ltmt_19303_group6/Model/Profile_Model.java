package com.example.ltmt_19303_group6.Model;

import java.io.Serializable;

public class Profile_Model implements Serializable {
    private Integer Id_Profile, Age;
    private String name_Empolyee, Email_Empolyee, Phone_Empolyee;
    private byte[] avatar;

    public Profile_Model(Integer id_Profile, Integer age, String name_Empolyee, String email_Empolyee, String phone_Empolyee, byte[] avatar) {
        Id_Profile = id_Profile;
        Age = age;
        this.name_Empolyee = name_Empolyee;
        Email_Empolyee = email_Empolyee;
        Phone_Empolyee = phone_Empolyee;
        this.avatar = avatar;
    }

    public Integer getId_Profile() {
        return Id_Profile;
    }

    public void setId_Profile(Integer id_Profile) {
        Id_Profile = id_Profile;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getName_Empolyee() {
        return name_Empolyee;
    }

    public void setName_Empolyee(String name_Empolyee) {
        this.name_Empolyee = name_Empolyee;
    }

    public String getEmail_Empolyee() {
        return Email_Empolyee;
    }

    public void setEmail_Empolyee(String email_Empolyee) {
        Email_Empolyee = email_Empolyee;
    }

    public String getPhone_Empolyee() {
        return Phone_Empolyee;
    }

    public void setPhone_Empolyee(String phone_Empolyee) {
        Phone_Empolyee = phone_Empolyee;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
