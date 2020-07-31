<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./CA1/css/CustomerRegistration.css">
</head>
<body>
<div class="d-flex justify-content-center text-center">

	<form action="./InsertUser" method="post"><br>
		<h2 class="text-warning">SHOPMANIA REGISTER</h2>
		<input type="text" name="fname" placeholder="First Name" required/>
		<input type="text" name="lname" placeholder="Last Name" required/>
		<input type="text" name="email" placeholder="Email" required/>
		<input type="password" name="pwd" placeholder="Password" required/>
		<input type="password" name="pwdc" placeholder="Confirm Password" required/>
		<input type="text" name="address" placeholder="Address"/>
		<input  type='tel' pattern='[0-9]{8,14}' name="phoneNo" placeholder="Phone Number"/>
			<%
			String errorCode = request.getParameter("errorCode");
				if("invalidPw".equals(errorCode)){
				out.print("<p class=\"text-danger\">Your password inputs in both fields does not tally!</p>");
				if("invalidEmail".equals(errorCode)){
				out.print("<p class=\"text-danger\">The email has been used!<br>Server error!</p>");
				}
			}
			%>;
		<input type="submit" class="submit bg-warning" value="SUBMIT"/>
		<p class="text-white">Already have an account? <a href="./CA1/Login.jsp">Sign in here</a></p>
		

			
	</form>

</div>

</body>
</html>
