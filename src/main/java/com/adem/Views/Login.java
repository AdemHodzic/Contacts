package com.adem.Views;

import java.util.List;
import java.util.Scanner;

import com.adem.App.InputUtil;
import com.adem.Commander.Invoker;
import com.adem.Commander.LoginCommand;
import com.adem.Commander.RegisterCommand;
import com.adem.Controller.Controller;
import com.adem.Controller.Window;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;


public class Login implements Window {

    private InputUtil input;
    private Invoker invoker;

    public Login(){}

    public void start() {
        input = new InputUtil();

        System.out.println("Welcome! Please log in or register to continue using our app." +
                "\nI want to ..." +
                "\n1. Register" +
                "\n2. Log in");

        int choice = input.getInt();
        System.out.println("Loading...");
        if(choice == 1){
            invoker = new Invoker(new RegisterCommand());
            invoker.execute();
        }else if(choice == 2){
            invoker = new Invoker(new LoginCommand());
            invoker.execute();
        }
    }

}
