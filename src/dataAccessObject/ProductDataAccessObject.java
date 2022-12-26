package dataAccessObject;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Product;
import java.sql.*;

public class ProductDataAccessObject {

    public static void add(Product product) {
        String query = "insert into product(name,category,price) values('" + product.getName() + "','" + product.getCategory() + "','" + product.getPrice() + "')";
        DatabaseOperation.setDataOrDelete(query, "Product added successfully");
    }

    public static ArrayList<Product> getAllRecords() {

        ArrayList<Product> list = new ArrayList<>();

        try {
            ResultSet rs = DatabaseOperation.getData("select *from product");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getString("price"));

                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return list;
    }

    public static void update(Product product) {
        String query = "update product set name='" + product.getName() + "',category='" + product.getCategory() + "',price='" + product.getPrice() + "' where id='" + product.getId() + "'";
        DatabaseOperation.setDataOrDelete(query, "Product updated successfully");
    }

    public static void delete(String id) {
        String query = "delete from product where id='" + id + "'";
        DatabaseOperation.setDataOrDelete(query, "product deleted successfully");
    }

    public static ArrayList<Product> getAllRecordsBycategory(String category) {

        ArrayList<Product> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseOperation.getData("select *from product where category='" + category + "'");
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return list;
    }

    public static ArrayList<Product> filterProductByName(String name, String category) {

        ArrayList<Product> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseOperation.getData("select *from product where name like '%" + name + "%' and category='" + category + "'");
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return list;
    }

    public static Product getProductByName(String name) {
        Product product = new Product();

        try {
            ResultSet rs = DatabaseOperation.getData("select *from product where name='" + name + "'");
            while (rs.next()) {
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, name);
        }

        return product;
    }
}
