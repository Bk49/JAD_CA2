<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.sql.*" %>
        <%@page import="valueBean.DiscountDetails" %>
        <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Discount Codes</title>
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
    		    <a class="nav-link" href="./GoDiscountTable?pg=1">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoProductTable?pg=1">Edit Products<span class="sr-only">(current)</span></a>
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

<h1>Discount Codes in the database</h1>

<!-- This button will allow the user to add new discount codes into the database -->
<a class="btn btn-warning" href='./CA1/addDiscount.jsp'>Add new discount code</a><br><br>

<table class="table table-striped table-hover"><!-- All discount code will be pushed into this table -->
<tr>
<th>No.</th>
<th>Discount Code</th>
<th>Discount Price</th>
<th>Discount Amount</th>
<th>Usage Limit</th>
<th>Number of uses</th>
<th colspan="2">Discount Options</th>
</tr>
<%
int pg= (int)request.getAttribute("pg");
int count = pg*10-10+1;
ArrayList<DiscountDetails> discounts= (ArrayList<DiscountDetails>)request.getAttribute("discounts");

String discountType;
Double discountValue;
String discountPrice;

   for (DiscountDetails discount : discounts) {       
	   
	   discountValue = discount.getDiscountValue();
	   discountType = discount.getDiscountType();
	   if(discountType.equals("DIRECT")){
		   discountPrice = "$"+String.format("%.2f",discountValue);
	   }else{
		   discountPrice = String.format("%.2f",discountValue)+"%";
	   }
	   
       // Printing all the items in the rs
       out.print("<tr>"+
       "<td>"+count+"</td>"+
       "<td>"+discount.getDiscountCode()+"</td>"+
       "<td>"+discountPrice+"<td>"+
       "<td>"+discount.getUsageLimit()+"</td>"+
       "<td>"+discount.getUsageCount()+"</td>"+
       "<td><a class='btn btn-warning' href='../CA2/GoEditDiscount?discountId="+discount.getDiscountId()+"'\">Edit</button></td>"+
       "<td><a class='btn btn-warning' href='../CA2/GoDeleteDiscount?discountId="+discount.getDiscountId()+"'\">Delete</button></td>"+
       "</tr>");
       
       count++;
   }
       
%>

</table>

<div class='pagination'>
<%
double  discountCount = (double)request.getAttribute("discountCount");
    
   double Pages = (double)Math.ceil((double) discountCount/(double)10.0);

   for(int i = 0 ; i < Pages; i++)
out.print("<a  class='active' href='../GoDiscountTable?pg="+(i+1)+"'>"+ (i+1) +"</a>");	          
%>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>