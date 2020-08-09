<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="valueBean.ProductDetails" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Searched Products</title>
</head>
<body>
<%
	ArrayList<ProductDetails> products = (ArrayList<ProductDetails>)request.getAttribute("products");
	if(products == null){
		out.print("No products found!");
	}else{
		for (ProductDetails product : products) {
   			out.print(       					
        "<div class=\"col mb-4\">" +
          "<div class=\"card cardProduct \">" +
		      "<a href='./GoProductDetail?productId="+product.getProductId()+"'>"+
                "<img src='./CA1"+product.getImageLocation()+"' class=\"card-img-top mx-auto\" alt=\"...\">" +
            "<div class=\"card-body \">" +
                "<h5 class=\"card-title\">"+product.getProductName()+"</h5>" +
                "<p class=\"card-text\">"+String.format("%.2f", product.getCostPrice())+"</p>" +
    		  "</a>" +
            "</div>"+
          "</div>"+
        "</div>");
   		}
	}
%>
</body>
</html>