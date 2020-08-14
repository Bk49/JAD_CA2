package utilityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import valueBean.Order;

public class OrderDB {
	// Insert a new order after the user check out
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
	
	// Get the id for the order to make payment
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
	
	// Set status to "Paid" after the user insert payment details
	public int pay(int orderId) {
		int count = 0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "UPDATE `order` SET status = 'Paid' WHERE orderId = ?";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, orderId);
	    		count = pstmt.executeUpdate();	          
	    		
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return count;
	}
	
	// Get the orders from database
	public ArrayList<Order> getOrders(int customerId) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Order order;
		try {
	          Class.forName("com.mysql.jdbc.Driver");
		      String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT orderId, orderDate, status, totalPrice, discountId from `order` WHERE customerId = ? ORDER BY orderId DESC";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, customerId);
	    		ResultSet rs = pstmt.executeQuery();	          
	    		while(rs.next()) {
	    			order = new Order();
	    			order.setOrderId(rs.getInt("orderId"));
	    			order.setOrderDate(rs.getString("orderDate"));
	    			order.setStatus(rs.getString("status"));
	    			order.setTotalPrice(rs.getDouble("totalPrice"));
	    			order.setDiscountId(rs.getInt("discountId"));
	    			order.setCustomerId(customerId);
	    			orders.add(order);
	    		}
	    		
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return orders;
	}
	
	// Get totalPrice by orderId
	public double getPriceById(int orderId) {
		double totalPrice = 0;
		try {
	          Class.forName("com.mysql.jdbc.Driver");
		      String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT totalPrice FROM `order` WHERE orderId = ?";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, orderId);
	    		ResultSet rs = pstmt.executeQuery();	          
	    		if(rs.next()) {
	    			totalPrice = rs.getDouble("totalPrice");
	    		}
	    		
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return totalPrice;
	}
	
	// Get the orders from database
		public ArrayList<Order> getOrdersbyDate(int pg) {
			ArrayList<Order> orders = new ArrayList<Order>();
			Order order;
			int startRow = pg*10-10;

			try {
		          Class.forName("com.mysql.jdbc.Driver");
			      String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT * from `order` where status = 'paid' order by orderDate asc LIMIT ?,10";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		pstmt.setInt(1, startRow);

		    		ResultSet rs = pstmt.executeQuery();	          
		    		while(rs.next()) {
		    			order = new Order();
		    			order.setOrderId(rs.getInt("orderId"));
		    			order.setOrderDate(rs.getString("orderDate"));
		    			order.setStatus(rs.getString("status"));
		    			order.setTotalPrice(rs.getDouble("totalPrice"));
		    			order.setDiscountId(rs.getInt("discountId"));
		    			order.setCustomerId(rs.getInt("customerId"));
		    			orders.add(order);
		    		}
		    		
		    		conn.close();
		    		
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return orders;
		}
		
		// Get the count of orders from database by date
		public double getOrdersbyDateCount() {
			double count = 0;

			try {
		          Class.forName("com.mysql.jdbc.Driver");
			      String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT COUNT(*) as count  from `order` where status = 'paid' order by orderDate asc";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		ResultSet rs = pstmt.executeQuery();	          
		    		while(rs.next()) {
			        	  count = (double)rs.getInt("count");

		    		}
		    		
		    		conn.close();
		    		
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return count;
		}
}
