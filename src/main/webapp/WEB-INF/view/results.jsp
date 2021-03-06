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
			<strong>Output page.</strong>
		</h3>
		<br>
		Found words for your query <strong>"${theQuery.userSequeceOfLetters}"</strong>:

		<c:url var="del" value="/deleteEntries">
			<c:param name="sequence" value="${theQuery.userSequeceOfLetters}" />
		</c:url>

		<form:form action="${del}" modelAttribute="anArrayForDeleting">
			<ul>
				<c:forEach var="anagram" items="${theListOfAnagrams}">
					<li><strong>${anagram}</strong> &nbsp;&nbsp;&#x21E8; <form:checkbox
							path="wordsForDeleting" value="${anagram}" /></li>
				</c:forEach>
			</ul>	
			If you want to delete a word, check the appropriate box and hit Delete button.
			<br>
			<br>
			<input type="submit" value="Delete" />
		</form:form>
		<br>
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