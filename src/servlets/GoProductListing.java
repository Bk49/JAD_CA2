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
 * Servlet implementation class GoProductListing
 */
@WebServlet("/GoProductListing")
public class GoProductListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoProductListing() {
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
		
		
		  // Get all the product categories
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				ArrayList<String> categories = new ArrayList<String>();
				
				categories = productDB.getProductCategories();
				System.out.println("This is the size of categories "+categories.size());

				request.setAttribute("productCategories", categories);

				}catch(Exception e){
					System.out.print(e);
				}
		
		   // Get products based on category
			String category = ((request.getParameter("category")!=null) ? request.getParameter("category") : "%");
			request.setAttribute("category", category);
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				ArrayList<ProductDetails> products = productDB.getProductsOnCats(category,pg);
				request.setAttribute("products", products);
			}catch(Exception e) {
				System.out.print(e);
			}
			
			// Get the count of products
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				double discountCount = productDB.getProductCategoryCount(category);
				
				request.setAttribute("productCount", discountCount);

				}catch(Exception e){
					System.out.print(e);
				}
			
			// Forward to ProductListing Page
			RequestDispatcher rd = request.getRequestDispatcher("CA1/ProductListing.jsp");
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
