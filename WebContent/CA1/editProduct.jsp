<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="java.sql.*" %>
       <%@page import="valueBean.ProductDetails" %>
       <%@ page import="java.util.ArrayList" %><%@page import ="valueBean.UserDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Product</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/addProduct.css">
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
<%
ProductDetails product = (ProductDetails)request.getAttribute("product"); 

int productId = product.getProductId();
String productName = product.getProductName();
String briefDescription= product.getBriefDescription();
String detailDescription= product.getDetailDescription();
double costPrice= product.getCostPrice();
double retailPrice= product.getRetailPrice();
int stockQuantity= product.getStockQuantity();
String productCategory= product.getProductCategory();
String imageLocation= product.getImageLocation();

%>
<div class="d-flex justify-content-center text-center">
<h2 class="text-warning">Edit Product</h2>
<form action='./EditProduct' method="post" enctype="multipart/form-data">
	<input type="hidden" name="productId" value="<%=productId%>"/>

    <div class="form-group">
      <label>Name of Product</label><br>
		<input type="text" name="productName" value="<%=productName%>" required/>
    </div>
  <div class="form-group">
    <label>Brief Description</label><br>
		<textarea rows="4" cols="100" name="briefDescription" required><%=briefDescription%></textarea>
  </div>
  
  <div class="form-group">
    <label>Detailed Description</label><br>
		<textarea rows="10" cols="100" name="detailDescription" ><%=detailDescription%></textarea>   
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Cost Price</label><br>
      <input type="number" name="costPrice"  value="<%=costPrice%>" step="0.01" required/>
    </div>
     <div class="form-group col-md-6">
      <label>Retail Price</label><br>
      <input type="number" name="retailPrice"  value="<%=retailPrice%>" step="0.01" required/>
    </div>
   
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Stock Quantity</label><br>
	  <input type="number" name="stockQuantity" value="<%=stockQuantity%>" required />
    </div>
     <div class="form-group col-md-6">
      <label>Image Upload</label><br>
	  <input type="file" name="guru_file" size="50" accept="image/*" />
    </div>
   
  </div>
  
      <div class="form-group">
      <label>Product Category</label>
      <select id="productCategory" name="productCategory" class="form-control" required>
		<option></option>
        <%
        ArrayList<String> categories = (ArrayList<String>)request.getAttribute("productCategories");
		for (String category : categories) {    
    	out.print("<option>"+category.toUpperCase()+"</option>");
		}
  		%> 
      </select>
    </div>
    
	<input type="submit" class="submit bg-warning" value="EDIT PRODUCT"/>
</form>
</div>
<script>
document.getElementById("productCategory").value ="<%=productCategory.toUpperCase()%>"
</script>
</body>
</html>