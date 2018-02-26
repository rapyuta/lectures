<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String curMenu = request.getParameter("curMenu");
%>
<div class="w3-bar w3-dark-grey" style="height:25px;">
  <a href="<%=contextPath %>/hr/empList.do" class="w3-bar-item w3-button <%=curMenu.equals("emp") ? "w3-black" : ""%>">사원</a>
  <a href="<%=contextPath %>/hr/deptList.do" class="w3-bar-item w3-button <%=curMenu.equals("dept") ? "w3-black" : ""%>">부서</a>
  <a href="<%=contextPath %>/hr/deptEmpList.do" class="w3-bar-item w3-button <%=curMenu.equals("deptEmp") ? "w3-black" : ""%>">부서별사원</a>
  <a href="<%=contextPath %>/stdt/studentList.do" class="w3-bar-item w3-button <%=curMenu.equals("student") ? "w3-black" : ""%>">학생</a>
</div>
