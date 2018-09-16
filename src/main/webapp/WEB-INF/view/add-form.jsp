<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>Add Form</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="/resources/static/header-footer.css" />
	<style>
		.error1 {
			color: red
		}
	</style>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h3>
				<a class="start-page-link" href="/">English Word Anagramator</a>
			</h3>
		</div>
	</div>

	<div class="container">
		<br>
		<br>
		<p>
			Enter below all matching anagrams for your query <strong>"${theUserInput}"</strong>.
		</p>
		<p>Separate your words by a whitespace and click the "Add" button.</p>
		<p>Attention: before adding your anagrams to the database, all
			words will get an appropriate validation.</p>

		<c:url var="showUserInput" value="/validateNewEntry">
			<c:param name="userInput" value="${theUserInput}" />
		</c:url>

		<form:form action="${showUserInput}" modelAttribute="theUserEntry">
			<form:input path="userWords" />
			<form:errors path="userWords" cssClass="error1" />
			<br>
			<br>
			<input type="submit" value="Submit" width="300" />
		</form:form>

	</div>
	
	<div id="wrapper">
		<div id="bottom">
			<h5>@Copyright 2018 dream-tree</h5>
		</div>
	</div>
</body>
</html>