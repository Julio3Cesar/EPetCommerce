package com.example.epetcommerce.database;

import com.example.epetcommerce.models.Customer;

public class UserData {

    private static UserData userData;

    private Customer user;

    private UserData() {
    }

    public static UserData getInstance() {
        if (userData == null) {
            userData = new UserData();
        }
        return userData;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}