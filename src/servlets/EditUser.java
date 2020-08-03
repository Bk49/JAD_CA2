package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.UserDetailsDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the user details
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String pfp = request.getParameter("pfp");
		String address = request.getParameter("address");
		String phoneNo = request.getParameter("phoneNo");
		
		// Check for values of pfp and phoneNo
		if(pfp == null || pfp.equals("")) pfp = "/images/u101.png";
		if(phoneNo == null || phoneNo.equals("")) phoneNo = "0";
		
		// Setting all the attributes into a value bean
		UserDetails user = new UserDetails();
		user.setUserId(userId);
		user.setName(name);
		user.setPwd(pwd);
		user.setEmail(email);
		user.setRole(role);
		user.setPfp(pfp);
		user.setAddress(address);
		user.setPhoneNo(phoneNo);

		// Accessing the database to update product
		try {
			UserDetailsDB userDB = new UserDetailsDB();
			int count = userDB.updateUserAdmin(user);
			System.out.println(count + " number of lines has been updated!");
			
			}catch(Exception e){
				System.out.print(e);
			}
		
		// Send Redirect to userTable.jsp
		response.sendRedirect("./GoUserTable");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
