package com.adem.Controller;

import com.adem.Entities.User;
import com.adem.Views.Login;
import com.adem.Views.MainMenu;

public class Controller {

    private static Window WINDOWS[] = {new Login(), new MainMenu()};
    private static User user;

    public Controller(){

    }

    public void start(){
        WINDOWS[0].start();
    }

    public static void start(int index){
        WINDOWS[index].start();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }
}
