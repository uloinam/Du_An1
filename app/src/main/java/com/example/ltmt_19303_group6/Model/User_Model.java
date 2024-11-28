package com.example.ltmt_19303_group6.Model;

import java.io.Serializable;

public class User_Model implements Serializable {
    private Integer Id_User;
    private  Integer Id_Profile;
    private String User_Name, User_Password, User_Posion;

    public User_Model(Integer id_User, Integer id_Profile, String user_Name, String user_Password, String user_Posion) {
        Id_User = id_User;
        Id_Profile = id_Profile;
        User_Name = user_Name;
        User_Password = user_Password;
        User_Posion = user_Posion;
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
