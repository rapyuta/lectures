<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ohhoonim.vo.EmpVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/commonBoard.jsp" %>

<%
	List<EmpVo> list = (List<EmpVo>)request.getAttribute("list");
	String searchType = (String)request.getAttribute("searchType");  //empno, ename
	String searchWord = (String)request.getAttribute("searchWord");
%>
<html>
	<head>
		<title>목록</title>
		<link rel="stylesheet" href="<%=contextRoot %>/css/w3.css">
	</head>
	<body>
		<h1>목록</h1>
		<hr>		
		<a href="<%=contextRoot%>/board/boardAddView.do">추가 화면 이동</a>
		<hr>
		<form name="searchFrm" action="<%=contextRoot%>/board/boardList.do" method="post">
			<select name="searchType">
				<option value="ename" <%= searchType.equals("ename") ? "selected" : "" %>>사원명</option>
				<option value="empno" <%= searchType.equals("empno") ? "selected" : "" %>>사원번호</option>
			</select>
			<input type="text" name="searchWord" value="">
			<input type="hidden" name="pageNo" value="">
			<input type="hidden" name="pageSize" value="10">
			<input type="submit" value="검색">
		</form>
		<table class="w3-table w3-bordered w3-striped w3-border">
			<tr class="w3-brown">
				<th>EMPNO</th>
				<th>ENAME</th>
				<th>SAL</th>
				<th>MANAGER</th>
				<th>DEPTNO</th>
				<th>HIREDATE</th>
				<th>AGE</th>
				<th>COMM</th>
			</tr>
			<%
			for(int i = 0; i < list.size(); i++) {
				EmpVo vo = list.get(i);
			%>
			<tr>
				<td><a href="<%=contextRoot%>/board/boardDetail.do?empno=<%=vo.getEmpno() %>"><%=vo.getEmpno() %></a></td>
				<td><%=Utils.toEmptyBlank(vo.getEname()) %></td>
				<td><%=Utils.toEmptyBlank(vo.getSal()) %></td>
				<td><%=Utils.toEmptyBlank(vo.getManager()) %></td>
				<td><%=Utils.toEmptyBlank(vo.getDeptno()) %></td>
				<td><%=Utils.dateFommatter(vo.getHiredate()) %></td>
				<td><%=Utils.toEmptyBlank(vo.getAge()) %></td>
				<td><%=Utils.customNum(vo.getComm(), "#,##0") %></td>
			</tr>
			<%
			}
			%>
		</table>		
		<jsp:include page="/WEB-INF/jsp/inc/paging.jsp">
				<jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
				<jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
				<jsp:param name="startPageNo" value="${paging.startPageNo}" />
				<jsp:param name="pageNo" value="${paging.pageNo}" />
				<jsp:param name="endPageNo" value="${paging.endPageNo}" />
				<jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
				<jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
			
			</jsp:include>
		
		<script type="text/javascript">
			function goPage(pageNo) {
				document.searchFrm.pageNo.value = pageNo;
				document.searchFrm.submit(); //위에있는 검색 submit을 실행시켜라
			}
		</script>	
	</body>
</html>
