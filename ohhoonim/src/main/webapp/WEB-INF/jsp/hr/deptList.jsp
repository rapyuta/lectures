<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.ohhoonim.vo.DeptVo" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	String dname = (String)request.getAttribute("dname"); 
	List<DeptVo> deptList = (List<DeptVo>)request.getAttribute("deptList");
	
	Object rtnResult = request.getAttribute("rtnResult");
	
	String unremovedList = "";
	String removeOk = "";
	if (rtnResult != null) {
		Map<String, Object> result = (Map<String, Object>)rtnResult;
		List<String> unremoved = (List<String>)result.get("unremoved");
		unremovedList = StringUtils.arrayToCommaDelimitedString(unremoved.toArray());
		removeOk = (String)result.get("isRemoved");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>부서목록</title>

<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
	$(function(){
		
		if ( <%=unremovedList.length() %> > 0) {
			alert(<%=unremovedList %> + ' 부서가 제거되지 않았습니다.\n 부서에 사원이 없어야 삭제가능합니다.');
		} else if ('<%=removeOk %>'.length > 0) {
			alert('제거되었습니다.');
		}
		$('#btnSearch').click(function(){
			document.deptForm.submit();
		});
		$('#btnAdd').click(function(){
			location.href='<%=contextPath %>/hr/deptAddView.do';
		});
		$('#btnDel').click(function(){
			var deptno = [];
			$('input[name="chk"]:checked').each(function(){
				deptno.push($(this).parent().parent().find('td:nth-child(2)').text());
			});
			$('#deptnoList').val(deptno.join(','));
			document.deptDelForm.submit();
		});
		$('#chkAll').click(function(){
			if($(this).prop("checked")) { 
				$("input[name=chk]").prop("checked",true); 
			} else { 
				$("input[name=chk]").prop("checked",false); 
			}
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="menu">
			<jsp:include page="/WEB-INF/jsp/inc/menu_header.jsp">
				<jsp:param name="curMenu" value="dept"/>
			</jsp:include>
		</div>
		<div id="title">
			<h1>부서목록</h1>
		</div>
		<div id="contents">
			<div id="search">
				<form name="deptForm" action="" method="post">
					<label for="dname">부서명</label>
					<input type="text" id="dname" name="dname" value="<%=dname %>" >
					<input type="button" value="검색" id="btnSearch">
				</form>
				
				<form name="deptDelForm" action="<%=contextPath %>/hr/deptDel.do" method="post">
					<input type="hidden" id="deptnoList" name="deptnoList" value="" >
				</form>
				
			</div>
			<div id="buttons">
	        	<input type="button" value="추가" id="btnAdd">
	        	<input type="button" value="삭제" id="btnDel">
	        	
	        </div>
			<div id="list">
				<table class="w3-table w3-bordered w3-striped w3-border">
					<tr class="w3-brown">
						<th><input type="checkbox" name="chkAll" id="chkAll"></th><th>부서번호</th><th>부서명</th><th>위치</th>
					</tr>
	<%
	for(DeptVo row: deptList) {
					%>
					<tr>
						<td><input type="checkbox" name="chk"></td>
						<td><%=row.getDeptno() %></td>
						<td><%=row.getDname() %></td>
						<td><%=row.getLoc() %></td>
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