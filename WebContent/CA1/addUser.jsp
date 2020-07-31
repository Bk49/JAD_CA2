<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="java.sql.*" %>
       <%@page import="valueBean.ProductDetails" %>
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

%>
<div class="d-flex justify-content-center text-center">
<h2 class="text-warning">Edit User</h2>
<form action="" method="post">

    <div class="form-group">
      <label>User Name</label><br>
		<input type="text" name="name" value="" required/>
    </div>
  <div class="form-group">
    <label>Password</label><br>
		<input  type="text" name="password" value="" required/>
  </div>
  
  <div class="form-group">
    <label>Email</label><br>
		<input  type="text" name="email" value="" required/>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Role</label><br>
      <input type="text" name="role"  value="" required/>
    </div>
     <div class="form-group col-md-6">
      <label>Profile Picture Path</label><br>
      <input type="text" name="pfp"  value="" required/>
    </div>
   
  </div>
  
  <div class="form-group">
    <label>Address</label><br>
		<input  type="text" name="address" value="" required/>
  </div>
  
    <div class="form-group">
    <label>Phone Number</label><br>
		<input  type="text" name="phoneNo" value="" required/>
  </div>
  


    
	<input type="submit" class="submit bg-warning" value="ADD USER"/>
</form>
</div>
<script>
</script>
</body>
</html>