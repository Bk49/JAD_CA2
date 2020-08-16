<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import="valueBean.DiscountDetails" %><%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deleting discount code</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/addProduct.css">
</head>
<body>
<div class="container text-center">
<%
try{
	UserDetails user = (UserDetails)session.getAttribute("user");
	if(user.getRole().equals("M")) response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}catch(Exception e){
	 response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}

%>
<%
DiscountDetails discount = (DiscountDetails)request.getAttribute("discount");
int discountId = discount.getDiscountId();
out.print("<div class='text-warning'><h1>Are you sure you want to delete this discount code?</h1><br>"+
             	"<div class='text-warning'>Discount Code: </div>"+
     			"<div class='text-white'>"+discount.getDiscountCode()+"</div>"+
               "<div class='text-warning'>Discount Value: </div>"+
     			"<div class='text-white'>"+String.format("%.2f",discount.getDiscountValue())+"</div>"+
               "<div class='text-warning'>Discount Type: </div>"+
     			"<div class='text-white'>"+discount.getDiscountType()+"</div>"+
               "<div class='text-warning'>Usage Limit: </div>"+
     			"<div class='text-white'>"+discount.getUsageLimit()+"</div>"+
               "<div class='text-warning'>Usage Count: </div>"+
               "<div class='text-white'>"+discount.getUsageCount()+"</div>"+
"</div>");
%>
<a class="btn btn-warning" href='./DeleteDiscount?discountId=<%= discountId %>'>Confirm Delete!</a>

</div>
<br>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>