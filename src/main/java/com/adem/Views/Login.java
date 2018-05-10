package com.adem.Views;


import com.adem.App.InputUtil;
import com.adem.Commander.*;
import com.adem.Controller.Window;


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
