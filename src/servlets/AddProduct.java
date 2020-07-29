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
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String productName = request.getParameter("productName");
			String briefDescription = request.getParameter("briefDescription");
			String detailDescription = request.getParameter("detailDescription");
			String costPriceStr = request.getParameter("costPrice");
			String retailPriceStr = request.getParameter("retailPrice");
			String stockQuantityStr = request.getParameter("stockQuantity");
			String productCategory = request.getParameter("productCategory");
			String imageLocation = request.getParameter("imageLocation");
			
			int retailPrice = 0;
			double costPrice=0;
			int stockQuantity=0;

			if(!retailPriceStr.equals("")){
				retailPrice = Integer.parseInt(retailPriceStr);
			}

			if(imageLocation == null){
				imageLocation = "/images/p101.png";
			}
			
			if(detailDescription == null) {
				detailDescription = "";
			}

			try{
			costPrice = Double.parseDouble(costPriceStr);
			stockQuantity = Integer.parseInt(stockQuantityStr);
			}catch( NumberFormatException e ){
				System.out.print(e);
			}
			
			
			ProductDetailsDB productDB = new ProductDetailsDB();
			ProductDetails product = new ProductDetails();	
			
			product.setProductName(productName);
			product.setBriefDescription(briefDescription);
			product.setDetailDescription(detailDescription);
			product.setCostPrice(costPrice);
			product.setRetailPrice(retailPrice);
			product.setStockQuantity(stockQuantity);
			product.setProductCategory(productCategory);
			product.setImageLocation(imageLocation);
			
			int count = productDB.insertProduct(product);
			
			System.out.print(count+" row affected");
			
			RequestDispatcher rd = request.getRequestDispatcher("/GoProductTable");
			rd.forward(request, response);
			}catch(Exception e){
				System.out.print(e);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
