<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>Input Form</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="/resources/static/header-footer.css" />
	<style> 
		.error1 {color:red}
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
		<p>Enter your query.</p>
		<p>Only letters allowed.</p>
		<p>No digits or non-letter characters e.g. "?", ":" or "/" allowed.</p>
		<p>Searching is case-insensitive.</p>

		<br>
		<form:form action="processForm" modelAttribute="theQuery">		
		Enter letters to find an anagram:
		<form:input path="userSequeceOfLetters" />
			<form:errors path="userSequeceOfLetters" cssClass="error1" />
			<br>
			<br>
			<input type="submit" value="Submit" />
		</form:form>
	</div>
	
	<div id="wrapper">
		<div id="bottom">
			<h5>@Copyright 2018 dream-tree</h5>
		</div>
	</div>
</body>
</html>