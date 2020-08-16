<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="valueBean.PaymentDetails" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./CA1/css/Payment.css?v=1">

<script type="text/javascript">
	var today = new Date();
	document.getElemendById("expDt").min = today.getFullYear()+"-"+(today.getMonth+1)+"-"today.getDate();
</script>
</head>
<body>
<%

	double totalPrice = (double)session.getAttribute("totalPrice");
	
	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate now = LocalDate.now();
	LocalDate tomorrow = now.plusDays(1);
	String tomorrowStr = dtf.format(tomorrow);
%>

<div class="d-flex justify-content-center text-center text-white">
	
<form action="./Pay">

<h1 class="text-warning">Checkout</h1>
		<input type='hidden' name='orderId' value=''>

<input type="text" name="ccName" placeholder="Name on Credit Card" required>
<input type="text" name="ccNum" minlength="16" maxlength="16" pattern="\d*" placeholder="Credit Card Number" required><br>
<label>Credit Card Type</label>
<select name="CCType" required>
	<option value="AE">American Express</option>
	<option value="AEC">American Express Corporate</option>
	<option value="ABC">Australian BankCard</option>
	<option value="DC">Diners Club</option>
	<option value="DCV">Discover</option>
	<option value="JCB">JCB</option>
	<option value="MC">MasterCard</option>
	<option value="VS">Visa</option>
	<option value="DPBS">American Express</option>
</select>
<input type="text" name="cvv" minlength="3" maxlength="3" pattern="\d*" placeholder="CVV code" required>
<input id="expDt" type="date" name="expDt" min="<%=tomorrowStr%>" required>

<input type="checkbox" name="save" value="save">
<label for="save">Save your payment details</label><br>
 
<label>Price to Pay (GST included): <%= totalPrice %></label>
<input type="submit" class="submit bg-warning" value="Pay Now!"><br><br>
<a href="./GoHome" class="submit bg-warning" >Pay Later</a><br><br>

</form>
</div>

<%
	ArrayList<PaymentDetails> payments = (ArrayList<PaymentDetails>)request.getAttribute("payments");
	if(payments.size() >0){
		for(PaymentDetails payment : payments){
			out.print("<div class='d-flex justify-content-center text-center text-white'><form action='./PayByPD'>"+
						"<label>Credit Card Owner : "+payment.getCcName()+"</label><br>"+
						"<label>Credit Card Number : "+payment.getCcNum()+"</label><br>"+
						"<label>Credit Card Type : "+payment.getCcType()+"</label><br>"+
						"<label>Expiry Date : "+payment.getCcExpDate()+"</label><br>"+
						"<input type='submit' value='Pay'>"+
					"</form></div>");
		}
	}
	
%>
	





</body>
</html>