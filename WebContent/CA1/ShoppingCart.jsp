<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="valueBean.ProductCart" %>
<%@page import="valueBean.ProductDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
</head>
<body>
<form action="./GoPayment">
<%
	ProductCart prodCT = null;

	if(request.getAttribute("prodCT") != null){
		prodCT = (ProductCart)request.getAttribute("prodCT");
		
		ArrayList<ProductDetails> products = prodCT.getProducts();
		ArrayList<Integer> quantities = prodCT.getQuantity();
		double price;
		double totalPrice = 0;
		int quantity;
		
		for(int i =0;i < products.size();i++){
			price = products.get(i).getCostPrice();
			quantity = quantities.get(i);
			out.print(
					"<div>"+
						"<div>"+products.get(i).getProductName()+"</div>"+
							"<div>Quantity: "+quantity+"</div>"+
							"<div>Price each: "+price+
							"<a href='./RemoveFromCart?productId="+products.get(i).getProductId()+"'>Remove Item</a>"+
					"</div>"
					);
			totalPrice += price * (double)quantity;
		}
		
		double gst = (Math.round(totalPrice * 0.07 *100.0)) / 100.0 ;
		out.print("Total Price: "+String.format("%.2f",totalPrice));
		out.print("GST: "+String.format("%.2f",gst));
		out.print("Total Price after GST: "+String.format("%.2f",(totalPrice + gst)));
		out.print("<input type='submit' value='Proceed to Checkout'/>");
	}else{
		out.print("Shopping cart is empty!");
	}
	
	
%>
</form>

</body>
</html>