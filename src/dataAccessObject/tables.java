package dataAccessObject;

import javax.swing.JOptionPane;

public class tables {

    public static void main(String[] args) {
        try {
            // create user table
            String userTable = "create table user(id int AUTO_INCREMENT primary key,name varchar(200),email varchar(200),mobilenumber varchar(10),address varchar(200),password varchar(200),securityQuestion varchar(200),answer varchar(200),status varchar(200),UNIQUE (email))";
            String adminDetails = "insert into user(name,email,mobilenumber,address,password,securityQuestion,answer,status) values('admin','admin@gmail.com','0163175388','Bangladesh','admin','What is your name?','Hasib','true')";
            
            // create category table
            String categoryTable= "create table category(id int AUTO_INCREMENT primary key,name varchar(200))";
            
            // create product able
            String productTable ="create table product(id int AUTO_INCREMENT primary key,name varchar(200),category varchar(200),price varchar(200))";
            
            // create bill table
             String billTable ="create table bill(id int primary key,name varchar(200),mobileNumber varchar(200),email varchar(200),date varchar(50),total varchar(200),createdBy varchar(200))";
            
            DatabaseOperation.setDataOrDelete(userTable, "User Table Created Successfully");
            DatabaseOperation.setDataOrDelete(adminDetails, "Admin Created Successfully");
            DatabaseOperation.setDataOrDelete(categoryTable, "Category Table Created Successfully");
            DatabaseOperation.setDataOrDelete(productTable, "Product Table Created Successfully");
            DatabaseOperation.setDataOrDelete(billTable, "Bill Table Created Successfully");
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
