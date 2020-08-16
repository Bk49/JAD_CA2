<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="valueBean.OrderDetails" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase Order</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./CA1/css/Payment.css?v=1">
</head>
<body>
<div class="d-flex justify-content-center text-center text-white">
<form>
<%
	int orderId = Integer.parseInt(request.getParameter("orderId"));
	ArrayList<OrderDetails> orderDetailsAry = (ArrayList<OrderDetails>)request.getAttribute("orderDetails");
	if(orderDetailsAry.size() == 0){
		out.print("<h1 class='text-white'>this order is empty</h1><br>");
	}else{
		for(OrderDetails orderDT : orderDetailsAry){
			out.print("<div>"+
						"Product Name : "+orderDT.getProductName()+
						"<br>Quantity : "+orderDT.getQuantity()+
					"</div>");
		}
	}
	
	double totalPrice = (double)session.getAttribute("totalPrice");
	String totalPriceStr = String.format("%.2f",totalPrice);
	
%>
<div>Total Price = <%= totalPriceStr%></div>
</form>
</div>
<div class="d-flex justify-content-center text-center text-white">


<%
	String status = (String)request.getAttribute("status");
	if(status.equals("Pending")){		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1);
		String tomorrowStr = dtf.format(tomorrow);
		
		out.print("<form action='./Pay'>"+"<h1 class='text-warning'>Checkout</h1>"+
		"<input type='hidden' name='orderId' value='"+orderId+"'>"+
				"<input type='text' name='ccName' placeholder='Name on Credit Card' required>"+
		"<input type='text' name='ccNum' minlength='16' maxlength='16' pattern='\\d*' placeholder='Credit Card Number' required><br>"+
		"<label>Credit Card Type</label>"+
		"<select name='CCType' required>"+
			"<option value='AE'>American Express</option>"+
			"<option value='AEC'>American Express Corporate</option>"+
			"<option value='ABC'>Australian BankCard</option>"+
			"<option value='DC'>Diners Club</option>"+
			"<option value='DCV'>Discover</option>"+
			"<option value='JCB'>JCB</option>"+
			"<option value='MC'>MasterCard</option>"+
			"<option value='VS'>Visa</option>"+
			"<option value='DPBS'>American Express</option>"+
		"</select>"+
		"<input type='text' name='cvv' minlength='3' maxlength='3' pattern='\\d*' placeholder='CVV code' required>"+
		"<input id='expDt' type='date' name='expDt' min='"+tomorrowStr+"' required>"+
		"<input type='submit' value='Pay Now!'>"+
		"</form>"
		);
	}
%>
</div>
</body>
</html>