<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@page import="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deleting User</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/addProduct.css">
</head>
<body>
<div class="container text-center">
<%
UserDetails user = (UserDetails)request.getAttribute("user");

out.print("<div><h1 class='text-warning'>Are you sure you want to delete this User?</h1>"+
       	  		"<img src='."+user.getPfp()+"'alt='image'>"+
               	"<div class='text-warning'>User Name:</div>"+
       		  	"<div class='text-white'>"+user.getName()+"</div>"+
                 "<div class='text-warning'>Password</div>"+
       			"<div class='text-white'>"+user.getPwd()+"</div>"+
                 "<div class='text-warning'>Email: </div>"+
       			"<div class='text-white'>"+user.getEmail()+"</div>"+
                 "<div class='text-warning'>Role: </div>"+
       			"<div class='text-white'>"+user.getRole()+"</div>"+
                 "<div class='text-warning'>Profile Picture Path: </div>"+
       			"<div class='text-white'>"+user.getPfp()+"</div>"+
                 "<div class='text-warning'>Address:</div>"+
       			"<div class='text-white'>"+user.getAddress()+"</div>"+
                 "<div class='text-warning'>Phone Number: </div>"+
       			"<div class='text-white'>"+user.getPhoneNo()+"</div>"+
		"</div>");
%>
<br>
<a class="btn btn-warning" href='../CA2/DeleteUser?userId=<%= user.getUserId() %>'>Confirm Delete!</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>