<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.StudentVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	StudentVo stdtVo = (StudentVo)request.getAttribute("stdt");

	Map<String, Object> redirectParam = (Map<String, Object>)request.getAttribute("redirectParam");
	String failMsg = ""; 
	if(redirectParam != null) {
		Map<String, String> rtnReq = (Map<String, String>)redirectParam.get("inputValues");
		if (rtnReq != null) {
			stdtVo = Utils.mappingReqparamToVo(rtnReq, StudentVo.class);
			failMsg = (String)redirectParam.get("failMsg");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>학생추가</title>
<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=contextPath %>/js/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
$(function(){
	$('input[name=memberGender]:radio[value=<%=stdtVo.getMemberGender()%>]').prop('checked',true);
	
	if ('<%=failMsg%>'.length > 0) {
		alert('<%=failMsg%>');		
	}
	$('#btnList').click(function(){
		location.href='<%=contextPath %>/stdt/studentList.do';
	});
	$('#memberBirth').datepicker({
		  dateFormat: "yy-mm-dd"
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
        <h1>학생수정</h1>
    </div>
    <div id="contents">
    	<div id="buttons">
        	<input type="button" value="목록" id="btnList">
        	
        </div>
	    <form name="frm" action="<%=contextPath %>/stdt/studentModify.do" method="post">
			아이디: <input type="text" name="memberId" id="memberId" value="<%=stdtVo.getMemberId()%>" readonly><br>
			패스워드: <input type="password" name="memberPw"  value=""><br>
			이름: <input type="text" name="memberName" value="<%=stdtVo.getMemberName()%>"><br>
	  		생일: <input type="text" name="memberBirth" id="memberBirth" value="<%=stdtVo.getMemberBirth()%>"><br>
	  		성별: <input type="radio" name="memberGender" value="M" >남&nbsp;&nbsp;&nbsp;&nbsp;
	  			  <input type="radio" name="memberGender" value="F">여
	  		<br>
	  		이메일: <input type="text" name="memberEmail" value="<%=stdtVo.getMemberEmail()%>"><br>
	  		
	  		<input type="submit" value="학생수정">
	    </form>        
    </div>
    <div id="footer">
    
    </div>
</div>
</body>
</html>



