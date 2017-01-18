<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registracija</title>
<style>

#login-box {
	width: 350px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body style="background-image:url(http://www.planwallpaper.com/static/images/10e39f13ddfb80570f3e44fb2016cb76.jpg);">
<div id="login-box">
 <form:form id="loginForm" method="post" action="registracija" modelAttribute="registracijaBean">
 <form:label path="userid">Unesite ime</form:label>
<form:input id="ime" name="userid" path="ime" /><br>
<form:label path="prezime">Unesite prezime</form:label>
<form:input id="prezime" name="prezime" path="prezime" /><br>
<form:label path="name">Unesite korisnicko ime</form:label>
<form:input id="name" name="name" path="name" /><br>
<form:label path="pwd">Unesite lozinku</form:label>
<form:password id="pwd" name="pwd" path="pwd" /><br>
<input type="submit" value="Registracija" />
</form:form>


	</div>
</body>
</html>
