package com.adem.Commander;

import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;

public class ListAllContactsCommand implements Command {

    private UserDAO database;
    @Override
    public void execute() {
        database = new UserDAO();
        List<UserProperties> list = database.getAllUsers();

        System.out.println("=======G L O B A L  C O N T A C T S =======");
        for(UserProperties temp : list){
            User tempUser = new User(temp);
            System.out.println(tempUser.toString());
            System.out.println("===========================================");
        }
    }
}
