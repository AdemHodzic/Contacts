package com.adem.Models;


import com.adem.Entities.User;
import com.adem.Entities.UserProperties;

import java.util.List;

public interface DAO {

    public void addUser(UserProperties user);
    public void updateUser(UserProperties user);
    public void deleteUser(UserProperties user);
    public UserProperties getUser(UserProperties user);
    public UserProperties getUser(int id);
    public List<UserProperties> getAllUsers();

}
