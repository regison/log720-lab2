<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ca.etsmtl.log720.RC_Police.Beans.BaseBean"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='RC_Police' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	<template:put name='content' direct='true'>
	
		<form  class="col-xs-6 col-xs-offset-3"  method="POST" action="j_security_check" >

			<div class="form-group">
			
			  	<label for="username">Name:</label>
				<input id="username" class="form-control" type="text" name="j_username" />
			</div>
			<div class="form-group">
			  	<label for="pwd">Password:</label>
				<input id="pwd" class="form-control"  type="password" name="j_password" />
			</div>
			
			<button type="submit" class="btn btn-default pull-right">Log-in</button>
			
		</form>
	</template:put>
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>




