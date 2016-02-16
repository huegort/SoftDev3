<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
todays date is <fmt:formatDate type="date" pattern="dd-MMM-yyyy" value="<%=new java.util.Date()%>" />
		
	<c:choose>
	    <c:when test="${isEdit}">
	      <h1> edit book</h1>
			<form action="EditBookServlet" method="get">
	    </c:when>
	    <c:otherwise>
	        <h1> Create book</h1>
			<form action="CreateBookServlet" method="get">
	    </c:otherwise>
	</c:choose>
	<input type="hidden" name="id" value="${book.id }">
	title <input type="text" name="title" value="${book.title }"><br/>
	author <input type="text" name="author" value="${book.author }"><br/>
	price <input type="number" name="price" value="${book.price }"><br/>
	date published <input type="date" name="datePublished" value="${book.datePublished }"><br/>
	genre <input type="text" name="genre" value="${book.genre }"><br/>
	<input type="submit">
</form>

</body>
</html>