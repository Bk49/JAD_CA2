package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import valueBean.ProductDetails;
import valueBean.UserDetails;
import utilityBean.ProductDetailsDB;
import utilityBean.UserDetailsDB;

/**
 * Servlet implementation class GetUserDetails
 */
@WebServlet("/GetUserDetails")
public class GetUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		UserDetailsDB userDB = new UserDetailsDB();
		UserDetails user;
		
		user = userDB.getUserDetails(email, pwd);
		
		System.out.print("This is from GetUserDetails "+user.getName());
		HttpSession session=request.getSession(); 
		session.setAttribute("user", user);
		String link;
		
		// This is for Administrator users
		if(user.getRole().equals("A")) link = "CA1/Administrator.jsp";
		
		// This is for non-admin users
		else {
			// Handling forward link
			link = "CA1/Home.jsp";
			
			// GoHome.java here
			// Get all the product categories
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				ArrayList<String> categories = new ArrayList<String>();
				
				categories = productDB.getProductCategories();
				
				request.setAttribute("productCategories", categories);

				}catch(Exception e){
					System.out.print(e);
				}
			
			// Select random products to display
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
				
				products = productDB.getRandomProducts();
				
				request.setAttribute("randomProducts", products);

				}catch(Exception e){
					System.out.print(e);
				}
		}// else ends here
		
		// Forwards from here
		RequestDispatcher rd = request.getRequestDispatcher(link);
		rd.forward(request, response);
		}catch(Exception e){
			System.out.print(e);
			RequestDispatcher rd = request.getRequestDispatcher("CA1/Login.jsp?errorCode=invalidLogin");
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
