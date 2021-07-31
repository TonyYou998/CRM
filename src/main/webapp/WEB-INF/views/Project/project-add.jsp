<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New project</title>
</head>
<body>


<div class="my-form">
    <div>
        <div class="row justify-content-center">
            <div class="col-12">
                    <div class="card">
                       
                        <div class="card-body">
                            <form name="my-form" method="POST" >
                                <div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Project Name</label>
                                    <div class="col-md-6">
                                        <input type="text" id="full_name" class="form-control"  name="project_name" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="email_address" class="col-md-4 col-form-label text-md-right">Description</label>
                                    <div class="col-md-6">
                                        <!-- <input type="text" id="email_address" class="form-control" name="email-address"> -->
                                        <textarea type="text" name="description" class="w-100" style="height: 150px;">

                                        </textarea>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="user_name" class="col-md-4 col-form-label text-md-right">Start date</label>
                                    <div class="col-md-6">
                                        <input type="date" id="user_name" class="form-control"  name="start_date" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="phone_number" class="col-md-4 col-form-label text-md-right">End date</label>
                                    <div class="col-md-6">
                                        <input type="date" id="phone_number" name="end_date" class="form-control" required>
                                    </div>
                                </div>
                                 

                          

                                
                             
                                <button type="submit" class="btn btn-outline-primary container  w-25">ADD</button>
                            </form>
                        </div>
                    </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>