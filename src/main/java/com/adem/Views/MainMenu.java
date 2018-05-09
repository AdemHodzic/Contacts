package com.adem.Views;

import com.adem.App.InputUtil;
import com.adem.Commander.*;
import com.adem.Controller.Controller;
import com.adem.Controller.UserManager;
import com.adem.Controller.Window;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu implements Window {

    private InputUtil input;
    private Invoker invoker;
    private User user;

    public MainMenu(){}

    public void start() {

        input = new InputUtil();
        user = UserManager.getUser();

        while(true) {
            System.out.println("Welcome, " + user.getName());
            System.out.println("Choose what do you want to do: " +
                    "\n1.Add contact in global contacts" +
                    "\n2.Edit contact in global contacts" +
                    "\n3.Delete contact from global contacts" +
                    "\n4.List all contacts" +
                    "\n5.List my contacts" +
                    "\n6.Add contact to my contacts" +
                    "\n7.Remove contact from my contacts" +
                    "\n8.Log out");
            int choice = input.getInt();
            while (choice < 1 | choice > 8) {
                choice = input.getInt();
            }

            switch (choice) {
                case 1:
                    invoker = new Invoker(new AddContactGlobalCommand());
                    invoker.execute();
                    break;
                case 2:
                    invoker = new Invoker(new EditContactsGlobalCommand());
                    invoker.execute();
                    break;
                case 3:
                    invoker = new Invoker(new DeleteContactsGlobalCommand());
                    invoker.execute();
                    break;
                case 4:
                    invoker = new Invoker(new ListAllContactsCommand());
                    invoker.execute();
                    break;
                case 5:
                    invoker = new Invoker(new ListPersonalContactsCommand());
                    invoker.execute();
                    break;
                case 6:
                    invoker = new Invoker(new AddPersonalContactCommand());
                    invoker.execute();
                    break;
                case 7:
                    invoker = new Invoker(new RemoveFromPersonalContactsCommand());
                    invoker.execute();
                    break;
                case 8:
                    invoker = new Invoker(new LogoutCommand());
                    invoker.execute();
                    break;

            }
        }
    }
}
