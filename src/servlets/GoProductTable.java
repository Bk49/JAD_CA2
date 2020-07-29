package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.ProductDetailsDB;
import valueBean.ProductDetails;

/**
 * Servlet implementation class GoProductTable
 */
@WebServlet("/GoProductTable")
public class GoProductTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoProductTable() {
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
				
				// Accessing the database to get productTable
				try {
					ProductDetailsDB productDB = new ProductDetailsDB();
					ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
					
					products = productDB.getProductsLimit(pg);
					System.out.print(products.size()+" from GoProductTable");
					
					request.setAttribute("products", products);

					}catch(Exception e){
						System.out.print(e);
					}
				// Get the number of rows in the productTable
				try {
					ProductDetailsDB productDB = new ProductDetailsDB();
					double discountCount = productDB.getProductCount();
					
					request.setAttribute("productCount", discountCount);

					}catch(Exception e){
						System.out.print(e);
					}
				
				// Forwards to discountTable.jsp
				RequestDispatcher rd = request.getRequestDispatcher("CA1/productTable.jsp");
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
