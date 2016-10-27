<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" 
  import="ca.etsmtl.log720.tutoriel_lab2.TestJeeMvc.beans.MyBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<% MyBean myBean = (MyBean)request.getSession().getAttribute("myBean"); %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Test_Client_Web_From_Servlet</title>
</head>

<body>
  <%=myBean.getSalut_FromServlet()%> 
</body>
</html>

