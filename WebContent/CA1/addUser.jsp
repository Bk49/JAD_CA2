<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="java.sql.*" %>
       <%@page import="valueBean.ProductDetails" %><%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/addProduct.css">
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
<h2 class="text-warning">Add User</h2>
<form action="../AddUser" method="post">

    <div class="form-group">
      <label>User Name</label><br>
		<input type="text" name="name" value="" required/>
    </div>
  <div class="form-group">
    <label>Password</label><br>
		<input  type="text" name="pwd" value="" required/>
  </div>
  
  <div class="form-group">
    <label>Email</label><br>
		<input  type="text" name="email" value="" required/>
  </div>
  
  <div class="form-row">
          <div class="form-group  col-md-6">
      <label>Role</label>
      <select id="role" name="role" class="form-control" required>
		<option></option>
		<option>M</option>
		<option>A</option>

      </select>
    </div>
     <div class="form-group col-md-6">
      <label>Profile Picture Path</label><br>
      <input type="text" name="pfp"  value=""/>
    </div>
   
  </div>
  
  <div class="form-group">
    <label>Address</label><br>
		<input  type="text" name="address" value="" required/>
  </div>
  
    <div class="form-group">
    <label>Phone Number</label><br>
				<input  type='tel' pattern='[0-9]{8,14}' name="phoneNo" value="" />
		
  </div>
 
	<input type="submit" class="submit bg-warning" value="ADD USER"/>
</form>
</div>
<script>
</script>
</body>
</html>