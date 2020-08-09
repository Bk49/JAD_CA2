<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="valueBean.Order" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase History</title>
</head>
<body>
<%
	ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
	for(Order order : orders){
		out.print(
			"<div>"+
				"Order ID : "+order.getOrderId()+
				"<br>Date of Order : "+order.getOrderDate()+
				"<br>Status : "+order.getStatus()+
				"<br>Total Price : "+order.getTotalPrice()+
				"<br><a href='./GoPurchaseOrder?orderId="+order.getOrderId()+"'>Check Order Details</a>"+
			"</div>"
		);
	}
%>
</body>
</html>