package com.adem.Controller;

import com.adem.Entities.User;

public class UserManager {

    private static User user;
    private static UserManager instance;

    private UserManager(){}

    public static User getUser(){
        return user;
    }

    public static void setUser(User newUser){
        user = newUser;
    }

    public static UserManager getInstance(){
        if(instance == null)
            return new UserManager();
        return instance;
    }

}
