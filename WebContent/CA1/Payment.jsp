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
<label></label>

<label>Price to Pay (GST included): <%= totalPrice %>></label>
<input/>
</form>
</body>
</html>