<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@page import="valueBean.ProductDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deleting product</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/addProduct.css">
</head>
<body>
<div class="container text-center">
<%
ProductDetails product = (ProductDetails)request.getAttribute("product");
int productId = product.getProductId();


out.print("<div><h1 class='text-warning'>Are you sure you want to delete this product?</h1>"+
       			"<img src='."+product.getImageLocation()+"'alt='image'>"+
               	"<div class='text-warning'>Product Name:</div>"+
       			"<div class='text-white'>"+product.getProductName()+"</div>"+
                 "<div class='text-warning'>Brief Description: </div>"+
       			"<div class='text-white'>"+product.getBriefDescription()+"</div>"+
                 "<div class='text-warning'>Detailed Description: </div>"+
       			"<div class='text-white'>"+product.getDetailDescription()+"</div>"+
                 "<div class='text-warning'>Cost Price: </div>"+
       			"<div class='text-white'>"+String.format("%.2f",product.getCostPrice())+"</div>"+
                 "<div class='text-warning'>Retail Price: </div>"+
       			"<div class='text-white'>"+String.format("%.2f",product.getRetailPrice())+"</div>"+
                 "<div class='text-warning'>Stock Quantity:</div>"+
       			"<div class='text-white'>"+product.getStockQuantity()+"</div>"+
                 "<div class='text-warning'>Product Category: </div>"+
       			"<div class='text-white'>"+product.getProductCategory()+"</div>"+
		"</div>");
%>
<br>
<a class="btn btn-warning" href='../CA2/DeleteProduct?productId=<%=productId%>'>Confirm Delete!</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>