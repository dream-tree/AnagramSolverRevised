<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<style> 
		.error1 {color:red}
	</style>

</head>
<body>
	<p>Enter below all anagrams of <strong>"${theQuery.alphabetizedWord}"</strong>.</p> 
	<p>If <strong>"${theQuery.alphabetizedWord}"</strong> is a real word itself, type it too.</p> 
	<p>Separate your words by a whitespace and click the "Add" button.</p> 
	<p>Attention: before adding your anagrams to the database, all words will get an appropriate validation.</p>

	<form:form action="validateNewEntry" modelAttribute="theUserEntry">		
		<form:input path="stringOfAnagrams"/>
		<form:errors path="stringOfAnagrams" cssClass = "error1"/>
		<br><br>
		<input type="submit" value="Submit" width="300"/>
	</form:form>


</body>
</html>