package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;

public class AddContactGlobalCommand implements Command{

    private InputUtil input;
    private UserDAO database = new UserDAO();

    @Override
    public void execute() {
        input = new InputUtil();
        System.out.println("Name: ");
        String name = input.getString();
        System.out.println("Password: ");
        String password = input.getString();
        System.out.println("Phone number: ");
        String phone = input.getString();
        System.out.println("E-mail: ");
        String email = input.getString();

        //Process phone to contain only numbers
        phone = processPhoneNumber(phone);

        UserProperties userProperties = new UserProperties();
        userProperties.setName(name);
        userProperties.setPassword(password);
        userProperties.setPhone(phone);
        userProperties.setEmail(email);
        System.out.println(name);
        System.out.println(password);
        System.out.println(phone);
        System.out.println(email);
        if(exists(userProperties))
            System.out.println("Error!\nUser with those properties already exists");
        else{
            System.out.println("New contact succesfully added!");
            database.addUser(userProperties);
        }
    }

    private String processPhoneNumber(String phone) {
        String temp = "";

        for(int i = 0;i<phone.length();i++){
            if(Character.isDigit(phone.charAt(i)))
                temp+=phone.charAt(i);
        }

        return temp;
    }

    private boolean exists(UserProperties userProperties) {
        List<UserProperties> list = database.getAllUsers();
        for(UserProperties temp : list){
            if(temp.getName().equals(userProperties.getName()))
                return true;
        }
        return false;
    }
}
