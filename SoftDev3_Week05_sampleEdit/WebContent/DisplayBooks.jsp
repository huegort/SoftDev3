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
<h3>${message }</h3>
<a href="CreateBookFormServlet?id=${book.id }">
					Create New Book
				</a>
<h1>all the books</h1>
<table>
	<tr>
		<th>
			title
		</th>
		<th>
			author
		</th>
		<th>
			published
		</th>
		<th>
			genre
		</th>
		<th>
			delete
		</th>
		</tr>
	<c:forEach var="book" items="${books }">
		<tr>
			<td>
				<a href="EditBookFormServlet?id=${book.id }">
					${book.title}
				</a>
			</td>
			<td>
				${book.author}
			</td>
			<td>
				<fmt:formatDate type="date" 
						pattern="dd-MMM-yyyy" 
						value="${book.datePublished}" />
			</td>
			<td>
				${book.genre}
			</td>
			<td>
				<a href="DeleteBookServlet?id=${book.id }">
					Delete
				</a>
			</td>
		</tr>	
	</c:forEach>

</table>

</body>
</html>