<%-- 
    Document   : addUser
    Created on : 1 Oct, 2016, 12:11:26 PM
    Author     : shrutika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->

        <!-- CSS INCLUDE -->
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->
    </head>
    <body onLoad="document.getElementById('name').focus();">
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home">Home</a></li>
                    <li><a href="../home/home">Admin</a></li>
                    <li><a href="../home/home">User</a></li>
                    <li><a href="#">Add User</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="user" method="POST" name="form1" id="form1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add User</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Email Id</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="emailId" cssClass="form-control"/>
                                                <span class="help-block">Please Enter email id</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="name" cssClass="form-control"/>
                                                <span class="help-block">Please enter Name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Mobile no</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="phoneNo" maxlength="10" cssClass="form-control" onidpress="return isNumberKey(event)"/>
                                                <span class="help-block">Please Enter 10 Digit Mobile No.</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                             <label class="req col-md-2 col-xs-12 control-label">Department</label>
                                              <div class="col-md-3 col-xs-12" style="margin-top: -16px;">
                                                    <form:select path="department.id" class="select form-control">
                                                       <form:option value="" label="Nothing selected"/>
                                                       <form:options items="${departmentList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                                <span class="help-block">Please select Department</span>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Location</label>
                                            <div class="col-md-3 col-xs-12">
                                                <form:select path="location.id"  cssClass="select form-control">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${locationList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                                <span class="help-block">Please select location</span>
                                            </div>
                                        </div>        
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Role</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="role.roleId" id="roleId" cssClass="form-control select">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${roleList}" itemLabel="roleName" itemValue="roleId"/>
                                                </form:select>
                                                <span class="help-block">Please select role</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Active</label>
                                            <div class="col-md-6 col-xs-12">
                                                Yes <form:radiobutton path="active" value="true"/>&nbsp;&nbsp;&nbsp;&nbsp;No <form:radiobutton path="active" value="false"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Outside access</label>
                                            <div class="col-md-6 col-xs-12">
                                                Yes <form:radiobutton path="outsideAccess" value="true"/>&nbsp;&nbsp;&nbsp;&nbsp;No <form:radiobutton path="outsideAccess" value="false"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Add in requisition</label>
                                            <div class="col-md-6 col-xs-12">
                                                Yes <form:radiobutton path="addInRequisition" value="true"/>&nbsp;&nbsp;&nbsp;&nbsp;No <form:radiobutton path="addInRequisition" value="false"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-2 col-xs-12 control-label">
                                                <spring:hasBindErrors name="user">
                                                    <span class="text-danger">
                                                        <form:errors path="*"/>
                                                    </span>
                                                </spring:hasBindErrors>
                                            </div>
                                        </div>
                                    </div> 

                                    <div class="panel-footer">
                                        <div class="pull-right">
                                            <button type="submit" id="_submit" name="btnSubmit"  class="btn btn-success" onclick="generatePassword();">Submit</button>
                                            <button type="submit" id="_cancel" name="_cancel" class="btn btn-primary" formnovalidate="formnovalidate">Cancel</button>
                                        </div>  
                                    </div>
                                </div>
                            </form:form>
                        </div>  
                    </div>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>            

        <!-- END THIS PAGE PLUGINS-->

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript">
                                                var jvalidate = $("#form1").validate({
                                                    ignore: [],
                                                    rules: {
                                                        'role.roleId': {
                                                            required: true
                                                        },
                                                        'department.id': {
                                                            required: true
                                                        },
                                                        'emailId': {
                                                            required: true,
                                                            email: true
                                                        },
                                                        'name': {
                                                            required: true
                                                        },
                                                        'phoneNo': {
                                                            required: true,
                                                            minlength: 10,
                                                            number: true
                                                        },
                                                        'location.id': {
                                                            required: true,
                                                        }
                                                    },
                                                    errorPlacement: function (error, element) {
                                                        if (element.hasClass('select')) {
                                                            error.insertAfter(element.next(".bootstrap-select"));
                                                            element.next("div").addClass("error");
                                                        } else {
                                                            error.insertAfter(element);
                                                        }
                                                    }
                                                });

        </script>
    </body>
</html>