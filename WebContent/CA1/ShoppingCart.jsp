<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="valueBean.ProductCart" %>
<%@page import="valueBean.ProductDetails" %>
<%@page import="valueBean.UserDetails" %>
<%@page import="valueBean.DiscountDetails" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/productDetail.css?v=1">
<link rel="stylesheet" type="text/css" href="./CA1/css/cart.css?v=2">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom navbar-dark ">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  		  <ul class="navbar-nav mr-auto">
    		  <li class="nav-item active">
    		    <a class="nav-link" href="<%=request.getContextPath()%>/GoHome">Home <span class="sr-only">(current)</span></a>
    		  </li>
    		  <li class="nav-item dropdown">
    		    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   		        Categories
    		    </a>
   		     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<%
try{
ArrayList<String> categories = (ArrayList<String>)request.getAttribute("productCategories");
for (String category : categories) {    
    // Style this line of code!
    out.print("<a href='"+request.getContextPath()+"/GoProductListing?category="+category+"' class='dropdown-item'>"+category.toUpperCase()+"</a><br>");
}

}catch(Exception e){
	System.out.print("it works");
	RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath()+"/GoHome");
	rd.forward(request, response);
	}

%>
    		 </div>
  		    </li>
  		      <li class="nav-item">
    		    <a class="nav-link" href="<%=request.getContextPath()%>/GoShoppingCart">Cart <span class="sr-only">(current)</span></a>
    		  </li>
 		   </ul>
   		 <form action="./SearchProduct" class="form-inline my-2 my-lg-0">
   		   <input class="form-control mr-sm-2" type="search" name="searchStr" placeholder="Search" aria-label="Search">
    		  <button class="btn btn-warning my-2 my-sm-0" type="submit">Search</button>
         </form>
   		 <ul  class="navbar-nav mr-right dropleft" style="margin-left:20px" >
         		 <li class="nav-item dropdown ">
        <%
	if(session.getAttribute("user")!=null){

		UserDetails user = (UserDetails)session.getAttribute("user");
	        String pfp = user.getPfp();
	        out.print("<a href='nav-link dropdown-toggle ' style='margin:0px' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><img src='"+request.getContextPath()+"/CA1/"+pfp+"' alt='userPfp' width=\"auto\" height=\"60px\"></a>");
	        out.print("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>" +
	      "<a class='dropdown-item' href='"+request.getContextPath()+"/GoProfilePage'>Profile</a>" +
	    			"<div class='dropdown-divider'></div>" +
		    	      "<a class='dropdown-item' href='"+request.getContextPath()+"/GoPurchaseHistory'>Purchase History</a>" +

	"<div class='dropdown-divider'></div>" +
	   "<a class='dropdown-item' href='"+request.getContextPath()+"/CA1/logout.jsp'>Log Out</a>" +
	 "</div>");
	}else{
		out.print("<a class=\"nav-link\" href='"+request.getContextPath()+"/GetUserDetails'>LOGIN</a>");
	}
%>

     		 </li>
      	</ul>   
  </div>
</nav>
<br><br>
<div class='container text-center text-white'>
<h1 class="text-warning">Your Cart</h1><br>

<form action="./GoPayment">

<%
	ProductCart prodCT = (ProductCart)request.getAttribute("prodCT");
DiscountDetails discount = new DiscountDetails();

discount = (DiscountDetails)request.getAttribute("discountDetails");
if(request.getParameter("discountCode") !=null && discount.getDiscountCode()!=null){
System.out.print(discount.getDiscountCode());
System.out.print(discount.getDiscountValue());
System.out.print(discount.getDiscountType());
System.out.print(discount.getUsageLimit());
}
	if(prodCT.getProducts().size() != 0){


		out.print("		<div class='row'>");
		out.print("	    <div class='col-sm text-white'>Product</div>");
		out.print("	    <div class='col-sm text-white'>Quantity</div>");
		out.print("	    <div class='col-sm text-white'>Price Each</div>");
		out.print("	    <div class='col-sm text-white'>Remove Item</div></div><hr>");



		prodCT = (ProductCart)request.getAttribute("prodCT");
		System.out.print(prodCT.getProducts());

		ArrayList<ProductDetails> products = prodCT.getProducts();
		ArrayList<Integer> quantities = prodCT.getQuantity();
		double price;
		double totalPrice = 0;
		int quantity;
		
		for(int i =0;i < products.size();i++){
			price = products.get(i).getRetailPrice();
			quantity = quantities.get(i);
			out.print(
					"<div class='row'>"+
				    "<div class='col-sm align-middle'>"+
				    "<img src='./CA1"+products.get(i).getImageLocation()+"' style='max-height: 100px; width:auto 	'>"		+
				    		products.get(i).getProductName()+
				  "  </div>"+
				 "   <div class='col-sm align-middle'>"+
						 quantity+
				  " </div>"+
				  "  <div class='col-sm align-middle'>$"+
						  price+

				   " </div>"+
					"  <div class='col-sm'>"+
					"<a href='./RemoveFromCart?productId="+products.get(i).getProductId()+"'>&#10060;</a>"+
					 " </div>"+

				"  </div><hr>"
				  
					);
			totalPrice += price * (double)quantity;
		}
	
		if(request.getParameter("discountCode") ==null){
		double gst = (Math.round(totalPrice * 0.07 *100.0)) / 100.0 ;
		out.print("Total Price: "+String.format("%.2f",totalPrice));
		out.print("<br>GST: "+String.format("%.2f",gst));
		out.print("<br>Total Price after GST: "+String.format("%.2f",(totalPrice + gst)));
		out.print("<br><input type='submit'  class=' btn  btn-success' value='Proceed to Checkout'>");
		
		session.setAttribute("totalPrice", (totalPrice + gst));
		}else if(request.getParameter("discountCode") !=null && discount.getDiscountCode()==null){
			double gst = (Math.round(totalPrice * 0.07 *100.0)) / 100.0 ;
			out.print("<p class='text-danger'>Invalid Discount Code!</p>");

			out.print("Total Price: "+String.format("%.2f",totalPrice));
			out.print("<br>GST: "+String.format("%.2f",gst));
			out.print("<br>Total Price after GST: "+String.format("%.2f",(totalPrice + gst)));
			out.print("<br><input type='submit'  class=' btn  btn-success' value='Proceed to Checkout'>");
			
			session.setAttribute("totalPrice", (totalPrice + gst));
			}else if(discount.getDiscountType().equals("DIRECT")){
			double newTotal = totalPrice -  discount.getDiscountValue();
			if (newTotal < 25) newTotal =25;
			double gst = (Math.round(newTotal * 0.07 *100.0)) / 100.0 ;
			
			out.print("DISCOUNT CODE: "+ discount.getDiscountCode());
			out.print("<br>DISCOUNT TYPE: "+ discount.getDiscountType());
			out.print("<br>DISCOUNT VALUE: $"+ discount.getDiscountValue());

			out.print("<br>	Total Price: $<strike class='text-danger'>"+String.format("%.2f",totalPrice)+"</strike>" + String.format("%.2f", newTotal));
			out.print("<br>GST: "+String.format("%.2f",gst));
			out.print("<br>Total Price after GST: $"+String.format("%.2f",(newTotal + gst)));
			out.print("<br><input type='submit'  class=' btn  btn-success' value='Proceed to Checkout'>");
			
			session.setAttribute("totalPrice", (newTotal + gst));
		}else if(discount.getDiscountType().equals("PERCENTAGE")){
			double newTotal = totalPrice - totalPrice *  discount.getDiscountValue()/100;
			if (newTotal < 25) newTotal =25;
			double gst = (Math.round(newTotal * 0.07 *100.0)) / 100.0 ;
			
			out.print("DISCOUNT CODE: "+ discount.getDiscountCode());
			out.print("<br>DISCOUNT TYPE: "+ discount.getDiscountType());
			out.print("<br>DISCOUNT VALUE: "+ discount.getDiscountValue()+"%") ;

			out.print("<br>	Total Price: $<strike class='text-danger'>"+String.format("%.2f",totalPrice)+"</strike>" + String.format("%.2f", newTotal));
			out.print("<br>GST: "+String.format("%.2f",gst));
			out.print("<br>Total Price after GST: $"+String.format("%.2f",(newTotal + gst)));
			out.print("<br><input type='submit'  class=' btn  btn-success' value='Proceed to Checkout'>");
			
			session.setAttribute("totalPrice", (newTotal + gst));
		}
	}else{
		out.print("<h2 class='text-danger'>Shopping cart is empty!</h2>");
	}
	
%>
</form>
<%


if(prodCT.getProducts().size() != 0){

	out.print(
				"<p class=\"text-warning\">Discount Code:</p>"+
	
				"<form  action=\"./GoShoppingCart\" method='GET'  >"+
				"<input type=\"search\" name='discountCode' placeholder=\"DISCOUNT CODE\" aria-label=\"Search\" style='width:200px'/>\n<button class=\"btn btn-warning my-2 my-sm-0\" type=\"submit\">APPLY</button></form><br>*minimum product price is $25");
				
				}%>
</div>

 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 
</body>
</html>