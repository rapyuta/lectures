<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ohhoonim.vo.EmpVo"%>
<%@ page import="com.ohhoonim.vo.DeptVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/inc/commonBoard.jsp" %>

<%
	EmpVo empvo = (EmpVo)request.getAttribute("empvo");
	List<DeptVo> deptList = (List<DeptVo>)request.getAttribute("deptList");
%>
<html>
	<head>
		<title>수정</title>
	</head>
	<body>
		<h1>수정</h1>
		<hr>				
		
		<form action="<%=contextRoot%>/board/boardModify.do" name="frm" method="post">
			*empno : 	<input name="empno" type="text" value="<%=Utils.toEmptyBlank(empvo.getEmpno())%>" readonly><br>
			*ename : 	<input name="ename" type="text" value="<%=Utils.toEmptyBlank(empvo.getEname())%>"><br>
			sal : 			<input name="sal" type="text" value="<%=Utils.toEmptyBlank(empvo.getSal())%>"><br>
			manager : 	<input name="manager" type="text" value="<%=Utils.toEmptyBlank(empvo.getManager())%>"><br>			
			deptno : 	<select  name="deptno" >
								<%
									for(DeptVo dept:deptList) {
								%>
								<option value="<%=dept.getDeptno()%>" <%= empvo.getDeptno().equals(dept.getDeptno()) ? "selected" : "" %>><%=dept.getDname() %></option>
								<%
									}
								%>
							</select>
			<br>			
			hiredate : 	<input name="hiredate" type="text" value="<%=Utils.dateFommatter(empvo.getHiredate())%>"><br>
			*age : 		<input name="age" type="text" value="<%=Utils.toEmptyBlank(empvo.getAge())%>"><br>
			comm : 		<input name="comm" type="text" value="<%=Utils.toEmptyBlank(empvo.getComm())%>"><br>
			<input type="submit" value="직원수정">
		</form>
	</body>
</html>