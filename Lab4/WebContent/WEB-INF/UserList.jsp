<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista korisnika</title>
</head>
<body>

<h1>Lista korisnika</h1>
	<table border = "1" cellpadding="5" style="background-color: #ffffcc;">
		<tr>
			<td>Name:</td>

			<td>User:</td>

			<td>Password:</td>

		</tr>

		<c:forEach items="${userList}" var="user">

			<tr>

				<td>${user.name}</td>

				<td>${user.userid}</td>

				<td>${user.pwd}</td>
			</tr>



		</c:forEach>
	</table>
</body>
</html>