package dataAccessObject;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;

/**
 *
 * @author hasib
 */
public class BillDataAccessObject {

    public static String getId() {
        int id = 1;

        try {
            ResultSet rs = DatabaseOperation.getData("select max(id) from bill");
            if (rs.next()) {
                id = rs.getInt(1);
                id = id + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return String.valueOf(id);
    }

    public static void save(Bill bill) {
        String query = "insert into bill(id,name,mobileNumber,email,date,total,createdBy) values('" + bill.getId() + "','" + bill.getName() + "','" + bill.getMobileNumber() + "','" + bill.getEmail() + "','" + bill.getDate() + "','" + bill.getTotal() + "','" + bill.getCreatedBy() + "')";
        DatabaseOperation.setDataOrDelete(query, "Bill details added successfully");
    }

    public static ArrayList<Bill> getAllRecordsByIncrement(String date) {
        ArrayList<Bill> list = new ArrayList<>();

        try {
            ResultSet rs = DatabaseOperation.getData("select *from bill where date like '%" + date + "%'");
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setMobileNumber(rs.getString("mobileNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("createdBy"));

                list.add(bill);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<Bill> getAllRecordsByDescending(String date) {
        ArrayList<Bill> list = new ArrayList<>();

        try {
            ResultSet rs = DatabaseOperation.getData("select *from bill where date like '%" + date + "%' order By id DESC");
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setMobileNumber(rs.getString("mobileNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("createdBy"));

                list.add(bill);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
}
