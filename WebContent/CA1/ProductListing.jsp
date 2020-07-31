<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="valueBean.UserDetails" %>
<%@ page import="valueBean.ProductDetails" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Products</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/ProductListing.css">
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
    		    <a class="nav-link" href="../CA2/GoHome">Home <span class="sr-only">(current)</span></a>
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
    out.print("<a href='../CA2/GoProductListing?category="+category+"' class='dropdown-item'>"+category.toUpperCase()+"</a><br>");
}

}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("./GoHome");
	rd.forward(request, response);
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

    if (session.getAttribute("user")!=null) {
  	  
  	  UserDetails user = (UserDetails)session.getAttribute("user");
        String pfp = user.getPfp();
        out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='."+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
        out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
      "<a class='dropdown-item' href='./GoProfilePage'>Profile</a>" +
"<div class='dropdown-divider'></div>" +
   "<a class='dropdown-item' href='./CA1/logout.jsp'>Log Out</a>" +
 "</div>");
    }else{
  	  out.print("<a class=\"nav-link\" href='Login.jsp'>LOGIN</a>");
    }

%>

     		 </li>
      	</ul>   
  </div>
</nav>

<%
int pg= (int)request.getAttribute("pg");

ArrayList<ProductDetails> products = (ArrayList<ProductDetails>)request.getAttribute("products");
String category = (String)request.getAttribute("category");

     		out.print("<div class=\"container margin\">");
     		if(category.equals("%")){
     	   		out.print("<h2 class=\"text-warning text-center\">All Products</h2>");
     			}else{
     	   		out.print("<h2 class=\"text-warning text-center\">"+category+"</h2>");
     			}
     		out.print("<div class=\"row row-cols-2 row-cols-md-3\">");
       		
       		for (ProductDetails product : products) {
       			out.print(       					
            "<div class=\"col mb-4\">" +
              "<div class=\"card cardProduct \">" +
    		      "<a href='./GoProductDetail?productId="+product.getProductId()+"'>"+
                    "<img src='."+product.getImageLocation()+"' class=\"card-img-top\" alt=\"...\">" +
                "<div class=\"card-body\">" +
                    "<h5 class=\"card-title\">"+product.getProductName()+"</h5>" +
                    "<p class=\"card-text\">"+String.format("%.2f", product.getCostPrice())+"</p>" +
        		  "</a>" +
                "</div>"+
              "</div>"+
            "</div>");
       		}
            out.print("</div>");
            out.print("</div>");
%>


<div class="container text-center">

<div class='pagination'>
<%
	double noOfProducts;
	noOfProducts = (double)request.getAttribute("productCount");
   
        double Pages = (double)Math.ceil((double) noOfProducts/(double)10.0);

        for(int i = 0 ; i < Pages; i++)
     out.print("<a  class='active' href='GoProductListing?pg="+(i+1)+"'>"+ (i+1) +"</a>");	          
		          
%>
</div>

</div>
<%@ include file = "footer.jsp" %>

 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 
</body>
</html>