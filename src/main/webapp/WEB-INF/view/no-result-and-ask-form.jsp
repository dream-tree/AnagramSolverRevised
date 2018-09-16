<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>No Results</title>
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
		<p>
			Nothing was found for <strong>"${theQuery.userSequeceOfLetters}".</strong>
		</p>
		<p>If the sequence of letters you entered can form a "real" word
			and</p>
		<p>and you want to add it to the database with all of its anagrams
			altogether (optionally),</p>
		<p>you can do it here - click the "Next" button below:</p>

		<c:url var="showUserInput" value="/processWordForm">
			<c:param name="userInput" value="${theQuery.userSequeceOfLetters}"></c:param>
		</c:url>

		<form:form action="${showUserInput}">
			<input type="submit" value="Next">
		</form:form>
		<br>
		<p>Otherwise you can go back:</p>
		<form:form action="showForm">
			<input type="submit" value="Go back" />
		</form:form>

	</div>
	<div id="wrapper">
		<div id="bottom">
			<h5>@Copyright 2018 dream-tree</h5>
		</div>
	</div>
</body>
</html>