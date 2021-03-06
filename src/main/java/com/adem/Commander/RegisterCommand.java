package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Controller.Controller;
import com.adem.Controller.UserManager;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;

public class RegisterCommand implements Command {

    private InputUtil input;
    private UserDAO database;
    private Controller controller;
    private final int MAIN_MENU_POSITION = 1;

    @Override
    public void execute() {

        controller = new Controller();
        input = new InputUtil();
        database = new UserDAO();

        System.out.println("Enter your name: ");
        String name = input.getString();
        System.out.println("Enter your password: ");
        String password = input.getString();
        System.out.println("Enter your phone number: ");
        String phone = input.getString();
        System.out.println("Enter your e-mail: ");
        String email = input.getString();

        //Process phone to contain only numbers
        phone = processPhoneNumber(phone);

        UserProperties userProperties = new UserProperties();
        userProperties.setName(name);
        userProperties.setPassword(password);
        userProperties.setPhone(password);
        userProperties.setPhone(phone);
        userProperties.setEmail(email);

        //Check of user alreadyExists
        if(exists(userProperties))
            System.out.println("Error!\nUser with those properties already exists");
        else{
            System.out.println("Succefully registered.");
            database.addUser(userProperties);
            User user = new User(userProperties);
            UserManager.setUser(user);
            controller.start(MAIN_MENU_POSITION);
        }
    }

    private boolean exists(UserProperties userProperties) {
        List<UserProperties> list = database.getAllUsers();
        for(UserProperties temp : list){
            if(temp.getName().equals(userProperties.getName()) && temp.getPassword().equals(userProperties.getPassword()))
                return true;
        }
        return false;
    }

    private String processPhoneNumber(String phone) {
        String temp = "";

        for(int i = 0;i<phone.length();i++){
            if(Character.isDigit(phone.charAt(i)))
                temp+=phone.charAt(i);
        }

        return temp;
    }
}
