package com.adem.Views;

import com.adem.App.InputUtil;
import com.adem.Controller.Controller;
import com.adem.Controller.Window;
import com.adem.Entities.User;
import com.adem.Entities.UserProperties;
import com.adem.Models.UserDAO;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu implements Window {

    private User user;
    private final int LOGIN_POSITION = 0;
    private UserDAO userDAO = new UserDAO();

    private InputUtil input;

    public MainMenu(){}

    public void start() {
        input = new InputUtil();
        this.user = Controller.getUser();
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
                    addContactToGlobal();
                    break;
                case 2:
                    editContactInGlobal();
                    break;
                case 3:
                    deleteContactFromGlobal();
                    break;
                case 4:
                    listAllContacts();
                    break;
                case 5:
                    listPersonalContacts();
                    break;
                case 6:
                    addToPersonalContacts();
                    break;
                case 7:
                    removeFromPersonalContacts();
                    break;
                case 8:
                    logout();
                    break;

            }
        }
    }

    private void addContactToGlobal() {

        System.out.println("Name: ");
        String name = input.getString();
        System.out.println("Password: ");
        String password = input.getString();
        System.out.println("Phone number: ");
        String phone = input.getString();
        System.out.println("E-mail: ");
        String email = input.getString();

        //Process phone to contain only numbers
        phone = processPhoneNumber(phone);

        UserProperties userProperties = new UserProperties();
        userProperties.setName(name);
        userProperties.setPassword(password);
        userProperties.setPhone(password);
        userProperties.setPhone(phone);
        userProperties.setEmail(email);

        if(exists(userProperties))
            System.out.println("Error!\nUser with those properties already exists");
        else{
            System.out.println("New contact succesfully added!");
            userDAO.addUser(userProperties);
        }
    }

    private void editContactInGlobal(){
        System.out.println("Enter the name of the user you want to edit: ");
        String name = input.getString();
        UserProperties temp = new UserProperties();
        temp.setName(name);

        if(exists(temp)){
            UserProperties edit = userDAO.getUser(temp);
            System.out.println("Select what do you want to edit: " +
                    "\n1. Name" +
                    "\n2. Phone" +
                    "\n3. Mail");

            int choice = input.getInt();
            System.out.println("Enter new value: ");
            if(getNewProperty(choice, edit))
                userDAO.updateUser(edit);
            System.out.println("Email of edited is " + edit.getEmail());

        }else{
            System.out.println("Error!\nUser with that name doesn't exist.");
        }
    }



    private void deleteContactFromGlobal(){
        System.out.println("Enter the name of user you want to remove from global contacts: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);
        UserProperties deleted = userDAO.getUser(temp);
        userDAO.deleteUser(deleted);
    }

    private void listAllContacts(){
        List<UserProperties> list = userDAO.getAllUsers();

        System.out.println("=======G L O B A L  C O N T A C T S =======");
        for(UserProperties temp : list){
            User tempUser = new User(temp);
            System.out.println(tempUser.toString());
            System.out.println("===========================================");
        }
    }

    private void listPersonalContacts(){
        List<UserProperties> list = user.getPersonalContacts().getList();
        System.out.println("=======P E R S O N A L  C O N T A C T S =======");
        for(UserProperties user : list){
            System.out.println(new User(user).toString());
            System.out.println("===============================================");
        }
    }

    private void addToPersonalContacts(){
        System.out.println("Enter the name of contact you want to add to your personal contacts: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);
        UserProperties props = userDAO.getUser(temp);

        user.addContact(new User(props));
    }

    private void removeFromPersonalContacts(){
        System.out.println("Enter the name of contact you want to remove from your personal contacts: ");
        String name = input.getString();

        UserProperties temp = new UserProperties();
        temp.setName(name);
        UserProperties props = userDAO.getUser(temp);

        user.deleteContact(props);

    }

    private void logout() {
        System.out.println("Thank you for using our app.");
        Controller.start(LOGIN_POSITION);
    }

    private String processPhoneNumber(String phone) {
        String temp = "";

        for(int i = 0;i<phone.length();i++){
            if(Character.isDigit(phone.charAt(i)))
                temp+=phone.charAt(i);
        }

        return temp;
    }

    private boolean exists(UserProperties userProperties) {
        List<UserProperties> list = userDAO.getAllUsers();
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
}
