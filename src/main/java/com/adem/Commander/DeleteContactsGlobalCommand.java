package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

public class DeleteContactsGlobalCommand implements Command {

    private InputUtil input;
    private UserDAO database;

    @Override
    public void execute() {
        database = new UserDAO();
        input = new InputUtil();
        System.out.println("Enter the name of user you want to remove from global contacts: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);
        UserProperties deleted = database.getUser(temp);
        database.deleteUser(deleted);

    }
}
