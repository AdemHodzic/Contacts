package com.adem.Models;

import com.adem.Entities.User;
import com.adem.Entities.UserProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO {

    private Connection conn = ConnectionManager.getInstance().getConnection();
    public UserDAO(){}

    public void addUser(UserProperties user) {
        String query = "INSERT INTO users(name, password, phone, email, contacts) VALUES(?,?,?,?,?)";
        try(
            PreparedStatement stmt = conn.prepareStatement(query);
            ){
            stmt.setString(1, user.getName());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getPhone());
            stmt.setString(4,user.getEmail());
            stmt.setString(5,"1,");

            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateUser(UserProperties user) {
        String query = "UPDATE users SET name=?, password=?, phone=?, email=?, contacts=? WHERE id=?";
        try(
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.setString(1, user.getName());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getPhone());
            stmt.setString(4,user.getEmail());
            stmt.setString(5,user.getContacts());
            stmt.setInt(6,user.getId());

            if(stmt.executeUpdate()==1)
                System.out.println("Update succeful");
            else
                System.out.println("Something went wrong");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteUser(UserProperties user) {
        String query = "DELETE FROM users WHERE name=?";
        try(
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.setString(1, user.getName());

            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public UserProperties getUser(UserProperties user) {
        String query = "SELECT * FROM users WHERE name=?";
        UserProperties temp = new UserProperties();
        try(
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.setString(1, user.getName());

            ResultSet rs = stmt.executeQuery();
            rs.next();
            temp.setId(rs.getInt("id"));
            temp.setName(rs.getString("name"));
            temp.setPassword(rs.getString("password"));
            temp.setPhone(rs.getString("phone"));
            temp.setEmail(rs.getString("email"));
            temp.setContacts(rs.getString("contacts"));

        }catch(NullPointerException en){
            return new UserProperties();
        }catch(Exception e){
            e.printStackTrace();
        }

        return temp;
    }

    @Override
    public UserProperties getUser(int id) {
        String query = "SELECT * FROM users WHERE id=?";
        UserProperties temp = new UserProperties();
        try(
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            boolean next = rs.next();
            if(next) {
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                temp.setPassword(rs.getString("password"));
                temp.setPhone(rs.getString("phone"));
                temp.setEmail(rs.getString("email"));
                temp.setContacts(rs.getString("contacts"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return temp;
    }

    public List<UserProperties> getAllUsers() {
        List<UserProperties> list = new ArrayList<>();
        String query = "SELECT * FROM users";
        try(
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){
            while(rs.next()) {
                UserProperties temp = new UserProperties();
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                temp.setPassword(rs.getString("password"));
                temp.setPhone(rs.getString("phone"));
                temp.setEmail(rs.getString("email"));
                temp.setContacts(rs.getString("contacts"));
                list.add(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
