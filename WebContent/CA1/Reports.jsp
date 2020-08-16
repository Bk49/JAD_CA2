<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.lang.*" %>
    <%@page import="java.util.ArrayList" %>    
    <%@page import="valueBean.ProductDetails" %> <%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reports</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productTable.css?v=2">
</head>
<body>
<%
try{
	UserDetails user = (UserDetails)session.getAttribute("user");
	if(user.getRole().equals("M")) response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}catch(Exception e){
	 response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}

%>

<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
  		    		      <li class="nav-item active">
    		    <a class="nav-link" href="./CA1/Administrator.jsp">Administrator <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoDiscountTable">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoProductTable">Edit Products<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoUserTable">Edit Users<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./Reports">Reports<span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
  </div>
</nav>

<div class="container text-center">
<h1>Reports</h1>
<br>
<div class="row">
    <div class="col-sm">
      <h2>Customers</h2>
    	<div class="card">
 		 <div class="card-body">
     	 <a href='./ReportsPage?report=customer'>Listing of Customers by Address</a>
 		 </div>
		</div>
    </div>


    <div class="col-sm">
      <h2>Products</h2>

        <div class="card">
        <div class="card-body">
     		 <form action='./ReportsPage'>
      		    <label>Products with Stock Quantity Lower Than :</label>
      			<input type='number' name="stock" placeholder="Enter Stock Quantity" required>
     		 	<input type='submit' class="submit bg-warning" value = 'Search'>
     		 </form>
       </div>
       </div>
       
		<div class="card">
        <div class="card-body">
              <a href='./ReportsPage?report=product2'>Top 10 Best Selling Product</a>
        </div>
        </div>
        
	</div>

    <div class="col-sm">
          <h2>Orders</h2>
          
          <div class="card">
  			<div class="card-body">
  			<form action='./ReportsPage'>
      		  <label>Customers who bought:</label>
      		  <div class="wrapper">
      		  
      <select name="products" class="form-control selection" size="5" required>
        <%
        ArrayList<String> productNames = (ArrayList<String>)request.getAttribute("productNames");
		for (String productName : productNames) {    
    	out.print("<option>"+productName.toUpperCase()+"</option>");
		}
  		%>  
    </select>
    </div>
          	<input type='submit' class="submit bg-warning" value = 'Search'>
      		</form>
        </div>
		</div>
    
          <div class="card">
  			<div class="card-body">
     	      	<a href='./ReportsPage?report=order1'>Top 10 Customers Who Made The Most Purchase</a>
  			</div>
			</div>
			    
          <div class="card">
  			<div class="card-body">
     	      	<a href='./ReportsPage?report=order2'>Listing of Orders by Date</a>
  			</div>
			</div>
    </div>
    
  </div>
  
</div>



</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>