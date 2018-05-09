package com.adem.Commander;

import com.adem.App.InputUtil;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;
import java.util.Scanner;

public class EditContactsGlobalCommand implements Command {

    private InputUtil input;
    private UserDAO database = new UserDAO();

    @Override
    public void execute() {
        input = new InputUtil();
        System.out.println("Enter the name of the user you want to edit: ");
        String name = input.getString();
        UserProperties temp = new UserProperties();
        temp.setName(name);

        if(exists(temp)){
            UserProperties edit = database.getUser(temp);
            System.out.println("Select what do you want to edit: " +
                    "\n1. Name" +
                    "\n2. Phone" +
                    "\n3. Mail");

            int choice = input.getInt();
            System.out.println("Enter new value: ");
            if(getNewProperty(choice, edit))
                database.updateUser(edit);
            System.out.println("Email of edited is " + edit.getEmail());

        }else{
            System.out.println("Error!\nUser with that name doesn't exist.");
        }
    }

    private boolean exists(UserProperties userProperties) {
        List<UserProperties> list = database.getAllUsers();
        for(UserProperties temp : list){
            if(temp.getName().equals(userProperties.getName()))
                return true;
        }
        return false;
    }

    private boolean getNewProperty(int choice, UserProperties edit) {
        Scanner input = new Scanner(System.in);
        String newProperty = input.nextLine();
        if(choice == 1) {
            edit.setName(newProperty);
        }else if(choice == 2) {
            newProperty = processPhoneNumber(newProperty);
            edit.setPhone(newProperty);
        }else if(choice == 3){
            edit.setEmail(newProperty);
        }else{
            System.out.println("Invalid input");
            return false;
        }
        return true;
    }

    private String processPhoneNumber(String phone) {
        String temp = "";

        for(int i = 0;i<phone.length();i++){
            if(Character.isDigit(phone.charAt(i)))
                temp+=phone.charAt(i);
        }

        return temp;
    }
}
