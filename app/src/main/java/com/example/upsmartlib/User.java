package com.example.upsmartlib;

public class User {
   private   String FullName,email,description;

    public User(){}


    public User(String FullName,String email, String description){
        this.FullName=FullName;
        this.email=email;
        this.description=description;

    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

