<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
번호 : ${board.seq }
제목 : ${board.title }
작성자 : ${board.writer }
내용 : ${board.content }
첨부파일 단건 : <a href="fileDown?seq=${board.seq }">${board.filename }</a><br />
첨부파일 : 
<a href="">일괄다운</a>
		<c:forTokens items="${board.filename }" delims="," var="file">
		<a href="fileNameDown?filename=${file}">${file }</a>
		</c:forTokens>
파일 : 
</body>
</html>