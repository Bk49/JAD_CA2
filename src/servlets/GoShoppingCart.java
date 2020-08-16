package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import valueBean.CartDetails;
import valueBean.ProductDetails;
import utilityBean.ProductDetailsDB;
import valueBean.ProductCart;
import valueBean.DiscountDetails;
import utilityBean.DiscountDetailsDB;

/**
 * Servlet implementation class GoShoppingCart
 */
@WebServlet("/GoShoppingCart")
public class GoShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
				
				
		HttpSession session = request.getSession();
		ArrayList<CartDetails> cart = null;
		if(session.getAttribute("cart") != null) cart = (ArrayList<CartDetails>)session.getAttribute("cart");
		ProductCart prodCT = new ProductCart();
		
			ProductDetailsDB productDB = new ProductDetailsDB();
			ProductDetails product;
			int quantity;
			if(cart != null) {
				// Populate the ProductCart
				for(CartDetails cartDT : cart) {
					product = productDB.getProductDetails(cartDT.getProductId());
					System.out.println("this is the id from line 51: " + product.getProductId());
					quantity = cartDT.getQuantity();
					
					prodCT.pushProduct(product, quantity);
					System.out.println("prodCT has "+prodCT.getProducts().size());
				}
			}
		
		// Set the product cart into the req obj
		request.setAttribute("prodCT", prodCT);
		
		// Get all the product categories
		try {
			productDB = new ProductDetailsDB();
			ArrayList<String> categories = new ArrayList<String>();
			
			categories = productDB.getProductCategories();
			
			request.setAttribute("productCategories", categories);

			}catch(Exception e){
				System.out.print(e);
			}
		
		// Get all the product categories
		
		String discountCd= request.getParameter("discountCode");
		if(discountCd !=null) {
			
			try {
				DiscountDetailsDB discountDB = new DiscountDetailsDB();
				DiscountDetails discount = new DiscountDetails();

				discount = discountDB.getDiscountValue(discountCd);
				System.out.print(discount.getDiscountCode());
				request.setAttribute("discountDetails", discount);

				}catch(Exception e){
					System.out.print(e);
				}
			
		}

		
		// Forwards to ShoppingCart.jsp
		RequestDispatcher rd = request.getRequestDispatcher("CA1/ShoppingCart.jsp");
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
