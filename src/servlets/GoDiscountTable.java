package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.DiscountDetailsDB;
import valueBean.DiscountDetails;

/**
 * Servlet implementation class GoDiscountTable
 */
@WebServlet("/GoDiscountTable")
public class GoDiscountTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoDiscountTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Getting the page number and set the current page number in the request obj
		int pg;
		try {
			pg = Integer.parseInt(request.getParameter("pg"));
		}catch(Exception e) {
			pg = 1;
		}
		request.setAttribute("pg", pg);
		
		// Accessing the database to get discountTable
		try {
			DiscountDetailsDB discountDB = new DiscountDetailsDB();
			ArrayList<DiscountDetails> discounts = new ArrayList<DiscountDetails>();
			
			discounts = discountDB.getDiscountsLimit(pg);
			System.out.print(discounts.size()+" from GoDiscountTable");
			
			request.setAttribute("discounts", discounts);

			}catch(Exception e){
				System.out.print(e);
			}
		// Get the number of rows in the discountTable
		try {
			DiscountDetailsDB discountDB = new DiscountDetailsDB();
			double discountCount = discountDB.getDiscountCount();
			
			request.setAttribute("discountCount", discountCount);

			}catch(Exception e){
				System.out.print(e);
			}
		
		// Forwards to discountTable.jsp
		RequestDispatcher rd = request.getRequestDispatcher("CA1/discountTable.jsp");
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
