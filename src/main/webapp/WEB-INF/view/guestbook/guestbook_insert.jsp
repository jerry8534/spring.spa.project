<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<form>
		<label>작성자</label>
		<input type='text' name='id'>
		<label></label>
		<output>
			<fmt:formatDate value=<% %>
		</output>
	</form>
</body>
</html>