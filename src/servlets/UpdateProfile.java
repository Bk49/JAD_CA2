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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utilityBean.UserDetailsDB;
import valueBean.UserDetails;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Getting the account details
		int userId = 0;
		String name = "";
		String email = "";
		String pfp ="/images/u101.png";
		String phoneNo = "";


		
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String nameField = new File(item.getName()).getName();
                        item.write( new File("C:/Users/Khyelerk/eclipse-workspace/TestingCa2/WebContent/CA1/images" + File.separator + nameField));
                        
                        pfp = "/images/" + nameField;
                    }else {
                        String nameField = item.getFieldName();
                        String value = item.getString();
                        
                        if(nameField.equals("userId")) {
                        	userId = Integer.parseInt(value);
                        }else if(nameField.equals("name")) {
                        	name = value;
                        }else if(nameField.equals("email")) {
                        	if(value == null) value = "";
                        	email = value;
                        }else if(nameField.equals("phoneNo")) {
                        	phoneNo = value;
                        }
                        
                        
   	
                    }
            		// Setting all the attributes into a value bean
            		UserDetails user = new UserDetails();
            		user.setUserId(userId);
            		user.setName(name);
            		user.setEmail(email);
            		user.setPfp(pfp);
            		user.setPhoneNo(phoneNo);
            		// Accessing the database to update the user details
            		try {
            			UserDetailsDB userDB = new UserDetailsDB();
            			int count = userDB.updateUser(user);
            			System.out.println(count + " number of lines has been updated!");
            		}catch(Exception e) {
            			System.out.println(e);
            		}
            		// Reset the user attribute
            		HttpSession session = request.getSession();
            		user = (UserDetails)session.getAttribute("user");
            		user.setName(name);
            		user.setEmail(email);
            		user.setPfp(pfp);
            		user.setPhoneNo(phoneNo);
            		session.setAttribute("user", user);
            		
            		
                }
               //File uploaded successfully   
                
            } catch (Exception ex) {
            	
            	System.out.print(ex);
            }         		
        }
        
        
        
		
		// Send Redirect to Home.jsp
		response.sendRedirect("./GoHome");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
