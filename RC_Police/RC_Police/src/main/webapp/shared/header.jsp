<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">RC_Police</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false">
          <ul class="nav navbar-nav">
          
           	
       		<%
            String loginUser = request.getRemoteUser();
            if(loginUser == null) {
            %>
            
            	<li class="active"><a href='<c:url value="/index.jsp"/>'>Home</a></li>
            
          	<% } else { %>
          		 <li class="active"><a href='<c:url value="/pages/protected/home.jsp"/>'>Home</a></li>
          		
          		<%-- <%if(request.isUserInRole("administrateur")){%>
          			 <li class=""><a href='<c:url value="/pages/protected/home.jsp" />'>Bureau</a></li>
          			 
          			 
          		<% } 
          		if(request.isUserInRole("policier")){
          		%>
          			<li class=""><a href='<c:url value="/pages/protected/home.jsp" />'>Policier</a></li>
          			
          		<% } %> --%>
	        	
	        <% } %>
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
          <%if(loginUser == null) {%>
			<li>
				<a href='<c:url value="/pages/protected/home.jsp" />'><span class="glyphicon glyphicon-log-in"></span> Sign-in</a>
			</li>
		  <%}else{%>
		  	<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		        	<span class="glyphicon glyphicon-user"></span> <%= loginUser %><span class="caret"></span>
		        </a>
		        <ul class="dropdown-menu">
		          <li><a href="#">Profile</a></li>
		          <li><a href='<c:url value="/pages/login/logout.jsp"/>'>Sign-out</a></li> 
		        </ul>
	        </li>
		  <%}%>
	      </ul>
          
        </div><!--/.nav-collapse -->
