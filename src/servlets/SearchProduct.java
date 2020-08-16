package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilityBean.ProductDetailsDB;
import valueBean.ProductDetails;

import java.util.ArrayList;

/**
 * Servlet implementation class SearchProduct
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get all the product categories
				try {
					ProductDetailsDB productDB = new ProductDetailsDB();
					ArrayList<String> categories = new ArrayList<String>();
					
					categories = productDB.getProductCategories();
					
					request.setAttribute("productCategories", categories);

					}catch(Exception e){
						System.out.print(e);
					}
		// Get the search string and pg
		String searchStr = request.getParameter("searchStr");
		if(searchStr == null) searchStr = "";
		String pgStr = request.getParameter("pg");
		int pg = (pgStr == null) ? 1 : Integer.parseInt(pgStr);
		
		// Get the products based on the name
		ProductDetailsDB productDB = new ProductDetailsDB();
		ArrayList<ProductDetails> products = productDB.searchProduct(searchStr, pg);
		// Get the number of rows in the productTable
		try {
			 productDB = new ProductDetailsDB();
			double discountCount = productDB.getProductSearchCount(searchStr);
			
			request.setAttribute("productCount", discountCount);

			}catch(Exception e){
				System.out.print(e);
			}
		
		// Set the products obtained in the req obj
		request.setAttribute("products", products);
		request.setAttribute("searchStr", searchStr);

		// Forward to ProductListing Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/Search.jsp");
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
