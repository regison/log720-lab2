<%@page import="ca.etsmtl.log720.RC_Police.Beans.InfractionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>


<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Policier' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	 
	<template:put name='content' >
	
		<%if(request.isUserInRole("policier") ){%>
		
		<jsp:useBean id="dossier" class="ca.etsmtl.log720.RC_Police.Beans.DossierBean" scope="request" />
		<jsp:useBean id="reactions" class="ca.etsmtl.log720.RC_Police.components.ReactionsList" scope="request" />
		<jsp:useBean id="general_erreur" class="java.lang.String" scope="request" />
		
		<div class="page-header" >
		  <h1>Dossier <%= dossier.getRn_Descriptor() %></h1>
		</div>
		<div class="col-xs-12">
						
		        <form method="post" action="dossier__reaction" class="form">
		        	<input type="hidden" name="dossierid" value="<%= dossier.getId() %>" />
		        	
		        	<% if(general_erreur != null && !general_erreur.equals("")) {%>
		        		<div class="alert alert-danger">
		        		 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						  	<strong>erreur</strong> <%= general_erreur %>
						</div>
					<% } %>
		         	<div class="panel panel-primary">
			         	<div class="panel-body">
			         		
							<div class="panel panel-default">
								<div class="panel-heading">
									Reactions
								</div>
								<div class="panel-body">
								 	<div class="table-responsive">
										<table class="table-striped table-bordered sortable col-xs-12" id="dataGrid-dossiers">
											<colgroup>
											     <col class="col-xs-1" style="width:2%"/>
										      	<col class="col-xs-9"/>
										        <col class="col-xs-2"/>
											</colgroup>
											<thead>
												<tr>
													<th data-defaultsort='disabled'>
														<a type="button" class="btn btn-default btn-xs" href='reactions/reaction'>
											   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
														</a> 
													</th>
											    	<th data-defaultsort='desc'>Description</th>
												</tr>
											</thead>
											<tbody>
												<%int rowNumber=0;%>
												
												<c:forEach var="row" items="${reactions.getReactions()}" >
													
													
													<tr id="${row.id}">
														<td>
															<input class="checkbox checkbox-selection" 
																	type="checkBox" 
																	id="${row.id}" 
																	name="${row.id}"  
																	<% if( dossier.getReactions().contains( reactions.getReactions().get(rowNumber) )){%> 
																		checked
																	<%} %>
																	/>
														</td>
												    	<td>${row.description}</td>
													</tr>
													
													<%rowNumber++; %>
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
					 	<div class="panel-footer">
					 		
	               	 			<button class="save-btn btn btn-primary" type="submit" class="btn btn-primary">Sauvegarder</button>
						</div>
		        	</div>
		        </form>
			
         	
		</div>
		<script type="text/javascript">
			
		</script>
		<%}%>
	</template:put>
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>




<!--  
	
-->
	
