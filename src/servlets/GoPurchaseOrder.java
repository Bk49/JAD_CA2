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
import valueBean.OrderDetails;

/**
 * Servlet implementation class GoPurchaseOrder
 */
@WebServlet("/GoPurchaseOrder")
public class GoPurchaseOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoPurchaseOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the parameter first
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		request.setAttribute("orderId", orderId);
		String status = request.getParameter("status");
		request.setAttribute("status", status);
		
		// Access the database to retrieve order details of this order
		try {
			OrderDetailsDB ODDB = new OrderDetailsDB();
			ArrayList<OrderDetails> orderDetails = ODDB.getOrderDetailsById(orderId);
			
			request.setAttribute("orderDetails", orderDetails);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		// Access the database to get the totalPrice of the orderId
		try {
			OrderDB orderDB = new OrderDB();
			double totalPrice = orderDB.getPriceById(orderId);
			
			HttpSession session = request.getSession();
			session.setAttribute("totalPrice", totalPrice);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		// Forwards to PurchaseOrder.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/CA1/PurchaseOrder.jsp");
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
