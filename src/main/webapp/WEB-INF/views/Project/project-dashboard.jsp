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
                  						<c:forEach var="project" varStatus="number" items="${projects}">
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
					                            		<a class="text-muted" href="<c:url value="<%=UrlConst.PROJECT_UPDATE %>" />?id=${project.projectID}" ><i class="material-icons" >settings</i></a>
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
		
			
	</body>