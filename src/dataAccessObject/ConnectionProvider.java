
package dataAccessObject;
import java.sql.*;

public class ConnectionProvider {
    public static Connection getCon(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/rms?useSSL=false","root","");
            return con;
            
        } catch (Exception e) {
        }
        return null;
    }
}
