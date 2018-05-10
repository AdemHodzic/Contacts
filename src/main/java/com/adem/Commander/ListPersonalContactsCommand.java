package com.adem.Commander;

import com.adem.Controller.Controller;
import com.adem.Controller.UserManager;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;

import java.util.List;

public class ListPersonalContactsCommand implements Command {
    private User user;
    @Override
    public void execute() {
        user = UserManager.getUser();
        List<UserProperties> list = user.getPersonalContacts().getList();
        System.out.println("=======P E R S O N A L  C O N T A C T S =======");
        for(UserProperties user : list){
            System.out.println(new User(user).toString());
            System.out.println("===============================================");
        }
    }
}
