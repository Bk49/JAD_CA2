package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.UserDetailsDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class GoUserTable
 */
@WebServlet("/GoUserTable")
public class GoUserTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoUserTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pg;
		pg = (request.getParameter("pg") != null) ? Integer.parseInt(request.getParameter("pg")) : 1;
		request.setAttribute("pg", pg);
		
		// Get all the users limit 10
		try {
			UserDetailsDB userDB = new UserDetailsDB();
			ArrayList<UserDetails> users = new ArrayList<UserDetails>();
			
			users = userDB.getUsersLimit(pg);
			request.setAttribute("users", users);
			
			}catch(Exception e){
				System.out.print(e);
			}		
	
		// Get the number of users
		try {
			UserDetailsDB userDB = new UserDetailsDB();
			double count = 0;
			count = userDB.getCount();
			
			request.setAttribute("userCount", count);
		}catch(Exception e) {
			System.out.print(e);
		}
		
		// Forward to Home Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/userTable.jsp");
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
