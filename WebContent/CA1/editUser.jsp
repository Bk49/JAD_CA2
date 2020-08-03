<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="java.sql.*" %>
       <%@page import="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/addProduct.css">
</head>
<body>
<%
UserDetails user = (UserDetails)request.getAttribute("user");
int userId = user.getUserId();
String name = user.getName();
String pwd = user.getPwd();
String email = user.getEmail();
String role = user.getRole();
String pfp = user.getPfp();
String address = user.getAddress();
String phoneNo = user.getPhoneNo();
%>
<div class="d-flex justify-content-center text-center">
<h2 class="text-warning">Edit User</h2>
<form action="./EditUser">
	<input type="hidden" name="userId" value="<%= userId %>"/>

    <div class="form-group">
      <label>User Name</label><br>
		<input type="text" name="name" value="<%= name %>" required/>
    </div>
  <div class="form-group">
    <label>Password</label><br>
		<input  type="text" name="pwd" value="<%= pwd %>" required/>
  </div>
  
  <div class="form-group">
    <label>Email</label><br>
		<input  type="text" name="email" value="<%= email %>" required/>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Role</label><br>
      <input type="text" name="role"  value="<%= role %>" required/>
    </div>
     <div class="form-group col-md-6">
      <label>Profile Picture Path</label><br>
      <input type="text" name="pfp"  value="<%= pfp %>"/>
    </div>
   
  </div>
  
  <div class="form-group">
    <label>Address</label><br>
		<input  type="text" name="address" value="<%= address %>"/>
  </div>
  
    <div class="form-group">
    <label>Phone Number</label><br>
		<input  type="text" name="phoneNo" value="<%= phoneNo %>"/>
  </div>
  
	<input type="submit" class="submit bg-warning" value="EDIT USER"/>
</form>
</div>
<script>
</script>
</body>
</html>