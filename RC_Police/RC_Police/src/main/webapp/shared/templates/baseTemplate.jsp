<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
       <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' />
       <link rel="stylesheet" href='<c:url value="/resources/css/sticky-footer.css" />' />
       
        <title><template:get name='title'/></title>
        
    </head>
    <body>
    	
	    	<header>
				<nav class="navbar navbar-default navbar-fixed-top">
			      	<div class="container">
	    				<template:get name='header'/>
			      </div>
			    </nav>
			</header>
			
	    	<div class="container">
	    		<template:get name='content'/>
	    	</div>
	       
	        <footer class="footer">
      			<div class="container">
	        		<template:get name='footer'/>
	         	</div>
	        </footer>
	        
	        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	        <script src='<c:url value="/resources/bootstrap/js/bootstrap.min.js" />'></script>
    </body>
</html>
