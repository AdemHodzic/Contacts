package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Controller.Controller;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;

public class LoginCommand implements Command{

    private InputUtil input = new InputUtil();
    private UserDAO userDAO = new UserDAO();
    private final int MAIN_MENU_POSITION = 1;

    @Override
    public void execute() {
        System.out.println("Name: ");
        String username = input.getString();
        System.out.println("Password: ");
        String password = input.getString();

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
}
