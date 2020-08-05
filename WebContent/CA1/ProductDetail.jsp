<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="valueBean.ProductDetails" %>
<%@page import="valueBean.UserDetails" %>
<%@page import="valueBean.CartDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Detail</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productDetail.css?v=1">
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
    		    <a class="nav-link" href="./CA1/Home.jsp">Home <span class="sr-only">(current)</span></a>
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
	RequestDispatcher rd = request.getRequestDispatcher("./Home.jsp");
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
       out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='."+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
       out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
       "<a class='dropdown-item' href='../GoProfilePage'>Profile</a>" +
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
<br>
<br>
<div class="container justify-content-center ">
<div class="row justify-content-center">
<%
ProductDetails product = (ProductDetails)request.getAttribute("product");
String name = product.getProductName();
String imageLocation = product.getImageLocation();
String briefDescription = product.getBriefDescription();
String detailDescription = product.getDetailDescription();
double costPrice = product.getCostPrice();
double retailPrice = product.getRetailPrice();
String productCategory = product.getProductCategory();
int productId = product.getProductId();
    		
out.print(
		"<div class=\"col-8 text-center\">"+
		"<h2 class=\"text-warning\">"+name+"</h2>" +
	    "<img src='./CA1"+imageLocation+"' class=\"productImg mx-auto width\" alt='productImage'>"+
		"</div>" +
	   	"<div class=\"col-4 text-left\">" +
				"<p class=\"text-warning\">Brief Description:</p>"+
		"<div class=\"text-white\">"+briefDescription+"</div>"+
				"<p class=\"text-warning\">Detailed Description:</p>"+

		"<div class=\"text-white\">"+detailDescription+"</div>"+
				"<p class=\"text-warning\">Cost Price:</p>"+

		"<div class=\"text-white\">"+String.format("%.2f", costPrice)+"</div>"+
				"<p class=\"text-warning\">Retail Price:</p>"+

		"<div class=\"text-white\">"+String.format("%.2f", retailPrice)+"</div>"+
				"<p class=\"text-warning\">Product Category:</p>"+

		"<div class=\"text-white\">"+productCategory+"</div>" +
		"</div>"
		);
%>
</div>
</div>

<form action="../CA2/AddToCart" method="post">
<input type="hidden" name="productId" value="<%= productId %>"/>

<label>Quantity</label>
<input type="number" name="quantity" min="1" value="1"/>

<input type="submit" value="Add to Cart!"/>
</form>

<%
ArrayList<CartDetails> cart = (session.getAttribute("cart") == null) ? null: (ArrayList<CartDetails>)session.getAttribute("cart");
if(cart != null){
	for(CartDetails cartDT : cart){
		if(cartDT.getProductId() == productId){
			out.print("<p class='text-white'>There is already "+cartDT.getQuantity()+" of this product in the cart!</p>");
		}
	}
}
%>

<a href="./GoShoppingCart">Go to Cart!</a>

<%@ include file = "footer.jsp" %>

 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 
</body>
</html>