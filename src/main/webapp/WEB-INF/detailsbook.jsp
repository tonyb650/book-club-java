<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title><c:out value="${ book.title }"/></title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container my-5">
		<div class="card" style="width: 50rem;">
		  <div class="card-body">
		    <h5 class="card-title border-bottom"><c:out value="${ book.title }"/></h5>
		    <c:if test="${ book.user.id == userId }">
		    	<h6 class="card-subtitle mb-2 text-body-secondary">You read <strong><em><c:out value="${ book.title }"/></em></strong> by <c:out value="${ book.author }"/>.</h6>
		    	<h6 class="card-subtitle mb-2 text-body-secondary">Here are your thoughts:</h6>
			</c:if>
			<c:if test="${ book.user.id != userId }">
		    	<h6 class="card-subtitle mb-2 text-body-secondary"><c:out value="${ book.user.userName }"/> read <strong><em><c:out value="${ book.title }"/></em></strong> by <c:out value="${ book.author }"/>.</h6>
		    	<h6 class="card-subtitle mb-2 text-body-secondary">Here are <c:out value="${ book.user.userName }"/>'s thoughts:</h6>
			</c:if>
		    <p class="card-text border-top border-bottom"><c:out value="${ book.thoughts }"/></p>
		    <c:if test="${ book.user.id == userId }">
		    	<div class="row">
		    		<div class="col d-flex">
		    			<a href="/books/${book.id}/edit" class="btn btn-link">edit</a>
						<form action="/books/${book.id}/delete" method="post"> <input type="submit" class="btn btn-link" value="delete"/></form>
		    		</div>
		    	</div>
			</c:if>
			<div class="row">
			    <a href="/dashboard" class="card-link">back to the shelves</a>
			</div>
		  </div>
		</div>
		<p class="small">Currently logged in: <c:out value="${ userName }"/></p>
	</div>
</body>
</html>