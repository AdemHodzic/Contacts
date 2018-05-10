package com.adem.Entities;

import com.adem.Models.UserDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {


    private UserProperties userProperties;
    private PersonalContacts personalContacts;
    private UserDAO userDAO = new UserDAO();

    public User(){
        personalContacts = new PersonalContacts();
    }

    public User(UserProperties userProperties){
        this.userProperties = userProperties;
        personalContacts = new PersonalContacts(userProperties.getContacts());
    }

    public void addContact(User user){
        String contact = user.getId() + ",";
        setContacts(this.getContacts()+contact);
        userDAO.updateUser(this.getUserProperties());
    }

    public void deleteContact(UserProperties user){
        String regex = user.getId() + ",";
        String current = this.getContacts();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(current);
        System.out.println("Contacts before: " + current);
        String newContacts = matcher.replaceFirst("");
        System.out.println("Contacts after: " + newContacts);
        this.setContacts(newContacts);
        userDAO.updateUser(this.getUserProperties());
    }

    public UserProperties searchContact(User user){
        makeList();
        for(UserProperties temp : personalContacts.getList()){
            if(temp.getName().equals(user.getName()))
                return temp;
        }
        return new UserProperties(); //I don't want to return null
    }

    private void makeList() {
        personalContacts.setStringVersion(this.getContacts());
    }

    public void addUser(User user){
        userDAO.addUser(user.getUserProperties());
    }

    public void editUser(User user){
        userDAO.updateUser(user.getUserProperties());
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user.getUserProperties());
    }

    public int getId() {
        return this.userProperties.getId();
    }

    public void setId(int id) {
        this.userProperties.setId(id);
    }

    public String getName() {
        return this.userProperties.getName();
    }

    public void setName(String name) {
        this.userProperties.setName(name);
    }

    public String getPassword() {
        return this.userProperties.getPassword();
    }

    public void setPassword(String password) {
        this.userProperties.setPassword(password);
    }

    public String getPhone() {
        return this.userProperties.getPhone();
    }

    public void setPhone(String phone) {
        this.userProperties.setPhone(phone);
    }

    public String getEmail() {
        return this.userProperties.getEmail();
    }

    public void setEmail(String email) {
        this.userProperties.setEmail(email);
    }

    public String getContacts() {
        return this.userProperties.getContacts();
    }

    public void setContacts(String contacts) {
        this.userProperties.setContacts(contacts);
        makeList();
    }

    public UserProperties getUserProperties() {
        return userProperties;
    }

    @Override
    public String toString() {
        String str = "ID: " + this.getId()
                +"\nName: " + this.getName()
                +"\nPhone: " + this.getPhone()
                +"\nEmail: " +this.getEmail();
        return str;
    }

    public PersonalContacts getPersonalContacts() {
        return personalContacts;
    }
}
