<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hhcl.mail.util.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
function emplist(){
    var name = "Gautam";
alert("namem");
}
</script>

<body>
<form action="#">
<%
/* Connection con = null;
PreparedStatement ps = null; */
Connection con = null;
PreparedStatement pstmt = null;
try
{
	ResultSet rs = null;
	con = Util.getConnection();
	String r = "SELECT * FROM hcladm_prod.tbl_businessunit";
	pstmt = con.prepareStatement(r);
	rs = pstmt.executeQuery();
%>
<p>Select Name :
<select onchange="emplist()">
<%
while(rs.next())
{
String bid = rs.getString(1); 
String bname= rs.getString(2); 
%>
<option  value="<%=bid %>"><%=bname %></option>
<%
}
%>
</select>
</p>
<%
  
 }
catch(SQLException sqe)
{ 

}
%>
</form>
</body>
</html>