<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %><%@page import ="valueBean.UserDetails" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Discount Code</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/Login.css">
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
<div class="d-flex justify-content-center text-center">
	<form action="<%= request.getContextPath() %>/AddDiscount" method="post">
	    <h2 class="text-warning">Add Discount</h2><br>
			<input type="text" name="discountCode" placeholder="Discount Code" required/>
			<input type="number" name="discountValue" placeholder="Discount Value" step="0.01" required/>
			          <div class="form-group">
      <label class='text-white'>DISCOUNT TYPE</label>
      <select id="discountType" name="discountType" class="form-control" required>
		<option></option>
		<option>DIRECT</option>
		<option>PERCENTAGE</option>

      </select>
    </div>
			<input type="number" name="usageLimit" placeholder="Usage Limit" step="0.01" required/>
			<input type="submit" class="submit bg-warning" value="Add Discount Code"/>
	</form>
</div>

</body>
</html>