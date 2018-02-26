<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ohhoonim.vo.StudentVo" %>
<%@ page import="com.ohhoonim.common.util.Utils" %>
<%@ include file="/WEB-INF/jsp/inc/common.jsp" %>
<%
List<StudentVo> list = (List<StudentVo>)request.getAttribute("list");
String searchMemberName = (String)request.getAttribute("memberName");
String searchGender = (String)request.getAttribute("memberGender");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>학생목록</title>
<script src="<%=contextPath %>/js/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" href="<%=contextPath %>/css/w3.css">
<link rel="stylesheet" href="<%=contextPath %>/css/common.css">

<script>
$(function(){
	$('#btnAdd').click(function(){
		location.href='<%=contextPath %>/stdt/studentAddView.do';
	});
	$('#btnDownloadExcel').click(function(){
		location.href='<%=contextPath %>/stdt/studentXls.do?searchName=' + $('#searchName').val() + '&searchGender=' + $('#searchGender').val();
	});
	
	$('#searchGender').val('<%=searchGender%>');
	$('#pageSize').val('${paging.pageSize}');
	$('#pageSize').change(function(){
		document.frm.submit();
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
        <h1>학생목록</h1>
    </div>
    <div id="contents">
        <div id="search">
            <form name="frm" action="<%=contextPath %>/stdt/studentList.do" method="post">
                <label for="">이름</label><input type="text" name="searchName" id="searchName" value="<%=searchMemberName%>">&nbsp;&nbsp;
                <label for="searchGender">성별</label><select name="searchGender" id="searchGender">
                								<option value="">--</option>
                								<option value="M">남</option>
                								<option value="F">여</option>
                						</select>
                <input type="hidden" name="pageNo" value="${paging.pageNo}">
                <span style="float:right;">
	                <select name="pageSize" id="pageSize">
	                	<option value="10">10</option>
	                	<option value="50">50</option>
	                	<option value="100">100</option>
	                </select>개씩 보기
                </span>
                <input type="submit" value="학생조회">
            </form>
        </div>
        <div id="buttons">
        	<input type="button" value="추가" id="btnAdd">
        	<input type="button" value="엑셀" id="btnDownloadExcel">
        </div>
        <div id="list">
            <table class="w3-table w3-bordered w3-striped w3-border">
                <tr class="w3-brown">
                    <th>아이디</th>
                    <th>이름</th>
                    <th>성별</th>
                    <th>이메일</th>
                </tr>
                <!-- 데이터 출력 -->
                <%
					for(StudentVo row: list) {
				%>
                <tr>
                	<td><a href="<%=contextPath %>/stdt/studentDetail.do?memberId=<%=row.getMemberId() %>"><%=row.getMemberId() %></a></td>
                	<td><%=row.getMemberName() %></td>
                	<td><%= Utils.toEmptyBlank(row.getMemberGender()) %></td>
                	<td><%= Utils.toEmptyBlank(row.getMemberEmail()) %></td>
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
        </div>
    </div>
    <div id="footer">
    
    </div>
</div>
	
<script type="text/javascript">
	function goPage(pageNo) {
		document.frm.pageNo.value = pageNo;
		document.frm.submit();
	}
</script>	
</body>
</html>



