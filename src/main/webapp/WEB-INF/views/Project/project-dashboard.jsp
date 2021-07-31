<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Project Dashboard</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="#">Project</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Project Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Project Dashboard</h1>
	            </div>
	            <c:if test="${roleID!=3}">
	            	 <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />"  class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    			Add New Project</a>
	            	</div> 
	            
	            </c:if>
	           		
	            
	            
	           
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
		    <div class="row no-gutters">
                <table class="table mb-0 thead-border-top-0">
                    <thead>
                        <tr>
							<th>Project name</th>
	                        <th>Description</th>
	                        <th>Start</th>
	                        <th>End</th>
	                        <th>Owner</th>
                        </tr>
                    </thead>
                    <tbody class="list" id="staff02">
                  			<c:choose>
                  				<c:when test="${projects==null}">
                  					<tr class="row">
                  						<td class="col-12">there no data</td>
                  					</tr>
                  					
                  					
                  					
                  				</c:when>
                  					<c:otherwise>
                  						<c:forEach var="project" items="${projects}">
       										<tr>
					                            <td>
					                                ${project.projectName }
					                            </td>
					                            <td>${project.projectDescription}</td>
					                            
					                            <td>${project.startDate}</td>
					                            <td>${project.endDate}</td>
					                            
					                            <td>
					                            	${project.ownerName }
					                            </td>
					                            <td>
					                            	<c:if test="${roleID !=3 }">
					                            		<a class="text-muted "><i class="material-icons " data-toggle="modal" data-backdrop="false" onClick="openModal(${project.projectName})" style="cursor: pointer">settings</i></a>
					                            	<a href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${project.projectID}" class="text-muted"><i class="material-icons">delete</i></a>
					                            	</c:if>
					                            	
					                            	<a href="<c:url value="<%=UrlConst.PROJECT_INFO %>" />?id=${project.projectID}"><i class="material-icons" style="cursor:pointer">info</i></a>
					                            </td>
					                        </tr>		
       									
										
       									
       									
       									</c:forEach>
                  						
                  						
                  				
                  					</c:otherwise>
                 			 </c:choose>
                    	
                                           	</tbody>
                </table>
		    </div>
		</div>
	</div>
		
		<div class="modal pt-5 "
										        id="demo"
										        tabindex="-1"
										        role="dialog"
										        aria-labelledby="myModalLabel"
										        aria-hidden="true"
										      >
										        <div class="modal-dialog" role="document">
										          <div class="modal-content">
										          	
										            <div class="modal-header text-center">
										              <h4 class="modal-title w-100 font-weight-bold">Edit Project</h4>
										            </div>
										            <div class="modal-body mx-3">
										            	<form method="post">
										            		<div class="md-form mb-5">
											               		
																
											                	<input type="hidden" name="ID"  value="${project.projectID}" class="form-control validate" />
										                
										            		</div>	
										            		<div class="md-form mb-5">
											               		
																<label data-error="wrong" data-success="right" for="form3">project name</label>
											                	<input type="text" name="name" id="projectName" value="" class="form-control validate" />
										                
										            		</div>	
										            		 <div class="md-form mb-5">
										                		
																<label data-error="wrong" data-success="right" for="form3">desciption</label>
										                		<input type="text" name="description" class="form-control validate" value="${project.projectDescription }" />
										                
										                
										              		</div>
										              		<div class="md-form mb-5">
										                		
																<label data-error="wrong" data-success="right" for="form3">start</label>
										                		<input type="date" name="start" class="form-control validate" value="${project.startDate}" />
										                
										                
										              		</div>
										             		 <div class="md-form mb-5">
										                		
																<label data-error="wrong" data-success="right" for="form3">end</label>
										                		<input type="date" name="end" class="form-control validate" value="${project.endDate }" />
										                
										                
										              		</div>
										              		<div class="md-form mb-4">
										                		
																<label data-error="wrong" data-success="right" for="form2">ownerID</label>
										                		<input type="text" name="ownerID" value="${project.ownerID }" class="form-control validate" />
										               
										              		</div>
										           		 	 <div class="modal-footer d-flex justify-content-center">
										              			<button type="submit" class="btn btn-primary">Change <i class="fas fa-paper-plane-o ml-1"></i>
										              			</button>
										            		</div>			
										              		
										            			
										            	</form>
										              
													 
													  
													 
										 </div>
										              
										           
          </div>
        </div>
     		</div>
	
	<!-- END BODY -->
	</body>