<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="page-header">
		  <h1>Bienvenue sur le site du commissariat de police</h1>
	</div>
	<div class="alert alert-info col-xs-12">
		Vous devez etre connecté pour acceder au site web
		<a class="pull-right"  href='<c:url value="/pages/protected/home.jsp"/>'>
				<span class="glyphicon glyphicon-log-in"></span>
				<span style="text-decoration: underline;" >Connexion</span>  
		</a>
	</div>

	
