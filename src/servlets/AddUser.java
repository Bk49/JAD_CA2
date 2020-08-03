package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.UserDetailsDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String email = request.getParameter("email");
			String role = request.getParameter("role");
			String pfp = request.getParameter("pfp");
			String address = request.getParameter("address");
			String phoneNo = request.getParameter("phoneNo");			
			
			if(pfp.equals("") || pfp == null) pfp = "/images/u101.png";
			if(phoneNo.equals("") || pfp == null) phoneNo = "0";
			
			UserDetailsDB userDB = new UserDetailsDB();
			UserDetails user = new UserDetails();	
			
			user.setName(name);
			user.setPwd(pwd);
			user.setEmail(email);
			user.setRole(role);
			user.setPfp(pfp);
			user.setAddress(address);
			user.setPhoneNo(phoneNo);
			
			int count = userDB.insertUser(user);
			
			System.out.print(count+" row affected");
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
