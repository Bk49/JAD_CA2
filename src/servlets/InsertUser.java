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
 * Servlet implementation class InsertUser
 */
@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
		String name = request.getParameter("fname")+" "+request.getParameter("lname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		if(pwd.equals(pwdc)) {
			RequestDispatcher rd = request.getRequestDispatcher("CA1/CustomerRegistration.jsp?errorCode=invalidPw");
			rd.forward(request, response);
		}
		String address = request.getParameter("address");
		String phoneNoStr = request.getParameter("phoneNo");
		int phoneNo;
		if(phoneNoStr.equals("")) phoneNo = 0;
		else phoneNo = Integer.parseInt(phoneNoStr);
		
		UserDetailsDB userDB = new UserDetailsDB();
		UserDetails user = new UserDetails();	
		
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		user.setAddress(address);
		user.setPhoneNo(phoneNo);
		user.setRole("M");
		user.setPfp("/images/u101.png");
		
		int count = userDB.insertUser(user);
		
		System.out.print(count+" row affected");
		
		HttpSession session=request.getSession(); 
		session.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("CA1/Home.jsp");
		rd.forward(request, response);
		}catch(Exception e){
			System.out.print(e);
			RequestDispatcher rd = request.getRequestDispatcher("CA1/CustomerRegistration.jsp?errorCode=invalidEmail");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
