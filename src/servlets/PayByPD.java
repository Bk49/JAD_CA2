package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilityBean.OrderDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class PayByPD
 */
@WebServlet("/PayByPD")
public class PayByPD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayByPD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the totalPrice and user stored under the session
				HttpSession session = request.getSession();
				double totalPrice = (double)session.getAttribute("totalPrice");
				
				// Get the user
				UserDetails user = (UserDetails)session.getAttribute("user");
				
				try {
					// Change the status to be paid
					OrderDB orderDB = new OrderDB();
					int orderId =  orderDB.getOrderId(user.getUserId(), totalPrice);
					int count = orderDB.pay(orderId);
					System.out.println(count+" number of rows has been updated!");
				}catch(Exception e) {
					System.out.println(e);
				}
				
				// Link to purchase history jsp page with servlet
				response.sendRedirect("./GoPurchaseHistory");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
