package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilityBean.OrderDB;
import utilityBean.OrderDetailsDB;
import utilityBean.PaymentDB;
import utilityBean.ProductDetailsDB;
import valueBean.CartDetails;
import valueBean.PaymentDetails;
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
	
		String link = "";
		
		// Set the totalPrice to be used by the payments page
		HttpSession session = request.getSession();
		double totalPrice = (double)session.getAttribute("totalPrice");
				
		// Insert a new order and orderDetails according to the order
		UserDetails user = (UserDetails)session.getAttribute("user");
		try {
			if(user == null) {
				System.out.print(" nooot s  ");

				 link = "CA1/Login.jsp";
			}
		}catch(Exception e) {
			System.out.print("loged  e");

			 link = "CA1/Login.jsp";
		}
		

		ArrayList<CartDetails> cart = (ArrayList<CartDetails>)session.getAttribute("cart");
		
			try {
				// Insert new order
				OrderDB orderDB = new OrderDB();
				int count = orderDB.insertOrder(user.getUserId(), totalPrice);
				if(count == 0) System.out.println("orderDB.insertOrder had failed to create the order!");
				
				// Get the id of the inserted order
				int orderId = orderDB.getOrderId(user.getUserId(), totalPrice);
				if(orderId == 0) System.out.println("orderDB.getOrderId had failed to get the orderId!");
				
				// Use the orderId for inserting the orderDetails
				OrderDetailsDB orderDTDB =  new OrderDetailsDB();
				int count2 = orderDTDB.bulkInsertOD(orderId, cart);
				if(count2 == 0) System.out.println("orderDTDB insert bulk had failed");
				link = "CA1/Payment.jsp";

			}catch(Exception e) {
				System.out.println(e);
			}
			
		// Get all payment details available from this user
		PaymentDB paymentDB = new PaymentDB();
		ArrayList<PaymentDetails> payments = paymentDB.getPaymentDetails(user.getUserId());
		request.setAttribute("payments", payments);


		// Forwards to Payment.jsp
		RequestDispatcher rd = request.getRequestDispatcher(link);
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
