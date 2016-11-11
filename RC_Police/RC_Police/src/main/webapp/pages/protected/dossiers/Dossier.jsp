<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>


<template:insert template='/shared/templates/baseTemplate.jsp'>

	<template:put name='title' content='Policier' direct='true'/>
	
	<template:put name='header' content='/shared/header.jsp' />
	 
	 
	<template:put name='content' >
	
		<%if(request.isUserInRole("policier") || request.isUserInRole("administration") ){%>
		
		<jsp:useBean id="dossier" class="ca.etsmtl.log720.RC_Police.Beans.DossierBean" scope="request" />
	
		<div class="page-header" >
		  <h1><%= dossier.getRn_Descriptor() %></h1>
		</div>
		<div class="col-xs-12">
						
		        <form method="post" action="dossier" class="form">
		         	<div class="panel panel-primary">
			         	<div class="panel-body">
			         		<div class="panel panel-default">
								<div class="panel-heading">
									 Informations
								</div>
					      		<div class="panel-body">
						        	<div class="col-xs-12">
							        	<div class="col-xs-6">
								            <div class="form-group">
								               		
								                <label for="idDossier">Identifiant: <span class="requis">*</span></label>
								                <input readonly class="form-control" type="text" id="idDossier" name="id" value="${dossier.id }" />
								                <span class="erreur">${form.erreurs['id']}</span>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-xs-6">
											<div class="form-group">
											
								                <label for="Name">Nom: <span class="requis">*</span></label>
								                <input class="form-control" type="text" id="Nom" name="Nom" value="${dossier.nom }" <% if(dossier.getId() != null){ %> readonly <%} %> />
								                <span class="erreur">${form.erreurs['Nom']}</span>
						
							                </div>
							       		</div>
						                <div class="col-xs-6">
											<div class="form-group ">
						
								                <label for="NoPermis">No. Permis </label>
								                <input class="form-control" type="text" id="NoPermis" name="NoPermis" value="${dossier.noPermis }"  <% if(dossier.getId() != null){ %> readonly <%} %>  />
								                <span class="erreur">${form.erreurs['NoPermis']}</span>
						
							                </div>
						                </div>
					                </div>
					                <div class="col-xs-12">
					               	 	<div class="col-xs-6">
					                		<div class="form-group">
				
								                <label for="Prenom">Prenom: <span class="requis">*</span></label>
								                <input class="form-control" type="text" id="Prenom" name="Prenom" value="${dossier.prenom }" <% if(dossier.getId() != null){ %> readonly <%} %> />
								                <span class="erreur">${form.erreurs['Prenom']}</span>
						
							                </div>
							            </div>
										<div class="col-xs-6">
											<div class="form-group">
									                <label for="NoPlaques">No. Plaque </label>
									                <input class="form-control" type="text" id="NoPlaques" name="NoPlaque" value="${dossier.noPlaque }"  <% if(dossier.getId() != null){ %> readonly <%} %>  />
									                <span class="erreur">${form.erreurs['NoPlaque']}</span>
							                </div>
										
						
										</div>	
									</div>
								</div>
							</div>	
							<div class="panel panel-default">
								<div class="panel-heading">
									Infractions
								</div>
								<div class="panel-body">
								 	<div class="table-responsive">
										<table class="table-striped table-bordered col-xs-12" id="dataGrid-dossiers">
											<colgroup>
											     <col class="col-xs-1" style="width:2%"/>
										      	<col class="col-xs-9"/>
										        <col class="col-xs-2"/>
											</colgroup>
											<thead>
												<tr>
													<th> 
														<% if(dossier.getId() != null && request.isUserInRole("policier")){ %>
															<a type="button" class="btn btn-default btn-xs" href='<c:url value="/pages/protected/dossier__infraction" />?dossierid=${dossier.id}' >
												   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
															</a> 
														<%} %>
													</th>
											    	<th>Description</th>
											    	<th>Severite</th>
												</tr>
											</thead>
											<tbody>
												<%int rowNumber=0;%>
												
												<c:forEach var="row" items="${dossier.getInfractions()}" >
													<%rowNumber++; %>
													<tr id="${row.id}">
														<td>
															<a type="button" class="btn btn-default btn-xs " href='<c:url value="/pages/protected/infractions/infraction" />?id=${row.id}' >
												   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
															</a> 
															
														</td>
												    	<td>${row.description}</td>
												    	<td>severite</td>
													</tr>
												</c:forEach>
												
												<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
													<tr >
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
					 		<% if(dossier.getId() == null){ %>
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
	
