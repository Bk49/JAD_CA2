package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import utilityBean.ProductDetailsDB;
import valueBean.ProductDetails;

/**
 * Servlet implementation class GetProductCategory
 */
@WebServlet("/GoHome")
public class GoHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Get all the product categories
		try {
			ProductDetailsDB productDB = new ProductDetailsDB();
			ArrayList<String> categories = new ArrayList<String>();
			
			categories = productDB.getProductCategories();
			
			request.setAttribute("productCategories", categories);

			}catch(Exception e){
				System.out.print(e);
			}
		
		// Select random products to display
		try {
			ProductDetailsDB productDB = new ProductDetailsDB();
			ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
			
			products = productDB.getRandomProducts();
			System.out.print(products.size());
			
			request.setAttribute("randomProducts", products);

			}catch(Exception e){
				System.out.print(e);
			}
		
		// Forward to Home Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/Home.jsp");
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
