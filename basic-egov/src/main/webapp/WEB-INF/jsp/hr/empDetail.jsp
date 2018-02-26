<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.EmpVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	String empno = (String)request.getAttribute("empno");
	Map<String, Object> emp = (Map<String, Object>)request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보</title>
<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
$(function(){
	$('#btnList').click(function(){
		location.href='<%=contextPath %>/hr/empList.do';
	});
	$('#btnModify').click(function(){
		location.href='<%=contextPath %>/hr/empModifyView.do?empno=<%=empno %>';
	});
	$('#btnRemove').click(function(){
		location.href='<%=contextPath %>/hr/empRemove.do?empno=<%=empno %>';
	});
});
</script>
</head>
<body>
<div id="wrapper">
    <div id="menu">
    	<jsp:include page="/WEB-INF/jsp/inc/menu_header.jsp">
			<jsp:param name="curMenu" value="emp"/>
		</jsp:include>
    </div>
    <div id="title">
        <h1>사원정보 (<%=empno %>)</h1>
    </div>
    <div id="contents">
        <div id="buttons">
        	<input type="button" value="목록" id="btnList">
        	<input type="button" value="수정" id="btnModify">
        	<input type="button" value="삭제" id="btnRemove">
        </div>
        <div id="list">
            <ul>
            	<li>사원번호: <%= Utils.toEmptyBlank(emp.get("empno")) %></li>
            	<li>사원명: <%= Utils.toEmptyBlank(emp.get("ename")) %></li>
            	<li>급여: <%= Utils.toEmptyBlank(emp.get("sal")) %></li>
            	<li>직속상관: <%= Utils.toEmptyBlank(emp.get("manager")) %></li>
            	<li>부서번호: <%= Utils.toEmptyBlank(emp.get("deptno")) %></li>
            	<li>부서명: <%= Utils.toEmptyBlank(emp.get("dname")) %></li>
            	<li>입사일: <%= Utils.dateFommatter(emp.get("hiredate")) %></li>
            	<li>나이: <%= Utils.toEmptyBlank(emp.get("age")) %></li>
            	<li>상여금: <%= Utils.toEmptyBlank(emp.get("comm")) %></li>
            </ul>
        </div>
    </div>
    <div id="footer">
    
    </div>
</div>
	
	
</body>
</html>