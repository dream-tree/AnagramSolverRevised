<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Add Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<style> 
		.error1 {color:red}
	</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">English Word Anagramator</a>
        </div>
    </div>
</nav>
<div class="container">
	<br><br><br><br>
	<p>Enter below all matching anagrams.</p> 
	<p>Separate your words by a whitespace and click the "Add" button.</p> 
	<p>Attention: before adding your anagrams to the database, all words will get an appropriate validation.</p>

	<form:form action="validateNewEntry" modelAttribute="theUserEntry">		
		<form:input path="stringOfAnagrams"/>
		<form:errors path="stringOfAnagrams" cssClass = "error1"/>
		<br><br>
		<input type="submit" value="Submit" width="300"/>
	</form:form>

</div>
<footer class="footer navbar-inverse navbar-fixed-bottom">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4"><p class="text-muted">@Copyright 2018 dream-tree</p></div>
        </div>
    </div>
</footer>
</body>
</html>