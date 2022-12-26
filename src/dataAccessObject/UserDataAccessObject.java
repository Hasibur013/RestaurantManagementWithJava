package dataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.User;

public class UserDataAccessObject {

    public static void save(User user) {
        String query = "insert into user(name,email,mobilenumber,address,password,securityQuestion,answer,status) values('" + user.getName() + "','" + user.getEmail() + "','" + user.getMobileNumber() + "','" + user.getAddress() + "','" + user.getPassword() + "','" + user.getSecurityQuestion() + "','" + user.getAnswer() + "','false')";
        DatabaseOperation.setDataOrDelete(query, "Register Successfully! wait for admin approval");
    }

    public static User login(String email, String password) {

        User user = null;

        try {

            ResultSet rs = DatabaseOperation.getData("select *from user where email='" + email + "' and password='" + password + "'");
            while (rs.next()) {

                user = new User();
                user.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return user;
    }

    public static User getSecurityQuestion(String email) {
        User user = null;
        try {

            ResultSet rs = DatabaseOperation.getData("select *from user where email = '" + email + "'");

            while (rs.next()) {
                user = new User();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
        String query = "update user set password='" + newPassword + "' where email='" + email + "'";
        DatabaseOperation.setDataOrDelete(query, "Password changed successfully");
    }

    public static ArrayList<User> getAllRecords(String email) {
        ArrayList<User> arrayList = new ArrayList<>();

        try {
            ResultSet rs = DatabaseOperation.getData("select *from user where email like '%" + email + "%'");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobilenumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;
    }

    public static void changeStatus(String email, String status) {
        String query = "update user set status ='" + status + "' where email='" + email + "'";
        DatabaseOperation.setDataOrDelete(query, "User status updated successfully");
    }

    public static void changePassword(String email, String oldPassword, String newPassword) {
        try {
            ResultSet rs = DatabaseOperation.getData("select *from user where email='" + email + "' and password='" + oldPassword + "'");
            if (rs.next()) {
                update(email, newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Old Password is wrong ! please try again");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void changeSecurityQuestion(String email, String password, String sequrityQuestion, String answer) {
        try {
            ResultSet rs = DatabaseOperation.getData("select *from user where email='" + email + "' and password='" + password + "'");
            if (rs.next()) {
                updateSQ(email, sequrityQuestion, answer);
            } else {
                JOptionPane.showMessageDialog(null, "Password is wrong ! please try again");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateSQ(String email, String securityQuestion, String answer) {
        
        String query = "update user set securityQuestion='"+securityQuestion+"',answer='"+answer+"' where email='"+email+"'";
        DatabaseOperation.setDataOrDelete(query, "Sequrity question changed successfully");
    }
}
