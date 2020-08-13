<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.lang.*" %>
    <%@page import="java.util.ArrayList" %>    
    <%@page import="valueBean.ProductDetails" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productTable.css">
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
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoDiscountTable">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoProductTable">Edit Products<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="">Edit Users<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="">Orders<span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
   		 
  </div>
</nav>

<div class="container text-center">
<h1>Products in the database</h1>

<!-- This button will allow the user to add new products into the database -->
<a class='btn btn-warning' href='./AddProductPage'>Add new item</a>
<div class="container text-right">
<form action="./GoProductTable">
<input type="text" name="productSearch"  value="<%= request.getAttribute("ProductSearch") != null?request.getAttribute("ProductSearch") : "Enter Product Name" %> " placeholder="Enter Product Name"/>
<input type="submit" class="submit bg-warning" value="Search"/>
</form>
<br>
</div>
<table class="table table-striped table-hover" > <!-- All products will be pushed into this table -->
<tr>
<th>No.</th>
<th>Product Name</th>
<th>Cost Price</th>
<th>Retail Price</th>
<th>Stock Quantity</th>
<th>Category</th>
<th colspan="2">Product Options</th>

</tr>
<%    
int pg= (int)request.getAttribute("pg");
int count = pg*10-10+1;

ArrayList<ProductDetails> products = (ArrayList<ProductDetails>)request.getAttribute("products");
	for(ProductDetails product: products){
       out.print("<tr>"+
       "<td>"+count+"</td>"+
       "<td>"+product.getProductName()+"</td>"+
       "<td>"+String.format("%.2f",product.getCostPrice())+"</td>"+
       "<td>"+String.format("%.2f",product.getRetailPrice())+"</td>"+
       "<td>"+product.getStockQuantity()+"</td>"+
       "<td>"+product.getProductCategory()+"</td>"+
       "<td><a class='btn btn-warning' href='./GoEditProduct?productId="+product.getProductId()+"'\">Edit</button></td>"+
       "<td><a class='btn btn-warning' href='./GoDeleteProduct?productId="+product.getProductId()+"'\">Delete</button></td>"+
       "</tr>");
       
       count++;
	}

%>
</table>

<!-- Use this button to access to the next 10 rows in the second page -->
<div class='pagination'>
<%
	String productSearch = (String)request.getAttribute("ProductSearch");
	double noOfProducts;
	noOfProducts = (double)request.getAttribute("productCount");
   
        double Pages = (double)Math.ceil((double) noOfProducts/(double)10.0);

        for(int i = 0 ; i < Pages; i++){
        	System.out.print(productSearch);
        	if(productSearch == null){

   			 out.print("<a  class='active' href='GoProductTable?pg="+(i+1)+"'>"+ (i+1) +"</a>");
        	}
        	else{
        	     out.print("<a  class='active' href='GoProductTable?productSearch="+productSearch+"&pg="+(i+1)+"'>"+ (i+1) +"</a>");	          

        	}
        
        }
%>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>