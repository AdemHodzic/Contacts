package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Controller.UserManager;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

public class GetInfoByNameCommand implements Command {

    private User user;
    private InputUtil input;
    private UserDAO database;

    @Override
    public void execute() {
        user = UserManager.getUser();
        input = new InputUtil();
        database = new UserDAO();

        System.out.println("Enter the name of contact you are searching for: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);

        User found = new User(database.getUser(temp));

        if(found != null){
            System.out.println("\n-----------------\n");
            System.out.println(found.toString());
            System.out.println("\n-----------------\n");
        }
    }
}
