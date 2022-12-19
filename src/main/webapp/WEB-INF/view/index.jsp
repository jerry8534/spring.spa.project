<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="../../../static/images/favicon.png">
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<%
String inc="temp.html";
if(request.getParameter("inc") != null){
	inc = request.getParameter("inc");
}

String sessionId = (String)session.getAttribute("sessionId");
%>
<main>
	<header>
		<div class='main_title'>
			<img src='images/noname4.png'>
		</div>
		<nav>
            <a href='/'>HOME</a>
            <a href='#' class='btnGuestBook'>Board</a>
            <a href='#' class='btnBoard'>GuestBook</a>
		</nav>
	</header>
	
	<div id='section'>
		<div class='guestbook'>최신 방명록</div>
		<div class='board'>최신 게시판</div>
	</div>
	<div class='content'>
        <jsp:include page="<%=inc %>"/>
    </div>
</main>
</body>
</html>