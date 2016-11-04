
<% 
	BaseBean base = (BaseBean)request.getSession(true).getAttribute("BaseBean");
	if(base == null){
		base = BaseBean.init();
		request.getSession().setAttribute("BaseBean", base);
		request.getSession().setAttribute("BaseBeanInit.currentPath", base.getCurrentPage());
	}
	
	request.getSession().setAttribute("BaseBean.currentPath", base.getCurrentPage());
%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ca.etsmtl.log720.RC_Police.Beans.BaseBean"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='<%= base.getTitle()%>' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	<template:put name='content' content='<%= base.getCurrentPage()%>'  />
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>
