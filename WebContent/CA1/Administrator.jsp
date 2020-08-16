<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import ="java.sql.*"%>
<%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<base href="<%= request.getContextPath() %>/CA1/" target="_self">
<link rel="stylesheet" type="text/css" href="./css/Administrator.css">
</head>
<body>
<%
UserDetails user = (UserDetails)session.getAttribute("user");

String name="";
String email="";
String pfp="";
String address="";
String phoneNo = "";

try{
	if(user.getRole().equals("M")) response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}catch(Exception e){
	 response.sendRedirect("./CA1/errorPage.jsp?type=AccessDenied");
}


if(user!=null){
	name= user.getName();
	email= user.getEmail();
	pfp= user.getPfp();
	address= user.getAddress();
	phoneNo= user.getPhoneNo();
}
%>


<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
  		    		      <li class="nav-item active">
    		    <a class="nav-link" href="./Administrator.jsp">Administrator <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%= request.getContextPath() %>/GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%= request.getContextPath() %>/GoDiscountTable">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%= request.getContextPath() %>/GoProductTable">Edit Products<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%= request.getContextPath() %>/GoUserTable">Edit Users<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%= request.getContextPath() %>/Reports">Reports<span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
  </div>
</nav>

  
<div class="container text-center ">
	<div class="row">

	  <div class="card" style="width: 18rem;" >
	  	     		   <h3 class="text-white">Administrator:</h3>
      		<img src=".<%=pfp %>" class="card-img-top" alt="Card image cap">
     	 <div class="card-body">
        	   <p class="card-title text-white">Name: <%=name %></p>
       	       <p class="card-title text-white">Email: <%=email %></p>
       	       <p class="card-title text-white">Address: <%=address %></p>
      	       <p class="card-title text-white">Phone No: <%=phoneNo %></p>      	       
         </div>
	    </div>
	    

	</div>
	
</div>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>