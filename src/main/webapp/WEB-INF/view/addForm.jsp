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
	
	<p>Enter all anagrams of your sequence of letters</p> 
	<p>If your sequence of letters form already a word, type this word in the input field too".</p>
	<p>Separate words by space and click "Add" button.</p> 
	<p>Attention: before adding anagrams to the database, your words will get an appropriate validation.</p>

	<form:form action="validateNewEntry" modelAttribute="userNewEntry">		
		<form:input path="optionalAnagramsString"/>
		<form:errors path="optionalAnagramsString" cssClass = "error1"/>
		<br><br>
		<input type="submit" value="Submit" width="300"/>
	</form:form>


</body>
</html>