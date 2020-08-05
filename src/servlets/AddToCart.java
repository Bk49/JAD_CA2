package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import valueBean.CartDetails;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		CartDetails cartDT;
		
		// Check if cart has been created or not
		ArrayList<CartDetails> cart;
		if(session.getAttribute("cart") != null) {
			cart = (ArrayList<CartDetails>) session.getAttribute("cart");
		}
		else {
			System.out.println("cart is null!");
			cart = new ArrayList<CartDetails>();
		}
		
		// Add the item in the cart if not exists, if exists, increase the quantity
		for(int i = 0; cart.size() >= i;i++) {
			if(cart.size() == i) {
				cartDT = new CartDetails(productId, quantity);
				cart.add(cartDT);
				break;
			}
			else if(cart.get(i).getProductId() == productId) {
				int totalQuantity = cart.get(i).getQuantity()+quantity;
				cartDT = new CartDetails(productId, totalQuantity);
				cart.set(i, cartDT);
				break;
			}
		}
		
		// Set the cart in session
		session.setAttribute("cart", cart);
		
		// Redirect back to the productDetails page
		response.sendRedirect("./GoProductDetail?productId="+productId);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
