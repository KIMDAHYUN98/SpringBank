<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!--  session="false" : 실행할 때 마다 섹션을 새로 생성한다.  -->
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="getAccoutList">사용자정보조회</a>
<a href="getOrgAuthorize">기관인증</a>
<a href="getBiz">크롤링</a>
<a href="pdf/empList">pdf empList</a>
<a href="pdf/empList2?dept=50">pdf empList2</a>
<a href="pdf/empList3">empList3 jrxml</a>


</body>
</html>
