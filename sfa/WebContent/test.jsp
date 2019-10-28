<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script> 
function get(){ 
var m="Hello"; 
window.location.replace("t.jsp?mess="+m); 
} 
</script> 
<input type="button" onclick="get();" value="click"> 
<%String message=request.getParameter("mess"); 
out.println(message); 
%> 
</body>
</html>