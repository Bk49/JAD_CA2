package utilityBean;

import java.sql.*;
import java.util.ArrayList;

import valueBean.DiscountDetails;

public class DiscountDetailsDB {
	// Get Discount Details using discountCode
		public DiscountDetails getDiscountDetails(int discountId) {
			DiscountDetails discount = new DiscountDetails();
			try {
		           Class.forName("com.mysql.jdbc.Driver");
		         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT * FROM discount WHERE discountId = ?";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		pstmt.setInt(1, discountId);
		    		ResultSet rs = pstmt.executeQuery();	          
		          if (rs.next()) {
		              discount.setDiscountId(rs.getInt("discountId"));
		              discount.setDiscountCode(rs.getString("discountCode"));
		              discount.setDiscountValue(rs.getDouble("discountValue"));
		              discount.setDiscountType(rs.getString("discountType"));
		              discount.setUsageLimit(rs.getInt("usageLimit"));
		              discount.setUsageCount(rs.getInt("usageCount"));
		          }	        
		          conn.close();
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return discount;
		}
		
		// Insert new Discount using DiscountDetails.java
		public int insertDiscount(DiscountDetails discount) {
			int count = 0;
			try {
		           Class.forName("com.mysql.jdbc.Driver");

			         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
		           
		           
		           Connection conn = DriverManager.getConnection(connURL); 
		         
		         String insertStr = "INSERT INTO discount(discountCode, discountValue, discountType, usageLimit) VALUES (?,?,?,?) ";
			 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
		         pstmt.setString(1, discount.getDiscountCode());
		         pstmt.setDouble(2, discount.getDiscountValue());
		         pstmt.setString(3, discount.getDiscountType());
		         pstmt.setInt(4, discount.getUsageLimit());
		         count = pstmt.executeUpdate();
		         conn.close(); 

		    } catch (Exception e) {
		       System.out.print(e);
		    }
			return count;
		}
		
		// Delete Discount using discountId
		public int deleteDiscount(int discountId) {
			int count = 0;
			try {

		         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
				
				
				Connection conn = DriverManager.getConnection(connURL); 
		         
		         String deleteStr = "DELETE FROM discount WHERE discountId = ?";
		         PreparedStatement pstmt = conn.prepareStatement(deleteStr);
		         pstmt.setInt(1, discountId);
		         count = pstmt.executeUpdate();
		         conn.close(); 

		    } catch (Exception e) {
		       System.out.print(e);
		    }
			return count;
		}
		
		// Update Discount using DiscountDetails.java
		public int updateDiscount(DiscountDetails discount) {
			int count = 0;
			try {
		           Class.forName("com.mysql.jdbc.Driver");

			         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
		           
		           
		           Connection conn = DriverManager.getConnection(connURL); 
		         
		         String insertStr = "UPDATE discount SET discountCode = ?, discountValue = ?, discountType = ?, usageLimit = ?, usageCount = ? WHERE discountId = ?";
			 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
		         pstmt.setString(1, discount.getDiscountCode());
		         pstmt.setDouble(2, discount.getDiscountValue());
		         pstmt.setString(3, discount.getDiscountType());
		         pstmt.setInt(4, discount.getUsageLimit());
		         pstmt.setInt(5, discount.getUsageCount());
		         pstmt.setInt(6, discount.getDiscountId());

		         System.out.println(pstmt);
		         count = pstmt.executeUpdate();
		         conn.close(); 

		    } catch (Exception e) {
		       System.out.print("error "+ e);
		    }
			return count;
		}
		
		// Get 10 discounts according to its page
		public ArrayList<DiscountDetails> getDiscountsLimit(int pg) {
			DiscountDetails discount;
			ArrayList<DiscountDetails> discounts = new ArrayList<DiscountDetails>();
			
			int startRow = pg*10-10;
			try {
		           Class.forName("com.mysql.jdbc.Driver");
		         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT discountId, discountCode, discountValue, discountType, usageLimit, usageCount FROM discount LIMIT ? ,10";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		pstmt.setInt(1, startRow);
		    		ResultSet rs = pstmt.executeQuery();	          
		          while (rs.next()) {
		        	  discount = new DiscountDetails();
		              discount.setDiscountId(rs.getInt("discountId"));
		              discount.setDiscountCode(rs.getString("discountCode"));
		              discount.setDiscountValue(rs.getDouble("discountValue"));
		              discount.setDiscountType(rs.getString("discountType"));
		              discount.setUsageLimit(rs.getInt("usageLimit"));
		              discount.setUsageCount(rs.getInt("usageCount"));
		              discounts.add(discount);
		          }	        
		          conn.close();
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			System.out.println("Size of discounts in DiscountDetailsDB "+discounts.size());
			return discounts;
		}
		
		// Get count of discounts
		public double getDiscountCount() {
			double count = 0;
			try {
		           Class.forName("com.mysql.jdbc.Driver");
		         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
		           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT COUNT(*) count FROM discount";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		ResultSet rs = pstmt.executeQuery();	          
		          if (rs.next()) {
		        	  count = (double)rs.getInt("count");
		          }	        
		          conn.close();
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return count;
		}
		
}
