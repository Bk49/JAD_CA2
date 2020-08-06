<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<%
	double totalPrice = (double)request.getAttribute("totalPrice");
%>
<form action="../MakePayments">
<input type="hidden" value="<%= totalPrice %>"/>
<input type="number" name="ccNum" placeholder="Credit Card Number" required>
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

 <input type="checkbox" name="save" value="save">
 <label for="save">Save your payment details</label><br>
<label>Price to Pay (GST included): <%= totalPrice %>></label>
<input/>
</form>
</body>
</html>