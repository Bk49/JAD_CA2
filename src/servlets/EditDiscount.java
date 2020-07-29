package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.DiscountDetailsDB;
import valueBean.DiscountDetails;

/**
 * Servlet implementation class EditDiscount
 */
@WebServlet("/EditDiscount")
public class EditDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Getting the discount details
			int discountId = Integer.parseInt(request.getParameter("discountId"));
			String discountCode = request.getParameter("discountCode");
			double discountValue = Double.parseDouble(request.getParameter("discountValue"));
			String discountType = request.getParameter("discountType");
			String usageLimitStr = request.getParameter("usageLimit");
			String usageCountStr = request.getParameter("usageCount");
			int usageLimit, usageCount;
			
			// Check for values of usageLimit and usageCount
			usageLimit = ((usageLimitStr == null)? 0 : Integer.parseInt(usageLimitStr));
			usageCount = ((usageCountStr == null)? 0 : Integer.parseInt(usageCountStr));
			
			// Setting all the attributes into a value bean
			DiscountDetails discount = new DiscountDetails();
			discount.setDiscountId(discountId);
			discount.setDiscountCode(discountCode);
			discount.setDiscountValue(discountValue);
			discount.setDiscountType(discountType);
			discount.setUsageLimit(usageLimit);
			discount.setUsageCount(usageCount);

			// Accessing the database to get discount
			try {
				DiscountDetailsDB discountDB = new DiscountDetailsDB();
				int count = discountDB.updateDiscount(discount);
				System.out.println(count + " number of lines has been updated!");
				
				}catch(Exception e){
					System.out.print(e);
				}
			
			// Forwards to discountTable.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/GoDiscountTable");
			rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
