<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ohhoonim.vo.EmpVo"%>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/commonBoard.jsp" %>

<%
	EmpVo empvo = (EmpVo)request.getAttribute("empvo");
%>
<html>
	<head>
		<title>상세보기</title>
	</head>
	<body>
		<h1>상세보기</h1>
		<hr>
		<a href="<%=contextRoot%>/board/boardRemove.do?empno=<%=empvo.getEmpno() %>">삭제</a><br/>
		<!-- el 문법으로 사용시 아래와 같이 사용 -->
		<%-- <a href="<%=contextRoot%>/board/boardRemove.do?empno=${empvo.empno}">삭제</a><br/> --%>
		<a href="<%=contextRoot%>/board/boardModifyView.do?empno=${empvo.empno}">수정화면이동</a><br>
		<a href="<%=contextRoot%>/board/boardList.do">목록화면</a>
		<hr>
		empno : <%=Utils.toEmptyBlank(empvo.getEmpno()) %><br>
		Ename : <%=Utils.toEmptyBlank(empvo.getEname()) %><br>
		Sal : <%=Utils.toEmptyBlank(empvo.getSal()) %><br>
		Manager : <%=Utils.toEmptyBlank(empvo.getManager()) %><br>
		Deptno : <%=Utils.toEmptyBlank(empvo.getDeptno()) %><br>
		Hiredate : <%=Utils.dateFommatter(empvo.getHiredate()) %><br>
		Age : <%=Utils.toEmptyBlank(empvo.getAge()) %><br>
		Comm : <%=Utils.toEmptyBlank(empvo.getComm()) %><br>
		
		
	</body>
</html>