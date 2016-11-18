<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>


<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Policier' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	<template:put name='content' >
	
		<div class="page-header" >
		  <h1>
		  		<%
		  		if(request.isUserInRole("policier")){
		  		%>Bienvenue sur le site du commissariat de police<%
		  		} 
		  		else if(request.isUserInRole("administration")) {
		  		%>Bienvenue sur le site du commissariat de police<%
		  		}
		  		%>
		  </h1>
		  
		  <h2>
		  		<%
		  		if(request.isUserInRole("policier")){
		  			%>"Site externe"<%
		  		} 
		  		else if(request.isUserInRole("administration")) {
		  			%>"Site interne"<%
		  		}
		  		%></h2>
		</div>
		<div class="col-xs-12">
		
			<%if(request.isUserInRole("policier") || request.isUserInRole("administration") ){%>
			
				
			
				<div class="col-xs-12">
				 	<div class="panel panel-primary">
						<div class="panel-heading">
							 Dossiers
							 <%if(request.isUserInRole("administration")) {%>
							 	<a href="dossiers/dossier" class="btn btn-success btn-xs pull-right"> Nouveau </a>
						 	<%} %>
						</div>
			      		<div class="panel-body">
			      		
						<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, nom, prenom, nopermis, noplaque 
							from dossier
							order by noplaque
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered sortable col-xs-12" id="dataGrid-dossiers">
									<colgroup>
									    <col style="width:2%"/>
								      	<col />
								        <col />
							          	<col />
							          	<col />
									</colgroup>
									<thead>
										<tr>
											<th data-defaultsort='disabled'> 
												<%-- <%if(request.isUserInRole("administration")) {%>
													<a type="button" class="btn btn-default btn-xs" href='dossiers/dossier'>
										   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
													</a> 
												<%} %> --%>
											</th>
									    	<th data-defaultsort="asc" >Nom</th>
									    	<th>Prenom</th>
									    	<th>No Permis</th>
									    	<th>No Plaque</th>
										</tr>
									</thead>
									<tbody>
										<%int rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}">
												<td>
													
													<a type="button" class="btn btn-default btn-xs" href='dossiers/dossier?id=${row.id}'>
										   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
													</a> 
													
												</td>
										    	<td>${row.nom}</td>
										    	<td>${row.prenom}</td>
										    	<td>${row.nopermis}</td>
										    	<td>${row.noplaque} </td>
											</tr>
										</c:forEach>
										
										<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
											<tr data-disablesort="true">
												<td>&nbsp; </td>
												<td>&nbsp; </td>
												<td>&nbsp; </td>
												<td>&nbsp; </td>
												<td>&nbsp; </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
		      		</div>
	      		</div>
	      		
	      		<div class="<% 
							if(request.isUserInRole("policier")){ 
								%> col-xs-6 no-rightpadding<%
							}else{ 
								%> col-xs-12<%
							} 
							%>">
		      		<div class="panel panel-primary">
						<div class="panel-heading">
							Infractions
							<%if(request.isUserInRole("administration")) {%>
								<a href="infractions/infraction" class="btn btn-success btn-xs pull-right"> Nouveau </a>
							<%} %>
						</div>
			      		<div class="panel-body">
							<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, description , idniveau
							from infraction
							order by idniveau
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered sortable col-xs-12" id="dataGrid-infractions">
									<colgroup>
									    <col class="col-xs-1" style="width:5%"/>
								      	<col class="col-xs-9"/>
								        <col class="col-xs-2"/>
									</colgroup>
									<thead>
										<tr>
											<th data-defaultsort='disabled'> 
											<%-- <%if(request.isUserInRole("administration")) {%>
												<a type="button" class="btn btn-default btn-xs" href='infractions/infraction'>
									   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
												</a> 
												<%} %> --%>
											</th>
									    	<th>Description</th>
									    	<th data-defaultsort="desc" >Severite</th>
										</tr>
									</thead>
									<tbody>
										<% rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}">
												<td>
													<a type="button" class="btn btn-default btn-xs" href='infractions/infraction?id=${row.id}'>
										   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
													</a> 
													
												</td>
										    	<td>${row.description}</td>
										    	<td>${row.idniveau}</td>
											</tr>
										</c:forEach>
										
										<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
											<tr data-disablesort="true" >
												<td>&nbsp; </td>
												<td>&nbsp; </td>
												<td>&nbsp; </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
		      		</div>
				</div>
			<%}
			
			if(request.isUserInRole("policier")){
			%>
         		<div class="col-xs-12 col-md-6">
		          	<div class="panel panel-primary ">
						<div class="panel-heading">
							Reactions
							 <a href="reactions/reaction" class="btn btn-success btn-xs pull-right"> Nouveau </a>
						</div>
			      		<div class="panel-body">
			      			<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, description 
							from reaction
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered sortable col-xs-12" id="dataGrid-reactions">
									<colgroup>
									    <col class="col-xs-1" style="width:5%"/>
								      	<col />
									</colgroup>
									<thead>
										<tr>
											<th data-defaultsort='disabled'> 
<!-- 												<a type="button" class="btn btn-default btn-xs" href='reactions/reaction'> -->
<!-- 									   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!-- 												</a>  -->
											</th>
									    	<th data-defaultsort="asc" >Description</th>
										</tr>
									</thead>
									<tbody>
										<%int rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}" >
												<td>
													<a type="button" class="btn btn-default btn-xs" href='reactions/reaction?id=${row.id}'>
										   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
													</a> 
													
												</td>
										    	<td>${row.description}</td>
											</tr>
										</c:forEach>
										
										<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
											<tr data-disablesort="true" >
												<td>&nbsp; </td>
												<td>&nbsp; </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
			      		</div>
		      		</div>
	      		</div>
          			 
         	<%}%>
		</div>
	
	</template:put>
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>




<!--  
	
-->
	
