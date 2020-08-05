<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/Login.css?v=2">
<link rel="stylesheet" type="text/css" href="./CA1/css/Login.css?v=1">

</head>
<body>
<div class="d-flex justify-content-center text-center">
	<form action="./GetUserDetails" method="post"><br>
	    <h2 class="text-warning">SHOPMANIA LOGIN</h2><br>
		<input type="text" name="email" placeholder="Email" required/><br>
		<input type="password" name="pwd" placeholder="Password" required/><br>
		
					<%
								String errorCode = request.getParameter("errorCode");
				if("invalidLogin".equals(errorCode)){
					out.print("<p class=\"text-danger\">Invalid credentials! Please check again!</p>");
			}
			%>
		<input type="submit" class="submit bg-warning" value="LOGIN"/>
		<p class="text-white">Don't have an account? <a href="./CustomerRegistration">Sign up here</a></p>
	</form>
</div>

</body>
</html>