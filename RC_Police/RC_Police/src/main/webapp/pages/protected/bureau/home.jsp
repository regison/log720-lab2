<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Bureau' direct='true'/>
	<template:put name='header' content='/pages/protected/bureau/header.jsp' />
	
	<template:put name='content'>
		<div class="page-header">
		  <h1>Hello World ! from home.jsp</h1>
		</div>
		<div class="col-xs-12">
			<div class="cols-xs-6">
				test
			</div>
			<div class="cols-xs-6">
				test
			</div>	
		</div>
	
	</template:put>
	
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>



	
