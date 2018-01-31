<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.EmpVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
	List<EmpVo> list = (List<EmpVo>)request.getAttribute("emplist");
	String searchName = (String)request.getAttribute("searchName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원목록</title>
<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">

<script>
$(function(){
	$('#btnAdd').click(function(){
		location.href='<%=contextPath %>/hr/empAddView.do';
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
        <h1>사원목록</h1>
    </div>
    <div id="contents">
        <div id="search">
            <form name="frm" action="<%=contextPath %>/hr/empList.do" method="post">
                <input type="text" name="searchName" value="<%=searchName%>">
                <input type="submit" value="사원조회">
            </form>
        </div>
        <div id="buttons">
        	<input type="button" value="추가" id="btnAdd">
        	
        </div>
        <div id="list">
            <table class="w3-table w3-bordered w3-striped w3-border">
                <tr class="w3-brown">
                    <th>사원번호</th>
                    <th>사원명</th>
                    <th>부서번호</th>
                </tr>
                <!-- 데이터 출력 -->
                <%
					for(EmpVo row: list) {
				%>
                <tr>
                	<td><a href="<%=contextPath %>/hr/empDetail.do?empno=<%=row.getEmpno() %>"><%=row.getEmpno() %></a></td>
                	<td><%=row.getEname() %></td>
                	<td><%= Utils.toEmptyBlank(row.getDeptno()) %></td>
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



