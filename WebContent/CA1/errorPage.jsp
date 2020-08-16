<%-- 
  - Author(s): LIEW KHYE LERK(P1937000), ERIC NG YONG WEI(P1940211);
  - Date: 29/5/20;
  --%>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/addProduct.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
</head>
<body>
<br>
<div class="container text-center">
	<div class="row">
		<div class="col-12">
			     <i class="fa fa-warning" style="font-size:150px;color:yellow"></i><br>
		</div>	
	</div>
	<div class="row">
		<div class="col-12">
			<h1 class="text-warning"></h1>
			<%
			String type = request.getParameter("type");
				if((type) == null){
					out.print("<h2 class='text-warning'>An Error Has Occurred!</h2><br>");
			}else if("AccessDenied".equals(type)){
				out.print("<h2 class='text-warning'>Access Denied!</h2><br>");
			}
			%>
		</div>
	</div>
    <div class="row">
    	<div class="col-12">
    	<%
    	 if("AccessDenied".equals(type)){
				out.print("<a class='btn btn-warning' href='"+request.getContextPath()+"/GoHome'>Back to Home</a>");
		}

			%>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>