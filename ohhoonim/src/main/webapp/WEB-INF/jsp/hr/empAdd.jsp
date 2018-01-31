<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.EmpVo" %>
<%@ page import="com.ohhoonim.vo.DeptVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	List<EmpVo> empList = (List<EmpVo>)request.getAttribute("empList");
	List<DeptVo> deptList = (List<DeptVo>)request.getAttribute("deptList");
	
	Map<String, String> rtnParams = (Map<String, String>)request.getAttribute("rtnParams");
	String empno     = "";
	String ename     = "";
	String sal       = "";
	String manager   = "";
	String deptno    = "";
	String hiredate  = "";
	String age       = "";
	String comm      = "";
	String msg      = "";
	if (rtnParams != null) {
		empno   = Utils.toEmptyBlank(rtnParams.get("EMPNO"));
		ename   = Utils.toEmptyBlank(rtnParams.get("ENAME"));
		sal     = Utils.toEmptyBlank(rtnParams.get("SAL"));
		manager = Utils.toEmptyBlank(rtnParams.get("MANAGER"));
		deptno  = Utils.toEmptyBlank(rtnParams.get("DEPTNO"));
		hiredate= Utils.toEmptyBlank(rtnParams.get("HIREDATE"));
		age     = Utils.toEmptyBlank(rtnParams.get("AGE"));
		comm    = Utils.toEmptyBlank(rtnParams.get("COMM"));
		
		msg    = Utils.toEmptyBlank(rtnParams.get("msg"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원추가</title>
<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=contextPath %>/js/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">
<script>
$(function(){
	if ('<%=msg%>'.length > 0) {
		$('#selectManager').val('<%=manager%>');
		$('#selectDeptno').val('<%=deptno%>');
		alert('<%=msg%>');		
	}
	$('#btnList').click(function(){
		location.href='<%=contextPath %>/hr/empList.do';
	});
	$('#hiredate').datepicker({
		  dateFormat: "yy-mm-dd"
	});
	$('#btnDupCheck').click(function(){
		if( $('#empno').val() != '' ) {
			$.ajax({
				url : '<%=contextPath%>/hr/checkDupEmpno.do',
				data : { empno: $('#empno').val() },
				method : "post",
				dataType : "json",
				success : function(data, status, jqXHR) {
					if( !data.isDup ) {
						alert('중복안됨');
						$('#empno').attr('readonly', 'readonly');
						$('#empno').css('background-color', 'gray');
					} else {
						alert('중복됩니다.');
						$('#empno').val('');
					}
				},
				error : function(jqXHR, status, errorThrown) {
					alert( 'ERROR: ' + JSON.stringify(jqXHR) );
				}
			});
		} else {
			alert('사원번호를 입력해주세요');
		}
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
        <h1>사원추가</h1>
    </div>
    <div id="contents">
    	<div id="buttons">
        	<input type="button" value="목록" id="btnList">
        	
        </div>
	    <form name="frm" action="<%=contextPath %>/hr/empAdd.do" method="post">
			사원번호: <input type="text" name="EMPNO" id="empno" value="<%=empno%>"><input type="button" value="중복확인" id="btnDupCheck"><br>
			사원명: <input type="text" name="ENAME"  value="<%=ename%>"><br>
			급여: <input type="text" name="SAL" value="<%=sal%>"><br>
			직속상관: 	<select name="MANAGER" id="selectManager">
							<option value="">----------</option>
							<%
							for (EmpVo vo: empList) {
							%>	
							<option value="<%=vo.getEmpno()%>"><%=vo.getEname()%></option>
							<%
							}
							%>
			
						</select><br>
			부서: 	<select name="DEPTNO" id="selectDeptno">
							<option value="">----------</option>
						<%
							for (DeptVo vo: deptList) {
							%>	
							<option value="<%=vo.getDeptno()%>"><%=vo.getDname()%></option>
							<%
							}
							%>
					</select> <br>
	  		고용일: <input type="text" name="HIREDATE" id="hiredate" value="<%=hiredate%>"><br>
	  		나이: <input type="text" name="AGE" value="<%=age%>"><br>
	  		상여금: <input type="text" name="COMM" value="<%=comm%>"><br>
	  		
	  		<input type="submit" value="사원추가">
	    </form>        
    </div>
    <div id="footer">
    
    </div>
</div>
</body>
</html>



