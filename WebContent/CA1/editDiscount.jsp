<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import="valueBean.DiscountDetails" %><%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Discount</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/addProduct.css">
</head>
<body>
<%
try{
	UserDetails user = (UserDetails)session.getAttribute("user");
	if(user.getRole().equals("M")) response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}catch(Exception e){
	 response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}

%>
<%
DiscountDetails discount= (DiscountDetails)request.getAttribute("discount"); 

int discountId = discount.getDiscountId();
String discountCode = discount.getDiscountCode();
double discountValue = discount.getDiscountValue();
String discountType = discount.getDiscountType();
int usageLimit = discount.getUsageLimit();
int usageCount = discount.getUsageCount();

%>
<div class="d-flex justify-content-center text-center">
<h2 class="text-warning">Edit Product</h2>

<form action='./EditDiscount' method="post">

<!-- The user will not see the line 42 input field! -->
<input type="hidden" name="discountId" value="<%=discountId %>"/>

<div>
<label>Discount Code</label><br>
<input type="text" name="discountCode" value="<%=discountCode%>" required/>
</div>
<br>
<div>
<label>Discount Value</label><br>
<input type="number" step="0.01" name="discountValue" value="<%=discountValue%>" required/>
</div>
<br>
<div>
      <label class='text-white'>DISCOUNT TYPE</label>
      <select id="discountType" name="discountType" class="form-control" required>
		<option><%=discountType%></option>
		<option>DIRECT</option>
		<option>PERCENTAGE</option>

      </select>
</div>
<br>
<div>
<label>Usage Limit (Default is 0)</label>
<input type="number" name="usageLimit" value="<%=usageLimit%>"/>
</div>
<br>
<div>
<label>Number of Usage (Default is 0)</label>
<input type="number" name="usageCount" value="<%=usageCount%>"/>
</div>
<br>
<input type="submit" class="submit bg-warning" value="EDIT DISCOUNT"/>

</form>
</div>
</body>
</html>