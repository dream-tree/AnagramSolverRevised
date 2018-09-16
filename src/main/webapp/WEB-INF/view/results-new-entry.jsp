<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>New Entry</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="/resources/static/header-footer.css" />
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

		<c:if test="${anagramsForDisplaying.isEmpty()}">
			<h2>
				<strong> Your anagrams did not match your initial input: ${theUserInput}.<br>
				<br> Try again.
				</strong>
			</h2>
		</c:if>

		<c:if test="${!anagramsForDisplaying.isEmpty()}">
			<h1>
				<strong>Success!</strong>
			</h1>
		You added the following words to the anagramator database:
		<ul>
				<c:forEach var="anagram" items="${anagramsForDisplaying}">
					<li>${anagram}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<form:form action="showForm">
			<input type="submit" value="Go back" />
		</form:form>
		<br>

	</div>	
	<div id="wrapper">
		<div id="bottom">
			<h5>@Copyright 2018 dream-tree</h5>
		</div>
	</div>	
</body>
</html>