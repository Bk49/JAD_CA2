<%-- 
  - Author(s): LIEW KHYE LERK(P1937000), ERIC NG YONG WEI(P1940211);
  - Date: 29/5/20;
  --%>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="valueBean.UserDetails" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Details</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/profilePage.css">
<link rel="stylesheet" type="text/css" href="./CA1/css/footer.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item dropdown">
    		    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   		        Categories
    		    </a>
   		     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<%
try{
ArrayList<String> categories = (ArrayList<String>)request.getAttribute("productCategories");

for (String category : categories) {    
    // Style this line of code!
   out.print("<a href='./GoProductListing?category="+category+"' class='dropdown-item'>"+category.toUpperCase()+"</a><br>");
}

}catch(Exception e){
	System.out.print(e);
	//RequestDispatcher rd = request.getRequestDispatcher("../GoHome");
	//rd.forward(request, response);
	}

%>
    		 </div>
  		    </li>
 		   </ul>
   		 <form class="form-inline my-2 my-lg-0">
   		   <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
    		  <button class="btn btn-warning my-2 my-sm-0" type="submit">Search</button>
         </form>
   		 <ul  class="navbar-nav mr-right dropleft" style="margin-left:20px" >
         		 <li class="nav-item dropdown ">
        <%
	if(session.getAttribute("user")!=null){

		UserDetails user = (UserDetails)session.getAttribute("user");
	        String pfp = user.getPfp();
	        out.print(pfp);
	        out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='."+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
	        out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
	      "<a class='dropdown-item' href='CA1/profilePage.jsp'>Profile</a>" +
	"<div class='dropdown-divider'></div>" +
	   "<a class='dropdown-item' href='./CA1/logout.jsp'>Log Out</a>" +
	 "</div>");
	}else{
		out.print("<a class=\"nav-link\" href='./CA1/Login.jsp'>LOGIN</a>");
	}
%>

     		 </li>
      	</ul>   
  </div>
</nav>

<div class="d-flex justify-content-center text-center">

<%
if(session.getAttribute("user")!=null){

	UserDetails user = (UserDetails)session.getAttribute("user");
				out.print(
	   		    		"<div class='container padding-0'>"+
	   		    			"<div class='row padding-0 text-center justify-content-center'>" +
	   		   		  		    "<div class='col-md-5 padding-0 text-right '>" +
    		    					"<img src='."+user.getPfp()+"' alt='userPfp' class='profilePic'>"+
    							"</div>" +
	   		   		  		    "<div class='col-md-6 text-center padding-0'>" +	
	   	    		  			 "<form onsubmit='pfpCheck()' action='./UpdateProfile' class='formProfile'>"+
     								"<input type='hidden' name='userId' value='"+user.getUserId()+"'/>"+
    								"<div><label class='text-warning'>Name</label><br><input type='text' name='name' value='"+user.getName()+"' required></div>"+
    								"<div><label class='text-warning'>Email</label><br><input type='text' name='email' value='"+user.getEmail()+"' required></div>"+
    								"<div><label class='text-warning'>Profile Picture Link or Directory</label><br><input type='text' id='pfp' name='pfp' value='"+user.getPfp()+"'></div>"+
    								"<div><label class='text-warning'>Phone Number</label><br>s<input type='tel' pattern='[0-9]{8,14}' name='phoneNo' value='"+user.getPhoneNo()+"'></div>"+
									"<input type='submit' class='submit bg-warning col-12' value='Update Profile Details'><br>"+
								 "</form>"+
    						    "</div>" +
    						"</div>" +
    					"</div>"
    					);
				
    		}else{
    			out.print("<p class='text-danger'>There is an error retrieving the data from the database!</p>");
    		}
%>
</div>

<%@ include file = "footer.jsp" %>
<script>
function pfpCheck(){
	if(document.getElementById("pfp").value == ""){
		document.getElementById("pfp").value = "/images/u101.png"
	}	
}

</script>
 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 
</body>
</html>