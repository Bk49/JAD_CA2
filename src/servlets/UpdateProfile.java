package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import utilityBean.UserDetailsDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Getting the account details
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pfp = request.getParameter("pfp");
		String phoneNo = request.getParameter("phoneNo");
		
		// Setting all the attributes into a value bean
		UserDetails user = new UserDetails();
		user.setUserId(userId);
		user.setName(name);
		user.setEmail(email);
		user.setPfp(pfp);
		user.setPhoneNo(phoneNo);
		
		// Accessing the database to update the user details
		try {
			UserDetailsDB userDB = new UserDetailsDB();
			int count = userDB.updateUser(user);
			System.out.println(count + " number of lines has been updated!");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		// Reset the user attribute
		HttpSession session = request.getSession();
		user = (UserDetails)session.getAttribute("user");
		user.setName(name);
		user.setEmail(email);
		user.setPfp(pfp);
		user.setPhoneNo(phoneNo);
		session.setAttribute("user", user);
		
		// Forwards to profilePage.jsp
		RequestDispatcher rd = request.getRequestDispatcher("GoHome");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
