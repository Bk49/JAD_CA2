<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="valueBean.ProductDetails" %>
<%@page import="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Detail</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productDetail.css">
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
    		  <li class="nav-item active">
    		    <a class="nav-link" href="Home.jsp">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item dropdown">
    		    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   		       Categories
    		    </a>
   		     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
   		     <%
ArrayList<String> categories = (ArrayList)request.getAttribute("productCategories");
          for (String category : categories) {           
              // Style this line of code!
              out.print("<a href='ProductListing.jsp?category="+category+"' class='dropdown-item'>"+category.toUpperCase()+"</a><br>");
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
       out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='."+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
       out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
       "<a class='dropdown-item' href='../GoProfilePage'>Profile</a>" +
   "<div class='dropdown-divider'></div>" +
  	   "<a class='dropdown-item' href='logout.jsp'>Log Out</a>" +
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
ProductDetails product = (ProductDetails)request.getAttribute("product");
    		
out.print("<div class=\"container \">");
out.print("<div class=\"row \">");
out.print(
		"<div class=\"col-4 \">"+
		"<h2 class=\"text-warning\">"+product.getProductName()+"</h2>" +
	    "<img src='."+product.getImageLocation()+"' class=\"productImg\" alt='productImage'>"+
		"</div>" +
	   	"<div class=\"col-8 \">" +
		"<div class=\"text-white\">"+product.getBriefDescription()+"</div>"+
		"<div class=\"text-white\">"+product.getDetailDescription()+"</div>"+
		"<div class=\"text-white\">"+String.format("%.2f", product.getCostPrice())+"</div>"+
		"<div class=\"text-white\">"+String.format("%.2f", product.getRetailPrice())+"</div>"+
		"<div class=\"text-white\">"+product.getProductCategory()+"</div>" +
		"</div>"
		);
%>
 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 
</body>
</html>