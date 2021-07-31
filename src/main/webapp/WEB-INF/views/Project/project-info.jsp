<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<%@ page import="cybersoft.java12.crmapp.servlet.ProjectServlet" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
  </head>
  <body>
  
  
    <div class="container">
      <div class="card card-form">
        <div class="row no-gutters">
          <table class="table mb-0 thead-border-top-0">
            <thead>
              <tr class="text-primary">
                <th>in progress</th>
                <th>completed</th>
                <th>number of staff</th>
                <th>days until deadline</th>
                <c:if test="${ roleID==1 || roleID==2}">
                	  <button class="btn btn-outline-secondary mr-2" data-toggle="modal" data-target="#exampleModal" data-backdrop="false">New task</button>
                <button class="btn btn-outline-secondary mx-2" data-toggle="modal" data-target="#newStaffModal" data-backdrop="false" >New staff</button>
                
                </c:if>
              
              </tr>

              
              
              
              
            </thead>
            <tbody class="list" id="staff02">
              <tr>
                <td class=" text-secondary">12</td>
                <td class=" text-success">20 of 32</td>

                <td>6</td>
                <td class=" text-warning">20</td>

                
                
              </tr>
              
            
            </tbody>
          </table>
         
        </div>
       
        
      </div>
      
      
    </div>
    <div class="container">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"> Tasks</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Staffs</a>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
        	<c:choose>
        		<c:when test="${tasks==null}">
        			there's no task available
        			
        		
        		</c:when>
        		<c:otherwise>
        			<table class="table">
        				<thead>
        				 <tr>
					      <th scope="col">#</th>
					      <th scope="col">name</th>
					      <th scope="col">description</th>
					      <th scope="col">deadline</th>
					      <th scope="col">handler</th>
					       <th scope="col">status</th>
					      
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="task" varStatus="theCount" items="${tasks}">
        						
							    <tr>
							      <th scope="row">${theCount.count}</th>
							      <td>${task.name}</td>
							      <td>${task.description}
							      <td>${task.end}</td>
							      <td>${task.userName}</td>
							      <td>${task.statusName}</td>
							      <td>
							      	<a class="text-muted "><i class="material-icons " data-toggle="modal" data-target="#new__task" data-backdrop="false" style="cursor: pointer">settings</i></a>
					                <a href="<c:url value="<%=UrlConst.TASK_DELETE%>" />?id=${task.id}" class="text-muted"><i class="material-icons">delete</i></a>
					                <a  href="<c:url value="<%=UrlConst.TASK_UPDATE_STATUS %>"/>?id=${task.id}" style="cursor:pointer"><i class="material-icons">update</i></a>
							      </td>
							    </tr>
							   
							  
					   
        			
        				</c:forEach>
					  
					  </tbody>
        				
        			
        			</table>
        			
        		
        		</c:otherwise>
        	
        	</c:choose>
        
        
       			
       
       
       
       
       
       
       
        </div>
      
        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
        
        	     <c:choose>
        		<c:when test="${prjUsers==null}">
        			there's no staff there available
        			
        		
        		</c:when>
        		<c:otherwise>
        			<table class="table">
        				<thead>
        				 <tr>
					      <th scope="col">#</th>
					      <th scope="col">name</th>
					      <th scope="col">description</th>
					      <th scope="col">deadline</th>
					      <th scope="col">handler</th>
					       <th scope="col">status</th>
					      
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="staff" varStatus="theCount" items="${prjUsers}">
        						
							    <tr>
							      <th scope="row">${theCount.count}</th>
							      <td>${staff.userName}</td>
							      
							      <td>${staff.email}</td>
							      <td>${staff.joinDate}</td>
							      <td>${staff.role}</td>
							      <td>
					                <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_REMOVE%>" />?id=${staff.projectID}&userID=${staff.userID}" class="text-muted"><i class="material-icons">delete</i></a>
							      </td>
							    </tr>
							   
							  
					   
        			
        				</c:forEach>
					  
					  </tbody>
        				
        			
        			</table>
        			
        		
        		</c:otherwise>
        	
        	</c:choose>
        
        </div>
      </div>
     
    </div>
    
                	<!-- Modal -->
            <c:set var="ID" value="${projectID}" />
<div class="modal pt-5 fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">add new task</h5>
       
      </div>
      <form method="POST">
      <div class="modal-body">
      	
      		<div class="form-group">
            	<label  class="col-form-label">Task name:</label>
            	<input type="text" class="form-control" name="taskName">
          	</div>
          	<div class="form-group">
            	<label  class="col-form-label">description:</label>
            	<input type="text" class="form-control" name="taskDescription">
          	</div>
          	<div class="form-group">
            	<label  class="col-form-label">Start date</label>
            	<input type="date" class="form-control" name="taskStart">
          	</div>
          	<div class="form-group">
            	<label  class="col-form-label">End date:</label>
            	<input type="date" class="form-control" name="taskEnd">
          	</div>
          	<div class="form-group">
            	<label  class="col-form-label">staff ID:</label>
            	<input type="number" class="form-control" name="staffID">
          	</div>
          	<div class="form-group">
            	
            	<input type="hidden" class="form-control" name="taskProjectID" value="<c:out value="${ID}" />">
          	</div>
      	
      	
     
      		
        	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Add</button>
      </div>
      </form>
    </div>
  </div>
</div>
                	<!-- Modal -->
            <c:set var="ID" value="${projectID}" />
<div class="modal pt-5 fade" id="newStaffModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">add new staff</h5>
       
      </div>
      <form method="POST" action="staff/add">
      <div class="modal-body">
      	
      		<div class="form-group">
            	<label  class="col-form-label">Staff ID:</label>
            	<input type="text" class="form-control" name="staffID">
          	</div>
          	<div class="form-group">
            	<label  class="col-form-label">role description:</label>
            	<input type="text" class="form-control" name="staffDescription">
          	</div>
      		<input type="hidden"name="projectID" value="<c:out value="${ID}"/>">
     
      		
        	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Add</button>
      </div>
      </form>
    </div>
  </div>
</div>
  
  </body>
</html>
