<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.ohhoonim.vo.DeptVo" %>
<%@ page import="com.ohhoonim.vo.EmpVo" %>
<%@ page import="java.util.List" %>
<%
	String contextPath = request.getContextPath();

	String dname = (String)request.getAttribute("dname"); 
	String deptno = (String)request.getAttribute("deptno"); 
	List<DeptVo> deptList = (List<DeptVo>)request.getAttribute("deptList"); 
	List<EmpVo> empList = (List<EmpVo>)request.getAttribute("empList"); 
	String containNoDept = (String)request.getAttribute("containNoDept"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>부서별사원목록</title>

<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=contextPath %>/js/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
	$(function(){
		$('#btnSearch').click(clickSearch);
		$('.row').click(clickRow);
		$("input:text").keydown(enterKey);
		$('#dname').focus();
		
		$('.empRow').draggable();
		$('.row').droppable({
			drop: function( event, ui ) {
				var empno = ui.draggable.find('td:nth-child(1)').text();
				var ename = ui.draggable.find('td:nth-child(2)').text();
				var oldDeptno = ui.draggable.find('td:nth-child(5)').text();
				var newDeptno = $(this).find('td:nth-child(1)').text();
				$('#empno').val(empno);
				$('#newDeptno').val(newDeptno);
				if (oldDeptno != newDeptno && confirm(ename + '(' + empno + ') 사원의 (' + oldDeptno + ')부서를 ' + newDeptno + ' 부서로 변경하시겠습니까?')) {
		        	//document.changeDeptForm.submit();
				}
		      }
		});
	});
	
	function clickSearch() {
		$('#deptno').val('');
		$('#dname').val($('#dname').val().toUpperCase());
		document.deptForm.submit();
	}
	
	function enterKey(evt) {
		if (evt.keyCode == 13) {
			clickSearch(); 
		}
	}
	
	function clickRow() {
		var deptno = $(this).find('td:nth-child(1)').text();
		$('#deptno').val(deptno);
		document.deptForm.submit();
	}
	
</script>
</head>
<body>
	<div id="wrapper">
		<div id="menu">
			<jsp:include page="/WEB-INF/jsp/inc/menu_header.jsp">
				<jsp:param name="curMenu" value="deptEmp"/>
			</jsp:include>
		</div>
		<div id="title">
			<h1>부서별 사원 목록</h1>
		</div>
		<div id="contents">
			<div id="search">
				<form name="deptForm" action="" method="post">
					<label for="dname">부서명</label>
					<input type="text" id="dname" name="dname" value="<%=dname %>" >
					<label for="containNoDept">부서없음포함</label>
					<input type="checkbox" name="containNoDept" id="containNoDept" <%=containNoDept %>>
					<input type="hidden" id="deptno" name="deptno" value="<%=deptno %>">
					<input type="button" value="검색" id="btnSearch">
				</form>
				<form name="changeDeptForm" action="<%=contextPath %>/hr/changeDept.do" method="post">
					<input type="hidden" id="empno" name="empno" value="">
					<input type="hidden" id="newDeptno" name="newDeptno" value="">
				</form>
			</div>
			<div id="buttons">
	        	<!-- input type="button" value="추가" id="btnAdd"-->
	        	
	        </div>
			<div id="list">
				<!-- dept list -->
				<table class="w3-table w3-bordered w3-striped w3-border">
					<tr class="w3-brown">
						<th>부서번호</th><th>부서명</th><th>위치</th>
					</tr>
	<%
	for(DeptVo row: deptList) {
					%>
					<tr class="row">
						<td><%=row.getDeptno() %></td>
						<td><%=row.getDname() %></td>
						<td><%=row.getLoc() %></td>
					</tr>
					<%
	}
	%>
				</table>
				<div style="height:20px">
					<hr>
				</div>
				<!-- emp list -->
				<table class="w3-table w3-bordered w3-striped w3-border">
					<tr class="w3-brown">
						<th>사원번호</th>
						<th>사원명</th>
						<th>급여</th>
						<th>관리자</th>
						<th>부서번호</th>
						<th>고용일</th>
						<th>나이</th>
						<th>상여금</th>
					</tr>
					
	<%
	for(EmpVo row: empList) {
					%>
					<tr class="empRow">
						<td><%=row.getEmpno() %></td>
						<td><%=row.getEname() %></td>
						<td><%=row.getSal() %></td>
						<td><%=row.getManager() %></td>
						<td><%=row.getDeptno() %></td>
						<td><%=row.getHiredate() %></td>
						<td><%=row.getAge() %></td>
						<td><%=row.getComm() %></td>
					</tr>
					<%
	}
	%>
				</table>
				
			</div>
		</div>
		<div id="footer">
		
		</div>
	</div>
</body>
</html>