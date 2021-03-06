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
 * Servlet implementation class GoEditDiscount
 */
@WebServlet("/GoEditDiscount")
public class GoEditDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoEditDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			// Get discountId to be edited
			int discountId = Integer.parseInt(request.getParameter("discountId"));
			
			// Get Discount Details
			DiscountDetailsDB discountDB = new DiscountDetailsDB();
			DiscountDetails discount = discountDB.getDiscountDetails(discountId);
			request.setAttribute("discount", discount);
			
			// Forwards to editDiscount.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/CA1/editDiscount.jsp");
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
