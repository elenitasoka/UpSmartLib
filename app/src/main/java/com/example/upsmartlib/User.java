package com.example.upsmartlib;

public class User {
   private   String FullName,email,description,pass;


    public User(String FullName,String email, String pass ,String description){
        this.FullName=FullName;
        this.email=email;
        this.pass=pass;
        this.description=description;

    }
    public User(){}
    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

