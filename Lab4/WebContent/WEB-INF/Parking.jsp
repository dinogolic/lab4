<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rezervacija</title>
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
 <form:form id="loginForm" method="post" action="rezervacija" modelAttribute="rezervacijaBean">
 <p>"${test}"</p>
 <select name="grad">
          <c:forEach items="${gradovi}" var="item" >
            <option value="${item.key}">${item.value}</option>
          </c:forEach>
        </select>

<input type="submit" value="Dalje" />
</form:form>


	</div>
</body>
</html>
