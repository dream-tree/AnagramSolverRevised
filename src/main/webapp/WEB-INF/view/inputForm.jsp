<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Menu</title>

	<style> 
		.error1 {color:red}
	</style>

</head>

<body>

	<h1>
		<strong>Welcome to the English Word-Anagramator</strong>
	</h1>
	<p>Enter your query.</p>
	<p>Only letters allowed.</p>
	<p>No digits or non-letter characters e.g. "?", ":" or "/" allowed.</p>
	<p>Searching is case-insensitive.</p>
	
	<br>
	<form:form action="processForm" modelAttribute="theQuery">		
		Enter letters to find an anagram:
		<form:input path="userSequeceOfLetters"/>
		<form:errors path="userSequeceOfLetters" cssClass = "error1"/>
		<br>
		<input type="submit" value="Submit"/>
	</form:form>



</body>
</html>