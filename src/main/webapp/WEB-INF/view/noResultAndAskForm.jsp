<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 	Nothing was found for "${theQuery.userSequeceOfLetters}".

	<p>If the sequence of letters you entered can form a "real" word and</p>
	<p>and you want to add it to the database with all of its anagrams altogether (optionally),</p>	
	<p>you can do it here - click the "Next" button below:</p>
	<form:form action="processWordForm">
		<input type="submit" value="Next">
	</form:form>
	<br>
	<p>Otherwise you can go back:</p>
	<form:form action="showForm">
		<input type="submit" value="Go back"/>
	</form:form>

</body>
</html>