<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Book Club Dashboard</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Welcome, <c:out value="${ userName }"/></h1>
		<p><a href="/user/logout">logout (Make to method=post?)</a></p>
		<p><a href="/books/add">+ Add a book to my shelf</a></p>		
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${ books }">
					<tr>
						<td><c:out value="${ book.id }"/></td>
						<td><a href="/books/${ book.id }/detail"><c:out value="${ book.title }"/></a></td>
						<td><c:out value="${ book.author }"/></td>
						<td><c:out value="${ book.user.userName }"/></td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>