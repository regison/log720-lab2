<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>


<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Policier' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	<template:put name='content' >
		<div class="page-header" >
		  <h1>Hello World ! from home.jsp</h1>
		</div>
		<div class="col-xs-12">
		
			<%if(request.isUserInRole("policier") || request.isUserInRole("administration") ){%>
			
				
			
				<div class="col-xs-12 col-md-6">
				 	<div class="panel panel-primary">
						<div class="panel-heading">
							 Dossiers
						 	<a type="button" class="btn btn-default btn-sm pull-right">
							   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							</a>
						</div>
			      		<div class="panel-body">
			      		
						<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, nom, prenom, nopermis, noplaque 
							from dossier
							order by noplaque
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered col-xs-12" id="dataGrid-dossiers">
									<colgroup>
									    <col style="align:center"/>
								      	<col style="align:center"/>
								        <col style="align:center"/>
							          	<col style="align:center"/>
							          	<col style="align:center"/>
									</colgroup>
									<thead>
										<tr>
											<th> 
												<a type="button" class="btn btn-default btn-xs">
									   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
												</a> 
											</th>
									    	<th>Nom</th>
									    	<th>Prenom</th>
									    	<th>No Permis</th>
									    	<th>No Plaque</th>
										</tr>
									</thead>
									<tbody>
										<%int rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}" height="30px">
												<td>
													<a type="button" class="btn btn-default btn-xs">
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
											<tr >
												<td>&nbsp;; </td>
												<td>&nbsp;; </td>
												<td>&nbsp;; </td>
												<td>&nbsp;; </td>
												<td>&nbsp;; </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
		      		</div>
	      		</div>
	      		
	      		<div class="col-xs-12 col-md-6">
		      		<div class="panel panel-primary">
						<div class="panel-heading">
							Infractions
							 <a type="button" class="btn btn-default btn-sm pull-right">
							   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							 </a>
						</div>
			      		<div class="panel-body">
							<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, nom, prenom, nopermis, noplaque 
							from dossier
							order by noplaque
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered col-xs-12" id="dataGrid-dossiers">
									<colgroup>
									    <col style="align:center"/>
								      	<col style="align:center"/>
								        <col style="align:center"/>
							          	<col style="align:center"/>
							          	<col style="align:center"/>
									</colgroup>
									<thead>
										<tr>
											<th> 
												<a type="button" class="btn btn-default btn-xs">
									   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
												</a> 
											</th>
									    	<th>Nom</th>
									    	<th>Prenom</th>
									    	<th>No Permis</th>
									    	<th>No Plaque</th>
										</tr>
									</thead>
									<tbody>
										<%rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}" height="30px">
												<td>
													<a type="button" class="btn btn-default btn-xs">
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
											<tr >
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
			<%}
			
			if(request.isUserInRole("policier")){
			%>
         		<div class="col-xs-12 col-md-6">
		          	<div class="panel panel-primary ">
						<div class="panel-heading">
							Reactions
							 <a type="button" class="btn btn-default btn-sm pull-right">
							   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							 </a>
						</div>
			      		<div class="panel-body">
			      			<sql:query var="listeDossier" dataSource="jdbc/TestJeeDB">
					    	select id, nom, prenom, nopermis, noplaque 
							from dossier
							order by noplaque
						</sql:query>
							 <div class="table-responsive">
								<table class="table-striped table-bordered col-xs-12" id="dataGrid-dossiers">
									<colgroup>
									    <col style="align:center"/>
								      	<col style="align:center"/>
								        <col style="align:center"/>
							          	<col style="align:center"/>
							          	<col style="align:center"/>
									</colgroup>
									<thead>
										<tr>
											<th> 
												<a type="button" class="btn btn-default btn-xs">
									   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
												</a> 
											</th>
									    	<th>Nom</th>
									    	<th>Prenom</th>
									    	<th>No Permis</th>
									    	<th>No Plaque</th>
										</tr>
									</thead>
									<tbody>
										<%int rowNumber=0;%>
										
										<c:forEach var="row" items="${listeDossier.rows}" >
											<%rowNumber++; %>
											<tr id="${row.id}" height="30px">
												<td>
													<a type="button" class="btn btn-default btn-xs">
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
											<tr >
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
          			 
         	<%}%>
		</div>
	
	</template:put>
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>




<!--  
	
-->
	
