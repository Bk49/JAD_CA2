package utilityBean;
import valueBean.UserDetails;
import java.sql.*;

public class UserDetailsDB {
	// Get User Details using email and pwd
	public UserDetails getUserDetails(String email, String pwd) {
        UserDetails user = new UserDetails();
		try {
	           Class.forName("com.mysql.jdbc.Driver");
	          //String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

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
	
	// Insert new user using UserDetails.java
	public int insertUser(UserDetails user) {
		int count = 0;
		try {

	         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
			
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
	         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
			
			
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

	         // String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	           String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";
			
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
}
