package com.example.miniprojet.Metier;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Client implements Serializable {
    private String UserName ;
    private String Password;
    private String Phone;
    private String Adress;
    private String Service;
    private Bitmap Image;
    private String Description;
    private Integer Id;

    public Client(String userName, String password, String phone, String adress,String service, Bitmap image, String description) {
        UserName = userName;
        Password = password;
        Phone = phone;
        Adress = adress;
        Service = service;
        Image = image;
        Description = description;
    }
    public Client(String userName, String phone, String adress, Bitmap image, String description) {
        UserName = userName;
        Phone = phone;
        Adress = adress;
        Image = image;
        Description = description;
    }


    public Client(){}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName(){
        return UserName;
    }

    public void setUserName(String userName){
        UserName=userName;
    }

    public String getPassword(){
        return Password;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }
    public void setPassword(String password){
        Password=password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }




}

