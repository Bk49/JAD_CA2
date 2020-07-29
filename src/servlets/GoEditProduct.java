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

/**
 * Servlet implementation class GoEditProducts
 */
@WebServlet("/GoEditProduct")
public class GoEditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoEditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Get productId to be edited
					int productId = Integer.parseInt(request.getParameter("productId"));
					
					
					// Get Product Details
					ProductDetailsDB productDB = new ProductDetailsDB();
					ProductDetails product = productDB.getProductDetails(productId);
					request.setAttribute("product", product);
					
					// Forwards to editProduct.jsp
					RequestDispatcher rd = request.getRequestDispatcher("/CA1/editProduct.jsp");
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
