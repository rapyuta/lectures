<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%
	Map reqMap = (Map)request.getAttribute("req");
	Set keys = reqMap.keySet();
	Iterator iter = keys.iterator();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Error</title>
</head>

<body>
    error: ${errorMsg}<br/>
<%
	while(iter.hasNext()) {
		out.print(((String[])reqMap.get(iter.next()))[0] + "<br/>");			
	}
%>
</body>
</html>