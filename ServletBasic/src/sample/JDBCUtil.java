package sample;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class JDBCUtil {

    public static void main(String[] args) {
    	 insert();
        //select();
    }
    
    public static void select() {
        String selQuery = "SELECT * FROM Employee";

        try {
        	// 1) Load the driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            // 2) Create Connection Object
            Connection conn = DriverManager.getConnection("jdbc:odbc:EmployeeDB","ThangLT2","");
            
            // 3) Create Statement            
            Statement stmt = conn.createStatement();
            
            // 4) Execute the query
            ResultSet rs = stmt.executeQuery(selQuery);
            
            // 5) Processing the resultset.
            String fullName;
            int levelCd;
            int recIdx = 0;
            while (rs.next()) {
                fullName = rs.getString("FullName");
                levelCd = rs.getInt("LevelCd");
                System.out.println(recIdx + ":" + fullName + "," + levelCd);
                recIdx++;
            }
        } catch (java.lang.ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        } catch (java.sql.SQLException sqlex) {
            sqlex.printStackTrace();
        }
} // end of select
    public static void insert() {
        String insQuery = "INSERT INTO Employee(FullName, Sex, Address, Birthday, LevelCd) VALUES (?, ?, ?, ?, ?)";
        
        try {
            // 1) Load the driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            // 2) Create Connection Object
            Connection conn = DriverManager.getConnection("jdbc:odbc:EmployeeDB","ThangLT2", "");
            
            // 3.1) Create PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(insQuery);
            
            // 3.2) Set parameters for the PreparedStatement
            pstmt.setString(1, "Le Thanh Hai");
            pstmt.setString(2, "Male");
            pstmt.setString(3, "D1");
            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setInt(5, 1);
         // 4) Execute the query
            int ret = pstmt.executeUpdate();

            // 5) Report the result.
            System.out.println("Inserted records:" + ret);
            
        } catch (java.lang.ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        } catch (java.sql.SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
}
