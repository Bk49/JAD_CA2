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
import valueBean.DiscountDetails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AddDiscount
 */
@WebServlet("/AddProductPage")
public class AddProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ProductDetailsDB productDB = new ProductDetailsDB();
			ArrayList<String> categories = new ArrayList<String>();
			
			categories = productDB.getProductCategories();
			
			request.setAttribute("productCategories", categories);

			}catch(Exception e){
				System.out.print(e);
			}
		RequestDispatcher rd = request.getRequestDispatcher("CA1/addProduct.jsp");
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
