package utilityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import valueBean.CartDetails;
import valueBean.OrderDetails;

public class OrderDetailsDB {
	public int bulkInsertOD(int orderId, ArrayList<CartDetails> cart) {
		int count=0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "INSERT INTO orderDetails(orderId, productId, quantity) VALUES";
	    		for(int i = 0; i < cart.size() ; i++) {
		    		int productId = cart.get(i).getProductId();
		    		int quantity = cart.get(i).getQuantity();
		    		sqlStr += "("+orderId+","+productId+","+quantity+")";
		    		if((i+1) != cart.size()) {
		    			sqlStr += ",";
		    		}
	    		}
	    		System.out.println(sqlStr);
	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		count = pstmt.executeUpdate();
	          conn.close();	          
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return count;
	}
	
	public ArrayList<OrderDetails> getOrderDetailsById(int orderId){
		ArrayList<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		OrderDetails orderDT;
		try {
	          Class.forName("com.mysql.jdbc.Driver");
		      String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT orderDetailsId, o.productId, quantity, productName FROM orderDetails o \r\n" + 
	          		"INNER JOIN product p ON o.productId = p.productId\r\n" + 
	          		"WHERE orderId = ?";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, orderId);
	    		ResultSet rs = pstmt.executeQuery();	          
	    		while(rs.next()) {
	    			orderDT = new OrderDetails();
	    			orderDT.setOrderDetailsId(rs.getInt("orderDetailsId"));
	    			orderDT.setProductId(rs.getInt("productId"));
	    			orderDT.setQuantity(rs.getInt("quantity"));
	    			orderDT.setProductName(rs.getString("productName"));
	    			orderDetails.add(orderDT);
	    		}
	    		conn.close();	
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return orderDetails;
	}
}
