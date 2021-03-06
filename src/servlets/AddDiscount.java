package servlets;

import java.io.IOException;
import java.util.List;
import java.io.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import utilityBean.DiscountDetailsDB;
import valueBean.DiscountDetails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AddDiscount
 */
@WebServlet("/AddDiscount")
public class AddDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String discountCode = request.getParameter("discountCode");
			double discountValue = Double.parseDouble(request.getParameter("discountValue"));
			String discountType = request.getParameter("discountType");
			int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));
			
			DiscountDetailsDB discountDB = new DiscountDetailsDB();
			DiscountDetails discount = new DiscountDetails();	
			
			discount.setDiscountCode(discountCode);
			discount.setDiscountValue(discountValue);
			discount.setDiscountType(discountType);
			discount.setUsageLimit(usageLimit);
			
			int count = discountDB.insertDiscount(discount);
			
			System.out.print(count+" row affected");			
			}catch(Exception e){
				System.out.print(e);
			}
		
		// Send Redirect to discountTable.jsp
		response.sendRedirect("./GoDiscountTable");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
