<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.ohhoonim.common.util.Utils"%>
<%@ page import="java.util.Map"%>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	/* String deptno = Utils.toEmptyBlank(request.getParameter("deptno"));
	String dname = Utils.toEmptyBlank(request.getParameter("dname"));
	String loc = Utils.toEmptyBlank(request.getParameter("loc"));
	String msg = Utils.toEmptyBlank(request.getParameter("msg")); */
	
	Map<String, String> rtnParam = (Map<String, String>)request.getAttribute("rtnParam");
	String deptno = "";
	String dname = "";
	String loc = "";
	String msg = "";
	if (rtnParam != null) {
		deptno = Utils.toEmptyBlank(rtnParam.get("deptno"));
		dname = Utils.toEmptyBlank(rtnParam.get("dname"));
		loc = Utils.toEmptyBlank(rtnParam.get("loc"));
		msg = Utils.toEmptyBlank(rtnParam.get("msg"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>부서등록</title>

<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
	$(function(){
		if ('<%=msg %>'.length > 0 ) {
			alert('<%=msg %>');
		}
		$('#btnList').click(function(){
			location.href='<%=contextPath %>/hr/deptList.do';
		});
		
		
		$('#btnDupCheck').click(function(){
			var a = {
					"param1": "test1",
					"map": {"key1":"1", "key2":"2", "key3":"3"},
					"list": [
						{"col1":"11", "col2":"12", "col3":"13"},
						{"col1":"21", "col2":"22", "col3":"23"},
						{"col1":"31", "col2":"32", "col3":"33"},
					]
			};
			$.ajax({
				url : '<%=contextPath%>/hr/checkDupDeptno.do',
				data : { formData: JSON.stringify(a) },
				method : "post",
				dataType : "json",
				success : function(data, status, jqXHR) {
					alert(JSON.stringify(data));
					if( !data.isDup ) {
						alert('중복안됨');
						$('#deptno').attr('readonly', 'readonly');
						$('#deptno').css('background-color', 'gray');
					} else {
						alert('중복됩니다.');
						$('#deptno').val('');
					}
				},
				error : function(jqXHR, status, errorThrown) {
					alert( 'ERROR: ' + JSON.stringify(jqXHR) );
				}
			});
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
			<h1>부서등록</h1>
		</div>
		<div id="contents">
			<div id="buttons">
	        	<input type="button" value="목록" id="btnList">
	        	
	        </div>
			<form id="frm" name="frm" action="<%=contextPath %>/hr/deptAdd.do" method="post">
			부서번호: <input type="text" id="deptno" name="DEPTNO" value="<%=deptno %>" maxlength="2">
						<input type="button" value="중복확인" id="btnDupCheck"><br>
			부서명: <input type="text" name="DNAME"  value="<%=dname %>" maxlength="10"><br>
			위치: <input type="text" name="LOC" value="<%=loc %>" maxlength="10"><br>
	  		
	  		<input type="submit" value="부서추가">
	    </form>        
		</div>
		<div id="footer">
		
		</div>
	</div>
</body>
</html>