<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
<%@ page import = "java.util.*, java.text.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><a href='sum.jsp'>합 계산하기</a></div>
<div><a href='member/join.jsp'>회원가입</a></div>
<div><a href='el/index.jsp'>EL</a></div>
<div><a href='jstl/index.jsp'>JSTL</a></div>
<hr>
<h3>홈</h3>
   <div>요청URL: ${pageContext.request.requestURL}</div>
   <div>호스트명: ${pageContext.request.serverName}</div>
   <div>어플리케이션경로: ${pageContext.servletContext.contextPath}</div>
   <div>요청URI: ${pageContext.request.requestURI}</div>
<hr>
<%
Date today = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
String now = sdf.format( today );
%>
<div>날짜정보: <%= today %> </div>
<div>현재: <%=now %></div>

<%@ include file="include/footer.jsp" %>
</body>
</html>