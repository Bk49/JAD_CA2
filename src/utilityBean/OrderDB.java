package utilityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDB {
	public int insertOrder(int customerId, double totalPrice) {
		int count = 0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "INSERT INTO `order`(customerId, totalPrice, orderDate) VALUES(?,?, curdate());";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, customerId);
	    		pstmt.setDouble(2, totalPrice);
	    		count = pstmt.executeUpdate();	          
        
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return count;
	}
	
	public int getOrderId(int customerId, double totalPrice) {
		int orderId=0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT orderId from `order` WHERE customerId = ? AND totalPrice = ? ORDER BY orderId DESC LIMIT 1;";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, customerId);
	    		pstmt.setDouble(2, totalPrice);
	    		ResultSet rs = pstmt.executeQuery();	          
	    		if(rs.next()) {
	    			orderId = rs.getInt("orderId");
	    		}
	    		
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return orderId;
	}
}
