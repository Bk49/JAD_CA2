package utilityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import valueBean.PaymentDetails;

public class PaymentDB {
	public int insertPaymentDt(PaymentDetails payment) {
		int count = 0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "INSERT INTO `payment`(ccType, ccName, ccExpDate, ccNum, ccCvv, customerId) VALUES(?,?,?,?,?,?);";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setString(1, payment.getCcType());
	    		pstmt.setString(2, payment.getCcName());
	    		pstmt.setString(3, payment.getCcExpDate());
	    		pstmt.setString(4, payment.getCcNum());
	    		pstmt.setInt(5, payment.getCcCvv());
	    		pstmt.setInt(6, payment.getCustomerId());

	    		count = pstmt.executeUpdate();	          
        
	    		conn.close();
	    		
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return count;
	}
	
	public ArrayList<PaymentDetails> getPaymentDetails(int customerId){
		ArrayList<PaymentDetails> payments = new ArrayList<PaymentDetails>();
		PaymentDetails payment;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";
	
	         Connection conn = DriverManager.getConnection(connURL); 
	         String sqlStr = "SELECT * FROM `payment` WHERE customerId = ?";
	         
	         PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	         pstmt.setInt(1, customerId);
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	 payment = new PaymentDetails();
	        	 payment.setCcType(rs.getString("ccType"));
	        	 payment.setCcName(rs.getString("ccName"));
	        	 payment.setCcExpDate(rs.getString("ccExpDate"));
	        	 payment.setCcNum(rs.getString("ccNum"));
	        	 payment.setCcCvv(rs.getInt("ccCvv"));
	        	 payments.add(payment);
	         }
		}catch(Exception e) {
			System.out.println(e);
		}
		return payments;
	
	}
}
