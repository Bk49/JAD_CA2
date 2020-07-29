<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="valueBean.UserDetails" %>
<%@ page import="valueBean.ProductDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/Home.css">
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
    out.print("<a href='../CA2/GoHome?category="+category+"' class='dropdown-item'>"+category.toUpperCase()+"</a><br>");
}

}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("../GoHome");
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
	if(session.getAttribute("user")!=null){

		UserDetails user = (UserDetails)session.getAttribute("user");
	        String pfp = user.getPfp();
	        out.print(pfp);
	        out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='."+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
	        out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
	      "<a class='dropdown-item' href='CA1/profilePage.jsp'>Profile</a>" +
	"<div class='dropdown-divider'></div>" +
	   "<a class='dropdown-item' href='logout.jsp'>Log Out</a>" +
	 "</div>");
	}else{
		out.print("<a class=\"nav-link\" href='./CA1/Login.jsp'>LOGIN</a>");
	}
%>

     		 </li>
      	</ul>   
  </div>
</nav>

<div class="main">
      <div class="mainBackground">
        <div class="maintext"><p id="mainPara">WELCOME TO SHOPMANIA!</p></div>
        <div class="subtext"><p id="subPara">Check out our products</p></div>
        <a class="btn btn-warning btn-lg exploreBtn" href="#Category" role="button">SHOP NOW</a>
      </div>
</div>
<br>
<br>

<div class="container text-center catCon">
     <h1 class=" text-warning" id="Category">Categories</h1>

  <div class="card-deck row row-col-4">
  <div class="card catCard" >
    <a href="ProductListing.jsp?category=Gaming Mouse">
      <img src="./images/Gaming Mouse Icon.png" class="card-img-top catIcon" alt="...">
    </a>
      <div class="card-body">
           <h5 class="card-title text-white"><a href="./GoProductListing?category=Gaming Mouse"> Gaming Mouse </a></h5>
    </div>
  </div>
  
    <div class="card catCard" style="width: 10rem;">
    <a href="ProductListing.jsp?category=Gaming Keyboard">   
    <img src="./images/Gaming Keyboard Icon.png" class="card-img-top catIcon" alt="...">
    </a>
      <div class="card-body">
        <h5 class="card-title text-white"><a href="./GoProductListing?category=Gaming Keyboard">Gaming Keyboard</a></h5>
    </div>
  </div>
  
    <div class="card catCard" style="width: 10rem;">
      <a href="ProductListing.jsp?category=Computer Graphics Card">   
        <img src="./images/Graphics Card Icon.png" class="card-img-top catIcon" alt="...">
      </a>
      <div class="card-body">
        <h5 class="card-title text-white"><a href="./GoProductListing?category=Computer Graphics Card">Computer Graphics Card </a></h5>
    </div>
  </div>
  
   <div class="card catCard" style="width: 10rem;">
      <a href="ProductListing.jsp?category=Gaming Mouse Pad">   
        <img src="./images/Gaming Mouse Pad.png" class="card-img-top catIcon" alt="...">
      </a>
      <div class="card-body">
        <h5 class="card-title text-white"><a href="./GoProductListing?category=Gaming Mouse Pad">Gaming Mouse Pads</a></h5>
    </div>
  </div>
  <div class="card catCard" style="width: 10rem;">
    <a href="ProductListing.jsp?category=Gaming Headphones">   
       <img src="./images/Gaming Headphones Icon.png" class="card-img-top catIcon1" alt="...">
    </a>
      <div class="card-body">
        <h5 class="card-title text-white"><a href="./GoProductListing?category=Gaming Headphones">Gaming Headphones</a></h5>
    </div>
  </div>
  </div>
</div>

<br>
<br>

<%
   		out.print("<div class=\"container margin\">");
   		out.print("<h2 class=\"text-warning text-center\">All Products</h2>");
   		out.print("<div class=\"row row-cols-2 row-cols-md-3\">");
   		
   		ArrayList<ProductDetails> products =(ArrayList<ProductDetails>) request.getAttribute("randomProducts");
   		System.out.print(products.size());
   		
   		for (ProductDetails product: products) {
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>