package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		
    	
		String productName ="";
		String briefDescription ="";
		String detailDescription ="";
		String productCategory="";
		String imageLocation = "/images/p101.png";
		
		int retailPrice = 0;
		double costPrice=0;
		int stockQuantity=0;
		
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File("C:/Users/Khyelerk/eclipse-workspace/TestingCa2/WebContent/CA1/images" + File.separator + name));
                        
                        imageLocation = "/images/" + name;
                    }else {
                        String name = item.getFieldName();
                        String value = item.getString();
                        
                        if(name.equals("productName")) {
                        	productName = value;
                        }else if(name.equals("briefDescription")) {
                        	briefDescription = value;
                        }else if(name.equals("detailDescription")) {
                        	if(value == null) value = "";
                        	detailDescription = value;
                        }else if(name.equals("costPrice")) {
                			costPrice = Double.parseDouble(value); 	
                        }else if(name.equals("retailPrice")) {
                    		retailPrice = Integer.parseInt(value);
                        }else if(name.equals("stockQuantity")) {
                			stockQuantity = Integer.parseInt(value);
                        }else if(name.equals("productCategory")) {
                        	productCategory = value;
                        }
   	
                    }
                }
               //File uploaded successfully   
                
            } catch (Exception ex) {
            	
            	System.out.print(ex);
            }         		
        }
        
        
	try {

			
			
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
