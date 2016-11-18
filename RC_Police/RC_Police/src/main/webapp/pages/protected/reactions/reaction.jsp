<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>


<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Policier' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	 
	<template:put name='content' >
	
		<%if(request.isUserInRole("policier")){%>
		
		<jsp:useBean id="reaction" class="ca.etsmtl.log720.RC_Police.Beans.ReactionBean" scope="request" />
	
		<div class="page-header" >
		  <h1><%= reaction.getRn_Descriptor() %></h1>
		</div>
		<div class="col-xs-12">
						
		        <form method="post" action="reaction" class="form">
		         	<div class="panel panel-primary">
			         	<div class="panel-body">
			         		<div class="panel panel-primary">
								<div class="panel-heading">
									 Informations
								</div>
					      		<div class="panel-body">
						        	<div class="col-xs-12">
							        	<div class="col-xs-6">
								            <div class="form-group">
								               		
								                <label for="idDossier">Identifiant: <span class="requis">*</span></label>
								                <input readonly class="form-control" type="text" id="idReaction" name="idReaction" value="${reaction.id}" />
								                <span class="erreur">${erreurs['idReaction']}</span>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-xs-12">
											<div class="form-group">
											
								                <label for="Description">Description: <span class="requis">*</span></label>
								                <input class="form-control" type="text" id="Description" name="Description" value="${reaction.description}" <% if(reaction.getId() != null){ %> readonly <%} %> />
								                <span class="erreur">${erreurs['Description']}</span>
						
							                </div>
							       		</div>
					                </div>
								</div>
							</div>	
							<div class="panel panel-default">
								<div class="panel-heading">
									Dossiers
								</div>
								<div class="panel-body">
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
														<% if(reaction.getId() != null && request.isUserInRole("testeur")){ %>
															<a type="button" class="btn btn-default btn-xs" href='<c:url value="/pages/protected/dossiers/dossier" /> '>
												   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
															</a> 
														<%} %>
													</th>
											    	<th data-defaultsort='asc' >Nom</th>
											    	<th>Prenom</th>
											    	<th>No Permis</th>
											    	<th>No Plaque</th>
												</tr>
											</thead>
											<tbody>
												<%int rowNumber=0;%>
												
												<c:forEach var="row" items="${reaction.getDossiers()}" >
													<%rowNumber++; %>
													<tr id="${row.id}">
														<td>
															<a type="button" class="btn btn-default btn-xs " href='<c:url value="/pages/protected/dossiers/dossier" />?id=${row.id}'>
												   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
															</a> 
															
														</td>
											    		<td>${row.nom}</td>
												    	<td>${row.prenom}</td>
												    	<td>${row.noPermis}</td>
												    	<td>${row.noPlaque} </td>
													</tr>
												</c:forEach>
												
												<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
													<tr data-disablesort="true" >
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
					 	<div class="panel-footer">
					 		<% if(reaction.getId() == null){ %>
	               	 			<button type="submit" class="btn btn-primary">Sauvegarder</button>
	               	 		 <%} %>
						</div>
		        	</div>
		        </form>
			
         	
		</div>
		<%}%>
	</template:put>
	
	<template:put name='footer' content='/shared/footer.jsp' />

</template:insert>




<!--  
	
-->
	
