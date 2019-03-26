<%@page import="bn.vo.RssVO"%>
<%@page import="java.util.List"%>
<%@page import="bn.service.RssParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Breaking News</title>

	<!-- bootstrap CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<%
	List<RssVO> newsList = RssParser.getInstance().newsFlashXMLParsing();
	pageContext.setAttribute("newsList", newsList);
%>

<div class="container">
  <div class="row">
		<c:forEach var="news" items="${ newsList }">
    <div class="col-md-4">
      <h4>${ news.title }</h4>
      <p>${ news.description }</p>
      <p>${ news.pubDate }</p>
      <p><a class="btn btn-secondary" href="${ news.link }" target="_blank" role="button">뉴스 링크 &raquo;</a></p>
    </div>
	  </c:forEach>
  </div>
</div> 

</body>
</html>
