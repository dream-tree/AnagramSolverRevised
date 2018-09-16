<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>Results</title>
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
		<h3>
			<strong>Anagrams were successfully deleted from the database.</strong>
		</h3>

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