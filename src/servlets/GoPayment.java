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
import utilityBean.OrderDetailsDB;
import valueBean.CartDetails;
import valueBean.UserDetails;

/**
 * Servlet implementation class GoPayment
 */
@WebServlet("/GoPayment")
public class GoPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		request.setAttribute("totalPrice", totalPrice);
		
		HttpSession session = request.getSession();
		UserDetails user = (UserDetails)session.getAttribute("user");
		ArrayList<CartDetails> cart = (ArrayList<CartDetails>)session.getAttribute("cart");
		

			OrderDB orderDB = new OrderDB();
			int count = orderDB.insertOrder(user.getUserId(), totalPrice);
			if(count == 0) System.out.println("orderDB.insertOrder had failed to create the order!");
			
			int orderId = orderDB.getOrderId(user.getUserId(), totalPrice);
			if(orderId == 0) System.out.println("orderDB.getOrderId had failed to get the orderId!");
			
			OrderDetailsDB orderDTDB =  new OrderDetailsDB();
			int count2 = orderDTDB.bulkInsertOD(orderId, cart);
			if(count2 == 0) System.out.println("orderDTDB insert bulk had failed");

		// Forwards to Payment.jsp
		RequestDispatcher rd = request.getRequestDispatcher("CA1/Payment.jsp");
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
