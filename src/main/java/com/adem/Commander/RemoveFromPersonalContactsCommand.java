package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Controller.Controller;
import com.adem.Controller.UserManager;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

public class RemoveFromPersonalContactsCommand implements Command {

    private UserDAO database;
    private InputUtil input;
    private User user;

    @Override
    public void execute() {

        database = new UserDAO();
        input = new InputUtil();
        user = UserManager.getUser();

        System.out.println("Enter the name of contact you want to remove from your personal contacts: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);
        UserProperties props = database.getUser(temp);

        user.deleteContact(props);

    }
}
