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

import utilityBean.OrderDB;
import valueBean.UserDetails;
import valueBean.Order;

/**
 * Servlet implementation class GoPurchaseHistory
 */
@WebServlet("/GoPurchaseHistory")
public class GoPurchaseHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoPurchaseHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the userId from the session
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails)session.getAttribute("user");
		int userId = user.getUserId();
		
		// Get the orders from the database
		OrderDB orderDB = new OrderDB();
		ArrayList<Order> orders = orderDB.getOrders(userId);
		request.setAttribute("orders", orders);
		
		// Forwards to Purchase History Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/PurchaseHistory.jsp");
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
