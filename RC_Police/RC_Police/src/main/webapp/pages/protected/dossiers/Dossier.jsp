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
		<jsp:useBean id="form" class="ca.etsmtl.log720.RC_Police.servlets.dossiers.DossierFormValidation" scope="request" />
		
		<div class="page-header" >
		  <h1><%= dossier.getRn_Descriptor() %></h1>
		</div>
		<div class="col-xs-12">
						
		        <form method="post" action="dossier" class="form">
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
								               		
								                <label for="idDossier" class="control-label">Identifiant:</label>
								                <input readonly class="form-control" type="text" id="idDossier" name="id" value="${dossier.id }" />
								                <span class="glyphicon form-control-feedback"></span>
								                <span class="erreur help-block" >${form.erreurs['id']}</span>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-xs-6">
											<div class="form-group <%
												if(form.getErreurs() != null){
													%>has-feedback <%
													if(form.getErreurs().containsKey("Nom") )
													{
														%>has-error<%
													}else{
														%>has-success<%
													}
												}
											%>">
											
								                <label for="Name" class="control-label">Nom: <span class="requis">*</span></label>
								                <input class="form-control" type="text" id="Nom" name="Nom" value="${dossier.nom }" <% if(dossier.getId() != null){ %> readonly <%} %> />
								                <span class="glyphicon form-control-feedback"></span>
								                <span class="erreur help-block">${form.erreurs['Nom']}</span>
						
							                </div>
							       		</div>
						                <div class="col-xs-6">
											<div class="form-group  <%
												if(form.getErreurs() != null){
													%>has-feedback <%
													if(form.getErreurs().containsKey("NoPermis") )
													{
														%>has-error<%
													}else{
														%>has-success<%
													}
												}
											%>">
						
								                <label for="NoPermis" class="control-label">No. Permis </label>
								                <input class="form-control" type="text" id="NoPermis" name="NoPermis" value="${dossier.noPermis }"  <% if(dossier.getId() != null){ %> readonly <%} %>  />
								                <span class="glyphicon form-control-feedback"></span>
								                <span class="erreur help-block">${form.erreurs['NoPermis']}</span>
						
							                </div>
						                </div>
					                </div>
					                <div class="col-xs-12">
					               	 	<div class="col-xs-6">
					                		<div class="form-group <%
				                				if(form.getErreurs() != null){
													%>has-feedback <%
													if(form.getErreurs().containsKey("Prenom") )
													{
														%>has-error<%
													}else{
														%>has-success<%
													}
												}
											%>">
				
								                <label for="Prenom" class="control-label">Prenom: <span class="requis">*</span></label>
								                <input class="form-control" type="text" id="Prenom" name="Prenom" value="${dossier.prenom }" <% if(dossier.getId() != null){ %> readonly <%} %> />
								                <span class="glyphicon form-control-feedback"></span>
								                <span class="erreur help-block">${form.erreurs['Prenom']}</span>
								                
						
							                </div>
							            </div>
										<div class="col-xs-6">
											<div class="form-group <%
												if(form.getErreurs() != null){
													%>has-feedback <%
													if(form.getErreurs().containsKey("NoPlaque") )
													{
														%>has-error<%
													}else{
														%>has-success<%
													}
												}
											%>" >
									                <label for="NoPlaques" class="control-label">No. Plaque </label>
									                <input class="form-control" type="text" id="NoPlaques" name="NoPlaque" value="${dossier.noPlaque }"  <% if(dossier.getId() != null){ %> readonly <%} %>  />
									                <span class="erreur help-block">${form.erreurs['NoPlaque']}</span>
							                </div>
										
						
										</div>	
									</div>
								</div>
							</div>
							<% if (dossier.getId() != null) { %>
								<div class="col-xs-12 alert <% 
											if(dossier.getInfractions().isEmpty()){
												%>alert-info<%
											}else{
												%>alert-warning<%
											}
											%>">
									<%if(dossier.getInfractions().isEmpty()){
										%>Ce dossier n'a pas d'infraction a son actif<%
									}else{
										%>Severite du dossier:<%= dossier.getSeverite() %><%
									}
									%>
								</div>
							<% } %>
							<div class="col-xs-12 no-sidepadding">
								<div class="<% 
											if(request.isUserInRole("policier")){ 
												%> col-xs-6 no-leftpadding<%
											}else{ 
												%> col-xs-12 no-sidepadding<%
											} 
											%>">
									<div class="panel panel-default">
										<div class="panel-heading">
											Infractions
										</div>
										<div class="panel-body">
										 	<div class="table-responsive">
												<table class="table-striped table-bordered sortable col-xs-12 " 
													id="dataGrid-dossiers">
													
													<colgroup>
													     <col class="col-xs-1" style="width:5%"/>
												      	<col class="col-xs-9"/>
												        <col class="col-xs-2"/>
													</colgroup>
													<thead>
														<tr>
															<th data-defaultsort='disabled'> 
																<% if(dossier.getId() != null && request.isUserInRole("policier")){ %>
																	<a type="button" class="btn btn-default btn-xs" href='<c:url value="/pages/protected/dossier__infraction" />?dossierid=${dossier.id}' >
														   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
																	</a> 
																<%} %>
															</th>
													    	<th>Description</th>
													    	<th data-defaultsort="desc" >Severite</th>
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
														    	<td>${row.severite}</td>
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
								<% if(request.isUserInRole("policier")){ %>
								<div class="col-xs-6 no-rightpadding">
									<div class="panel panel-default">
										<div class="panel-heading">
											Reactions
										</div>
										<div class="panel-body">
										 	<div class="table-responsive">
												<table class="table-striped table-bordered sortable col-xs-12" id="dataGrid-reactions">
													<colgroup>
													     <col class="col-xs-1" style="width:5%"/>
												      	<col class="col-xs-11"/>
													</colgroup>
													<thead>
														<tr>
															<th data-defaultsort='disabled'> 
																<% if(dossier.getId() != null && request.isUserInRole("policier")){ %>
																	<a type="button" class="btn btn-default btn-xs" href='<c:url value="/pages/protected/dossier__reaction" />?dossierid=${dossier.id}' >
														   				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
																	</a> 
																<%} %>
															</th>
													    	<th data-defaultsort='asc' >Description</th>
														</tr>
													</thead>
													<tbody>
														<%rowNumber=0;%>
														
														<c:forEach var="row" items="${dossier.getReactions()}" >
															<%rowNumber++; %>
															<tr id="${row.id}">
																<td>
																	<a type="button" class="btn btn-default btn-xs " href='<c:url value="/pages/protected/reactions/reaction" />?id=${row.id}' >
														   				<span class="glyphicon glyphicon-open" aria-hidden="true"></span>
																	</a> 
																	
																</td>
														    	<td data-defaultsort="asc" >${row.description}</td>
															</tr>
														</c:forEach>
														
														<c:forEach begin="<%=rowNumber%>" end="10" varStatus="loop">
															<tr  data-disablesort="true" >
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
								<%} %>
							</div>
						</div>
					 	<div class="panel-footer">
					 		<% if(dossier.getId() == null){ %>
	               	 			<button type="submit" class="btn btn-primary">Sauvegarder</button>
	               	 		 <%}else if(request.isUserInRole("administration")) { %>
	               	 		 	<a href="dossier" class="btn btn-success">Nouveau</a>
							<%}%>
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
	
