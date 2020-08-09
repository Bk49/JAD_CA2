package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import valueBean.UserDetails;
import valueBean.PaymentDetails;
import utilityBean.OrderDB;
import utilityBean.PaymentDB;


/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
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
			int orderId = orderDB.getOrderId(user.getUserId(), totalPrice);	
			int count = orderDB.pay(orderId);
			System.out.println(count+" number of rows has been updated!");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		// Get all the details from the form
		String ccName = request.getParameter("ccName");
		String ccNumStr = request.getParameter("ccNum");
		String ccType = request.getParameter("CCType");
		String cvvStr = request.getParameter("cvv");
		String expDate = request.getParameter("expDt");
		String[] save = request.getParameterValues("save");
		System.out.println(ccName+" "+ccNumStr+" "+ccType+" "+cvvStr);
		System.out.println(request.getParameter("expDt"));
		
		if(save == null) {
			System.out.println("Save checkbox returns null!");
		}
			session.setAttribute("cart", null);
			// If save is checked, save payment details
			if(save !=null) {
				try {
					PaymentDetails payment = new PaymentDetails();

					payment.setCcName(ccName);
					payment.setCcNum(ccNumStr);
					payment.setCcType(ccType);
					payment.setCcCvv(Integer.parseInt(cvvStr));
					payment.setCustomerId(user.getUserId());
					payment.setCcExpDate(expDate);
					
					PaymentDB paymentDB = new PaymentDB();
					int count = paymentDB.insertPaymentDt(payment);
					System.out.println(count+" number of rows has been inserted into payment!");
					
				}catch(Exception e) {
					System.out.println(e);
				}		
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
