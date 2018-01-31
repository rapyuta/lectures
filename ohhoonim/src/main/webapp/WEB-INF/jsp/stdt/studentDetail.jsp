<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.StudentVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
StudentVo stdt = (StudentVo)request.getAttribute("stdt");
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
		location.href='<%=contextPath %>/stdt/studentList.do';
	});
	$('#btnModify').click(function(){
		location.href='<%=contextPath %>/stdt/studentModifyView.do?memberId=<%=stdt.getMemberId() %>';
	});
	$('#btnRemove').click(function(){
		location.href='<%=contextPath %>/stdt/studentRemove.do?memberId=<%=stdt.getMemberId() %>';
	});
});
</script>
</head>
<body>
<div id="wrapper">
    <div id="menu">
    	<jsp:include page="/WEB-INF/jsp/inc/menu_header.jsp">
			<jsp:param name="curMenu" value="student"/>
		</jsp:include>
    </div>
    <div id="title">
        <h1>학생정보 (<%=stdt.getMemberId() %>)</h1>
    </div>
    <div id="contents">
        <div id="buttons">
        	<input type="button" value="목록" id="btnList">
        	<input type="button" value="수정" id="btnModify">
        	<input type="button" value="삭제" id="btnRemove">
        </div>
        <div id="list">
            <ul>
            	<li>아이디: <%= stdt.getMemberId() %></li>
            	<li>이름: <%= stdt.getMemberName() %></li>
            	<li>생년월일: <%= stdt.getMemberBirth() %></li>
            	<li>성별: <%= stdt.getMemberGender() %></li>
            	<li>이메일: <%= stdt.getMemberEmail() %></li>
            </ul>
        </div>
    </div>
    <div id="footer">
    
    </div>
</div>
	
	
</body>
</html>