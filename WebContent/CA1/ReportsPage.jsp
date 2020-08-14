<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.lang.*" %>
    <%@page import="java.util.ArrayList" %>    
    <%@page import="valueBean.UserDetails" %> 
        <%@page import="valueBean.ProductDetails" %> 
            <%@page import="valueBean.Order" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reports Page</title>
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
    		    <a class="nav-link" href="./CA1/Administrator.jsp">Administrator <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoDiscountTable">Edit Discounts<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoProductTable">Edit Products<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./GoUserTable">Edit Users<span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item active">
    		    <a class="nav-link" href="./Reports">Reports<span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
  </div>
</nav>

<div class="container text-center">
<h1><%=	request.getAttribute("title")%></h1>

<table class="table table-striped table-hover" > <!-- All products will be pushed into this table -->
<tr>
<%    
Object report = request.getAttribute("report");

	
if(report== null)report = "";

if(report.equals("customer")){
	
	out.print("<th>No.</th>");
	out.print("<th>Name</th>");
	out.print("<th>Email</th>");
	out.print("<th>Role</th>");
	out.print("<th>Address</th>");
	out.print("<th>Phone Number</th>");

}else if(request.getAttribute("stock")!= null){
	out.print("<th>No.</th>");
	out.print("<th>Product Name</th>");
	out.print("<th>Cost Price</th>");
	out.print("<th>Retail Price</th>");
	out.print("<th>Stock Quantity</th>");
	out.print("<th>Category</th>");

}else if(report.equals("product2")){
	out.print("<th>No.</th>");
	out.print("<th>Product Name</th>");
	out.print("<th>Cost Price</th>");
	out.print("<th>Category</th>");
	out.print("<th>Total Sales</th>");
	out.print("<th>Quantity Sold</th>");


}else if(report.equals("order1")){
	out.print("<th>No.</th>");
	out.print("<th>User Id</th>");
	out.print("<th>Customer Name</th>");
	out.print("<th>Email</th>");
	out.print("<th>Phone Number</th>");
	out.print("<th>Total Purchased</th>");

}else if(report.equals("order2")){
	out.print("<th>No.</th>");
	out.print("<th>Order Id</th>");
	out.print("<th>Date</th>");
	out.print("<th>Total Price</th>");
	out.print("<th>Discount Id</th>");
	out.print("<th>Customer Id</th>");

}else if(request.getAttribute("product")!=null){
	out.print("<th>No.</th>");
	out.print("<th>User Id</th>");
	out.print("<th>Customer Name</th>");
	out.print("<th>Email</th>");
	out.print("<th>Phone Number</th>");
	out.print("<th>Quantity Purchased</th>");

}
%>
</tr>
<%    
int pg= (int)request.getAttribute("pg");
int count = pg*10-10+1;
if(report.equals("customer")){

ArrayList<UserDetails> users = (ArrayList<UserDetails>)request.getAttribute("users");

	for(UserDetails user: users){
       out.print("<tr>"+
      "<td>"+count+"</td>"+
      "<td>"+user.getName()+"</td>"+
       "<td>"+user.getEmail()+"</td>"+
      "<td>"+user.getRole()+"</td>"+
       "<td>"+user.getAddress()+"</td>"+
     "<td>"+user.getPhoneNo()+"</td>"+
       "</tr>");
       
       count++;
	}
	}else if(request.getAttribute("stock")!= null){
		ArrayList<ProductDetails> products = (ArrayList<ProductDetails>)request.getAttribute("products");
		System.out.print(products);
		for(ProductDetails product: products){
	       out.print("<tr>"+
	       "<td>"+count+"</td>"+
	       "<td>"+product.getProductName()+"</td>"+
	       "<td>"+String.format("%.2f",product.getCostPrice())+"</td>"+
	       "<td>"+String.format("%.2f",product.getRetailPrice())+"</td>"+
	       "<td>"+product.getStockQuantity()+"</td>"+
	       "<td>"+product.getProductCategory()+"</td>"+
	       "</tr>");
	       count++;
		}
	}else if(report.equals("product2")){
			ArrayList<ProductDetails> products = (ArrayList<ProductDetails>)request.getAttribute("products");
			System.out.print(products);
			for(ProductDetails product: products){
		       out.print("<tr>"+
		       "<td>"+count+"</td>"+
		       "<td>"+product.getProductName()+"</td>"+
		       "<td>"+String.format("%.2f",product.getCostPrice())+"</td>"+
		       "<td>"+product.getProductCategory()+"</td>"+
		       "<td>"+String.format("%.2f",product.getRetailPrice())+"</td>"+
		       "<td>"+product.getStockQuantity()+"</td>"+
		       "</tr>");
		       
		       count++;
			}
	}else if(report.equals("order1")){

ArrayList<UserDetails> users = (ArrayList<UserDetails>)request.getAttribute("users");

	for(UserDetails user: users){
       out.print("<tr>"+
      "<td>"+count+"</td>"+
      "<td>"+user.getUserId()+"</td>"+
      "<td>"+user.getName()+"</td>"+
      "<td>"+user.getEmail()+"</td>"+
      "<td>"+user.getPhoneNo()+"</td>"+
       "<td>"+user.getPwd()+"</td>"+
       "</tr>");
       
       count++;
		}
}else if(report.equals("order2")){

ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");

	for(Order order: orders){
       out.print("<tr>"+
      "<td>"+count+"</td>"+
      "<td>"+order.getOrderId()+"</td>"+
      "<td>"+order.getOrderDate()+"</td>"+
      "<td>"+order.getTotalPrice()+"</td>"+
      "<td>"+order.getDiscountId()+"</td>"+
       "<td>"+order.getCustomerId()+"</td>"+
       "</tr>");
       
       count++;
		}
}else if(request.getAttribute("product") != null){

	ArrayList<UserDetails> users = (ArrayList<UserDetails>)request.getAttribute("users");

	for(UserDetails user: users){
       out.print("<tr>"+
      "<td>"+count+"</td>"+
      "<td>"+user.getUserId()+"</td>"+
      "<td>"+user.getName()+"</td>"+
      "<td>"+user.getEmail()+"</td>"+
      "<td>"+user.getPhoneNo()+"</td>"+
       "<td>"+user.getPwd()+"</td>"+
       "</tr>");
       
       count++;
}
}

%>
</table>

<!-- Use this button to access to the next 10 rows in the second page -->
<div class='pagination'>
<%
	double Rows = 0;
	if(request.getAttribute("count") != null ){
		Rows = (double)request.getAttribute("count");
}
	
	
	if(Rows != 0){
        double Pages = (double)Math.ceil((double) Rows/(double)10.0);

        for(int i = 0 ; i < Pages; i++){
        	if(request.getParameter("report") != null)
     	out.print("<a  class='active' href='ReportsPage?report="+request.getAttribute("report")+"&pg="+(i+1)+"'>"+ (i+1) +"</a>");		
        	else if(request.getParameter("stock") !=null){
             	out.print("<a  class='active' href='ReportsPage?stock="+request.getAttribute("stock")+"&pg="+(i+1)+"'>"+ (i+1) +"</a>");		

        	}        	else if(request.getParameter("products") !=null){
             	out.print("<a  class='active' href='ReportsPage?products="+request.getAttribute("product")+"&pg="+(i+1)+"'>"+ (i+1) +"</a>");		

        	}
        }
	}
          
		          
%>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>