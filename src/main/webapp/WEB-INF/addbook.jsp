<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add a Book</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Add a book, <c:out value="${ userName }"/></h1>
		<p>userID:<c:out value="${ userId }"/> </p>
		<a href="/dashboard">back to the shelves</a>
		<form:form action="/books/add" modelAttribute="book" method="post">
			<form:input path="user" type="hidden" value="${ userId }"></form:input>
			<form:label path="title" for="title" class="form-label">Title</form:label>
			<form:errors path="title" class="text-danger"/>
			<form:input path="title" type="text" id="title" class="form-control"/>
			<form:label path="author" for="author" class="form-label">Author</form:label>
			<form:errors path="author" class="text-danger"/>
			<form:input path="author" type="text" id="author" class="form-control"/>
			<form:label path="thoughts" for="thoughts" class="form-label">My thoughts</form:label>
			<form:errors path="thoughts" class="text-danger"/>
			<form:textarea path="thoughts" id="thoughts" rows="5" class="form-control"/>
			<input type="Submit" value="Add book" class="btn btn-secondary my-2"/>
		</form:form>
	</div>
</body>
</html>