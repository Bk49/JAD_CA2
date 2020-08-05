package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import valueBean.CartDetails;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the whole cart and the item to be removed
		HttpSession session = request.getSession();	
		ArrayList<CartDetails> cart = (ArrayList<CartDetails>)session.getAttribute("cart");
		int delProdId = Integer.parseInt(request.getParameter("productId"));
		
		// Find the item and remove it from the shopping cart
		for(int i = 0; i < cart.size();i++) {
			if(cart.get(i).getProductId() == delProdId) {
				cart.remove(i);
				break;
			}
		}
		
		session.setAttribute("cart", cart);
		
		// Link back to the shopping cart
		response.sendRedirect("./GoShoppingCart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
