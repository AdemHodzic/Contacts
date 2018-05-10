package com.adem.Entities;

import com.adem.Models.UserDAO;

import java.util.LinkedList;
import java.util.List;

public class PersonalContacts {

    private UserDAO userDAO = new UserDAO();
    private List<UserProperties> list = new LinkedList<>();
    private String stringVersion;

    public PersonalContacts(){ }

    public PersonalContacts(String stringVersion) {
        this.stringVersion = stringVersion;
        initList();
    }

    private void initList(){
        try{
            list.clear();
            String[] arr = stringVersion.split(",");
            for(String str : arr){
                if(str.equals("") || str== null)
                    continue;
                UserProperties temp = userDAO.getUser(Integer.parseInt(str));
                list.add(temp);
            }
        }catch (NullPointerException en){
            System.out.println("that user doesn't exist");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<UserProperties> getList() {
        initList();
        return list;
    }

    public String getStringVersion() {
        return stringVersion;
    }

    public void setStringVersion(String stringVersion) {
        this.stringVersion = stringVersion;
        initList();
    }
}
