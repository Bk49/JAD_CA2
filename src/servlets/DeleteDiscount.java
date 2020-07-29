package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.DiscountDetailsDB;

/**
 * Servlet implementation class DeleteDiscount
 */
@WebServlet("/DeleteDiscount")
public class DeleteDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Getting the discountId
		int discountId = Integer.parseInt(request.getParameter("discountId"));

		// Accessing the database to get discount
		try {
			DiscountDetailsDB discountDB = new DiscountDetailsDB();
			int count = discountDB.deleteDiscount(discountId);
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
