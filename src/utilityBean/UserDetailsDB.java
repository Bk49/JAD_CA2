package utilityBean;
import valueBean.UserDetails;
import java.util.ArrayList;
import java.sql.*;

public class UserDetailsDB {
	// Get User Details using email and pwd
	public UserDetails getUserDetails(String email, String pwd) {
        UserDetails user = new UserDetails();
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT userId, name, pfp, address, phoneNo, role FROM user WHERE email = ? AND pwd = ?";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setString(1,email);
	    		pstmt.setString(2,pwd);
	    		ResultSet rs = pstmt.executeQuery();	          
	          if (rs.next()) {
	        	  System.out.print(rs.getString("name")+"this is from UserDetailsDB");
	              user.setName(rs.getString("name"));
	              user.setPwd(pwd);
	              user.setEmail(email);
	              user.setRole(rs.getString("role"));
	              user.setPfp(rs.getString("pfp"));
	              user.setAddress(rs.getString("address"));
	              user.setPhoneNo(rs.getString("phoneNo"));
	              user.setUserId(rs.getInt("userId"));
	          }
	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return user;
	}
	
	// Get User Details using userId
		public UserDetails getUserDetailsById(int id) {
	        UserDetails user = new UserDetails();
			try {
		           Class.forName("com.mysql.jdbc.Driver");
			         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "SELECT name, pwd, email, pfp, address, phoneNo, role FROM user WHERE userId = ?";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		pstmt.setInt(1, id);
		    		ResultSet rs = pstmt.executeQuery();	          
		          if (rs.next()) {
		              user.setName(rs.getString("name"));
		              user.setPwd(rs.getString("pwd"));
		              user.setEmail(rs.getString("email"));
		              user.setRole(rs.getString("role"));
		              user.setPfp(rs.getString("pfp"));
		              user.setAddress(rs.getString("address"));
		              user.setPhoneNo(rs.getString("phoneNo"));
		              user.setUserId(id);
		          }		        
		          conn.close();
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return user;
		}
	
	// Insert new user using UserDetails.java
	public int insertUser(UserDetails user) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";
			
			Connection conn = DriverManager.getConnection(connURL); 
	         
	         String insertStr = "INSERT INTO user(name, pwd, email, role, pfp, address, phoneNo) VALUES (?,?,?,?,?,?,?) ";
		 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
	         pstmt.setString(1, user.getName());
	         pstmt.setString(2, user.getPwd());
	         pstmt.setString(3, user.getEmail());
	         pstmt.setString(4, user.getRole());
	         pstmt.setString(5, user.getPfp());
	         pstmt.setString(6, user.getAddress());
	         pstmt.setString(7, user.getPhoneNo());

	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print("error");
	    }
		return count;
	}
	
	// Delete user using userId
	public int deleteUser(int userId) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";
			
			Connection conn = DriverManager.getConnection(connURL); 
	         
	         String deleteStr = "DELETE FROM user WHERE userId = ?";
	         PreparedStatement pstmt = conn.prepareStatement(deleteStr);
	         pstmt.setInt(1, userId);
	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print("error");
	    }
		return count;
	}
	
	// Update user using UserDetails.java
	public int updateUser(UserDetails user) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

			Connection conn = DriverManager.getConnection(connURL); 
	         
	         String insertStr = "UPDATE user SET name = ?, email = ?, pfp = ?, phoneNo = ? WHERE userId = ?";
		 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
		 	pstmt.setString(1, user.getName());
		 	pstmt.setString(2, user.getEmail());
		 	pstmt.setString(3, user.getPfp());
		 	pstmt.setString(4, user.getPhoneNo());
	         pstmt.setInt(5, user.getUserId());

	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print("error");
	    }
		return count;
	}
	
	// Update user using UserDetails.java for Admin use
	public int updateUserAdmin(UserDetails user) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

			Connection conn = DriverManager.getConnection(connURL); 
	         
	         String insertStr = "UPDATE user SET name = ?, pwd = ?, email = ?, role = ?, pfp = ?, address = ?, phoneNo = ? WHERE userId = ?";
		 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
		 	pstmt.setString(1, user.getName());
		 	pstmt.setString(2, user.getPwd());
		 	pstmt.setString(3, user.getEmail());
		 	pstmt.setString(4, user.getRole());
		 	pstmt.setString(5, user.getPfp());
		 	pstmt.setString(6, user.getAddress());
		 	pstmt.setString(7, user.getPhoneNo());
	         pstmt.setInt(8, user.getUserId());

	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print(e);
	    }
		return count;
	}
	
	// Get User Details using email and pwd
	public ArrayList<UserDetails> getUsersLimit(int pg) {
        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
        UserDetails user;
        int startRow = pg*10-10;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT * FROM user LIMIT ?,10";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, startRow);
	    		ResultSet rs = pstmt.executeQuery();	          
	          while (rs.next()) {
	        	  user = new UserDetails();
	        	  user.setName(rs.getString("name"));
	              user.setPwd(rs.getString("pwd"));
	              user.setEmail(rs.getString("email"));
	              user.setRole(rs.getString("role"));
	              user.setPfp(rs.getString("pfp"));
	              user.setAddress(rs.getString("address"));
	              user.setPhoneNo(rs.getString("phoneNo"));
	              user.setUserId(rs.getInt("userId"));
	              users.add(user);
	          }
	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return users;
	}
	
	// Get the number of users
	public int getCount() {
		int count = 0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT COUNT(*) count FROM user";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		ResultSet rs = pstmt.executeQuery();	          
	          if (rs.next()) {
	        	  count = rs.getInt("count");
	          }
	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return count;
	}
	
	//get users based by address
	public ArrayList<UserDetails> getUserByAddress(int pg) {
        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
        UserDetails user;
        int startRow = pg*10-10;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
		         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT * FROM heroku_ec924e2e031aaa6.user order by address desc LIMIT ?,10";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, startRow);
	    		ResultSet rs = pstmt.executeQuery();	          
	          while (rs.next()) {
	        	  user = new UserDetails();
	        	  user.setName(rs.getString("name"));
	              user.setPwd(rs.getString("pwd"));
	              user.setEmail(rs.getString("email"));
	              user.setRole(rs.getString("role"));
	              user.setPfp(rs.getString("pfp"));
	              user.setAddress(rs.getString("address"));
	              user.setPhoneNo(rs.getString("phoneNo"));
	              user.setUserId(rs.getInt("userId"));
	              users.add(user);
	          }
	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return users;
	}
	
	
	//get users based by address
		public ArrayList<UserDetails> getTop10Customers() {
	        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
	        UserDetails user;
			try {
		           Class.forName("com.mysql.jdbc.Driver");
			         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

		          Connection conn = DriverManager.getConnection(connURL); 
		          String sqlStr = "Select p.customerId, u.*, CAST(Round(SUM(o.totalPrice)/2,2) as CHAR) as 'Total Purchased' From payment p inner join heroku_ec924e2e031aaa6.order o on o.customerId = p.customerId inner join heroku_ec924e2e031aaa6.user u on u.userId = o.customerId where o.status = 'paid' Group by p.customerId order by 'Total Purchased' desc limit 10";

		    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		    		ResultSet rs = pstmt.executeQuery();	          
		          while (rs.next()) {
		        	  user = new UserDetails();
		        	  user.setName(rs.getString("u.name"));
		        	  user.setEmail(rs.getString("u.email"));
		        	  user.setPhoneNo(rs.getString("u.phoneNo"));
		              user.setPwd(rs.getString("Total Purchased"));
		              user.setUserId(rs.getInt("p.customerId"));
		              users.add(user);
		          }
		        
		          conn.close();
		     } catch (Exception e) {
		        System.err.println("Error :" + e);
		     }
			return users;
		}
		
		//get users based on product purchased
				public ArrayList<UserDetails> getCustomerByProduct(String Product, int pg) {
			        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
			        UserDetails user;
			        int startRow = pg*10-10;

					try {
				           Class.forName("com.mysql.jdbc.Driver");
					         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

				          Connection conn = DriverManager.getConnection(connURL); 
				          String sqlStr = "select u.*, o.customerId,(od.quantity) as 'Quantity Sold' From product p inner join orderdetails od on od.productId = p.productId inner join heroku_ec924e2e031aaa6.order o on o.orderId = od.orderId inner join heroku_ec924e2e031aaa6.user u on u.userId = o.customerId Where o.status = 'paid' and p.productName = ? GROUP BY p.productName order by od.quantity desc LIMIT ? , 10";

				    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
				    		pstmt.setString(1, Product);
				    		pstmt.setInt(2, startRow);

				    		ResultSet rs = pstmt.executeQuery();	          
				          while (rs.next()) {
				        	  user = new UserDetails();
				        	  user.setName(rs.getString("u.name"));
				        	  user.setEmail(rs.getString("u.email"));
				        	  user.setPhoneNo(rs.getString("u.phoneNo"));
				              user.setPwd(rs.getString("Quantity Sold"));
				              user.setUserId(rs.getInt("o.customerId"));
				              users.add(user);
				          }
				        
				          conn.close();
				     } catch (Exception e) {
				        System.err.println("Error :" + e);
				     }
					return users;
				}
				
				//get users based on product purchased count
				public double getCustomerByProductCount(String Product) {
			        double count = 0;

					try {
				           Class.forName("com.mysql.jdbc.Driver");
					         String connURL = "jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_ec924e2e031aaa6?user=bd75cdad57c09f&password=75b47259&serverTimezone=UTC";

				          Connection conn = DriverManager.getConnection(connURL); 
				          String sqlStr = "select COUNT(*) count From product p inner join orderdetails od on od.productId = p.productId inner join heroku_ec924e2e031aaa6.order o on o.orderId = od.orderId inner join heroku_ec924e2e031aaa6.user u on u.userId = o.customerId Where o.status = 'paid' and p.productName = ? GROUP BY p.productName order by od.quantity desc";

				    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
				    		pstmt.setString(1, Product);

				    		ResultSet rs = pstmt.executeQuery();	          
				          while (rs.next()) {
				        	  count = (double)rs.getInt("count");

				          }
				        
				          conn.close();
				     } catch (Exception e) {
				        System.err.println("Error :" + e);
				     }
					return count;
				}
}
