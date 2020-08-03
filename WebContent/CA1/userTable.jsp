<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.lang.*" %>
    <%@page import="java.util.ArrayList" %>    
    <%@page import="valueBean.UserDetails" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productTable.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
    		  <li class="nav-item active">
    		    <a class="nav-link" href="../CA2/GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="../CA2/GoDiscountTable">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="../CA2/GoProductTable">Edit Products<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="../CA2/GoUserTable">Edit Users<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="">Orders<span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
  </div>
</nav>

<div class="container text-center">
<h1>Users in the database</h1>

<!-- This button will allow the user to add new products into the database -->
<a class='btn btn-warning' href='./CA1/addUser.jsp'>Add new user</a><br><br>

<table class="table table-striped table-hover" > <!-- All products will be pushed into this table -->
<tr>
<th>No.</th>
<th>Name</th>
<th>Password</th>
<th>Email</th>
<th>Role</th>
<th>Profile Picture Path</th>
<th>Address</th>
<th>Phone Number</th>

<th colspan="2">User Options</th>

</tr>
<%    
int pg= (int)request.getAttribute("pg");
int count = pg*10-10+1;

ArrayList<UserDetails> users = (ArrayList<UserDetails>)request.getAttribute("users");

	for(UserDetails user: users){
       out.print("<tr>"+
      "<td>"+count+"</td>"+
      "<td>"+user.getName()+"</td>"+
       "<td>"+user.getPwd()+"</td>"+
       "<td>"+user.getEmail()+"</td>"+
      "<td>"+user.getRole()+"</td>"+
       "<td>"+user.getPfp()+"</td>"+
       "<td>"+user.getAddress()+"</td>"+
     "<td>"+user.getPhoneNo()+"</td>"+
       "<td><a class='btn btn-warning' href='./GoEditUser?userId="+user.getUserId()+"'>Edit</button></td>"+
       "<td><a class='btn btn-warning' href='./GoDeleteUser?userId="+user.getUserId()+"'>Delete</button></td>"+
       "</tr>");
       
       count++;
	}

%>
</table>

<!-- Use this button to access to the next 10 rows in the second page -->
<div class='pagination'>
<%
	double noOfUsers;
	noOfUsers = (double)request.getAttribute("userCount");
   
        double Pages = (double)Math.ceil((double) noOfUsers/(double)10.0);

        for(int i = 0 ; i < Pages; i++)
     out.print("<a  class='active' href='GoUserTable?pg="+(i+1)+"'>"+ (i+1) +"</a>");	          
		          
%>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>