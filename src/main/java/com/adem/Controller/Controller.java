package com.adem.Controller;

import com.adem.Entities.User;
import com.adem.Views.Login;
import com.adem.Views.MainMenu;

public class Controller {

    private  Window WINDOWS[] = {new Login(), new MainMenu()};
    private  User user;

    public Controller(){

    }

    public void start(){
        WINDOWS[0].start();
    }

    public void start(int index){
        WINDOWS[index].start();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        user = newUser;
    }
}
