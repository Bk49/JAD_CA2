package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import utilityBean.DiscountDetailsDB;
import utilityBean.ProductDetailsDB;
import utilityBean.UserDetailsDB;
import utilityBean.OrderDB;

import valueBean.DiscountDetails;
import valueBean.ProductDetails;
import valueBean.UserDetails;
import valueBean.Order;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AddDiscount
 */
@WebServlet("/ReportsPage")
public class ReportsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportsPage() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String type = request.getParameter("report");
		if(type == null) type="";
		int pg;
		pg = (request.getParameter("pg") != null) ? Integer.parseInt(request.getParameter("pg")) : 1;
		request.setAttribute("pg", pg);
		
		if(type.equals("customer")) {
			// Get the users by address
			try {
				UserDetailsDB productDB = new UserDetailsDB();
				ArrayList<UserDetails> users = new ArrayList<UserDetails>();
				
				users = productDB.getUserByAddress(pg);
				
				request.setAttribute("users", users);

				}catch(Exception e){
					System.out.print(e);
				}
			
			// Get the number of users
			try {
				UserDetailsDB userDB = new UserDetailsDB();
				double count = 0;
				count = userDB.getCount();
				
				request.setAttribute("count", count);
			}catch(Exception e) {
				System.out.print(e);
			}
			request.setAttribute("title", "Listing of Customers by Address" );
			request.setAttribute("report", "customer" );

		}else if(request.getParameter("stock") != null) {
			int stock = Integer.parseInt(request.getParameter("stock"));
			
			// Accessing the database to get productTable
						try {
							ProductDetailsDB productDB = new ProductDetailsDB();
							ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
							
							products = productDB.getProductDetailsStock(stock,pg);
							
							request.setAttribute("products", products);

							}catch(Exception e){
								System.out.print(e);
							}
						

						// Get the number of rows in the productTable
						try {
							ProductDetailsDB productDB = new ProductDetailsDB();
							double count = productDB.getProductCountStock(stock);
							
							request.setAttribute("count", count);

							}catch(Exception e){
								System.out.print(e);
							}
						request.setAttribute("title", "Products with Stock Quantity Lower Than " + stock );
						request.setAttribute("stock", stock );

			
		}else if(type.equals("product2")) {
			
			// Accessing the database to get productTable
			try {
				ProductDetailsDB productDB = new ProductDetailsDB();
				ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
				
				products = productDB.getTop10Products();
				
				request.setAttribute("products", products);

				}catch(Exception e){
					System.out.print(e);
				}
			
			
			request.setAttribute("title", "Top 10 Best Selling Products" );
			request.setAttribute("report", "product2" );
		}else if(type.equals("order1")) {
			
			try {
				UserDetailsDB productDB = new UserDetailsDB();
				ArrayList<UserDetails> users = new ArrayList<UserDetails>();
				
				users = productDB.getTop10Customers();
				
				request.setAttribute("users", users);

				}catch(Exception e){
					System.out.print(e);
				}
			
			
			
			request.setAttribute("title", "Top 10 Customers Who Made The Most Purchase" );
			request.setAttribute("report", "order1" );
		}else if(type.equals("order2")) {
			//get order by date
			try {
				OrderDB orderDB = new OrderDB();
				ArrayList<Order> orders = new ArrayList<Order>();
				
				orders = orderDB.getOrdersbyDate(pg);
				
				request.setAttribute("orders", orders);

				}catch(Exception e){
					System.out.print(e);
				}
			
			//get order by date count
			
			// Get the count
						try {
							OrderDB orderDB = new OrderDB();
							double count = 0;
							count = orderDB.getOrdersbyDateCount();
							
							request.setAttribute("count", count);
						}catch(Exception e) {
							System.out.print(e);
						}
						
			
			request.setAttribute("title", "Listing of Orders by Date" );
			request.setAttribute("report", "order2" );
		}else if(request.getParameter("products") != null){
			String product = request.getParameter("products");
			//get customer by product 
			try {
				UserDetailsDB productDB = new UserDetailsDB();
				ArrayList<UserDetails> users = new ArrayList<UserDetails>();
				
				users = productDB.getCustomerByProduct(product,pg);
				
				request.setAttribute("users", users);

				}catch(Exception e){
					System.out.print(e);
				}
			
			// Get the  count
			try {
				UserDetailsDB productDB = new UserDetailsDB();
				double count = 0;
				count = productDB.getCustomerByProductCount(product);
				
				request.setAttribute("count", count);
			}catch(Exception e) {
				System.out.print(e);
			}
			
			
			
			request.setAttribute("title", "Customers who purchased " + request.getParameter("products") );
			request.setAttribute("product",  request.getParameter("products")  );
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("CA1/ReportsPage.jsp");
		rd.forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
