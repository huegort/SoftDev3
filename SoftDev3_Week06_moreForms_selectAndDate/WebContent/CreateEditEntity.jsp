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
<c:choose>
	    <c:when test="${isEdit}">
	      <h1> Edit book</h1>
			
	    </c:when>
	    <c:otherwise>
	        <h1> Create book</h1>
			
	    </c:otherwise>
	</c:choose>


<form action="CreateEditEnityServlet" method="get">

	<input type="hidden" name="id" value="${entity.id }">
	<select name="genre">
		<c:forEach var="current" items="${genres }">
			<option value="${current }" 
				<c:if test="${current.equals(entity.genre) }">
					selected
				</c:if>
			
			>${current }</option>
		</c:forEach>
	
	</select>
	<input type="submit">
</form>


</body>
</html>