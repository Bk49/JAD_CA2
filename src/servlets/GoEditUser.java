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
 * Servlet implementation class GoEditUser
 */
@WebServlet("/GoEditUser")
public class GoEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoEditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		try {
			UserDetailsDB userDB = new UserDetailsDB();
			UserDetails user =  userDB.getUserDetailsById(userId);
			
			request.setAttribute("user", user);
		}catch(Exception e) {
			System.out.print(e);
		}	
		
		// Forward to editUser Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/editUser.jsp");
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
