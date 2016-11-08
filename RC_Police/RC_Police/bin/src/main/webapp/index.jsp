

<jsp:useBean id="base" class="ca.etsmtl.log720.RC_Police.Beans.BaseBean" scope="session" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='<%= base.getTitle()%>' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	
	<template:put name='content' content='/pages/welcome.jsp'  />
	
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>
