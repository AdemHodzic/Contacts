package com.adem.Views;

import com.adem.Controller.Controller;
import com.adem.Controller.Window;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;
import java.util.Scanner;


public class Login implements Window {

    private UserDAO userDAO = new UserDAO();

    private final int MAIN_MENU_POSITION = 1;

    public Login(){}

    public void start() {
        System.out.println("Welcome! Please log in or register to continue using our app." +
                "\nI want to ..." +
                "\n1. Register" +
                "\n2. Log in");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        while(choice < 1 || choice > 2){
            choice = input.nextInt();
        }

        if(choice == 1)
            register();
        else
            login();
    }

    private void register() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your password: ");
        String password = input.nextLine();
        System.out.println("Enter your phone number: ");
        String phone = input.nextLine();
        System.out.println("Enter your e-mail: ");
        String email = input.nextLine();

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
            userDAO.addUser(userProperties);
            User user = new User(userProperties);
            Controller.setUser(user);
            Controller.start(MAIN_MENU_POSITION);
        }

    }



    private void login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        String username = input.nextLine();
        System.out.println("Password: ");
        String password = input.nextLine();

        UserProperties userProperties = new UserProperties();
        userProperties.setName(username);
        userProperties.setPassword(password);

        if(exists(userProperties)){
            System.out.println("Succefully logged in.");
            userProperties = userDAO.getUser(userProperties);
            Controller.setUser(new User(userProperties));
            Controller.start(MAIN_MENU_POSITION);
        }else{
            System.out.println("Error!\nInvalid input.");
        }

    }

    private boolean exists(UserProperties userProperties) {
        List<UserProperties> list = userDAO.getAllUsers();
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
