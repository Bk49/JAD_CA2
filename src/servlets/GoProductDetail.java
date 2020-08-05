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
 * Servlet implementation class GoProductDetail
 */
@WebServlet("/GoProductDetail")
public class GoProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoProductDetail() {
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
		
		// Get product based on productId
		int productId = Integer.parseInt(request.getParameter("productId"));
		request.setAttribute("productId", productId);
		try {
			ProductDetailsDB productDB = new ProductDetailsDB();
			ProductDetails product = productDB.getProductDetails(productId);
			request.setAttribute("product", product);
		}catch(Exception e) {
			System.out.print(e);
		}
		
		// Forward to ProductDetail Page
		RequestDispatcher rd = request.getRequestDispatcher("CA1/ProductDetail.jsp");
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
