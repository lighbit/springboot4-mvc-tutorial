<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizza JSP View</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>NAME</td>
			<td>Flavor</td>
			<td>Toppings</td>
		</tr>
		<tr>
			<td>${pizza.name}</td>
			<td>${pizza.flavor}</td>
			<td><c:forEach var="item" items="${pizza.toppings}">
					<c:out value="${item}" />&nbsp; 
                </c:forEach></td>
		</tr>
	</table>
</body>
</html>