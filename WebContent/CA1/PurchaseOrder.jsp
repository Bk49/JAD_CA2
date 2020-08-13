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
</head>
<body>
<%
	int orderId = Integer.parseInt(request.getParameter("orderId"));
	ArrayList<OrderDetails> orderDetailsAry = (ArrayList<OrderDetails>)request.getAttribute("orderDetails");
	if(orderDetailsAry.size() == 0){
		out.print("this order is empty");
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
<%
	String status = (String)request.getAttribute("status");
	if(status.equals("Pending")){		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1);
		String tomorrowStr = dtf.format(tomorrow);
		
		out.print("<form action='./Pay'>"+
		"<input type='hidden' name='orderId' value='"+orderId+"'"+
				"<input type='text' name='ccName' placeholder='Name on Credit Card' required>"+
		"<input type='text' name='ccNum' minlength='16' maxlength='16' pattern='\\d*' placeholder='Credit Card Number' required>"+
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
</body>
</html>