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
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Getting the discount details
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		String briefDescription = request.getParameter("briefDescription");
		String detailDescription = request.getParameter("detailDescription");
		double costPrice = Double.parseDouble(request.getParameter("costPrice"));
		double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));
		int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
		String productCategory = request.getParameter("productCategory");
		String imageLocation = request.getParameter("imageLocation");
					
					// Check for values of detailDescription and imageLocation
					detailDescription = ((detailDescription == null)? "" : detailDescription);
					imageLocation = ((briefDescription == null)? "/images/p101.png" : imageLocation);
					
					// Setting all the attributes into a value bean
					ProductDetails product = new ProductDetails();
					product.setProductId(productId);
					product.setProductName(productName);
					product.setBriefDescription(briefDescription);
					product.setDetailDescription(detailDescription);
					product.setCostPrice(costPrice);
					product.setRetailPrice(retailPrice);
					product.setStockQuantity(stockQuantity);
					product.setProductCategory(productCategory);
					product.setImageLocation(imageLocation);

					// Accessing the database to update product
					try {
						ProductDetailsDB productDB = new ProductDetailsDB();
						int count = productDB.updateProduct(product);
						System.out.println(count + " number of lines has been updated!");
						
						}catch(Exception e){
							System.out.print(e);
						}
					
					// Send Redirect to productTable.jsp
					response.sendRedirect("./GoProductTable");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
