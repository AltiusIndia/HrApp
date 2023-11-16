
<%-- 
    Document   : addQuestion
    Created on : 28 Dec, 2017, 3:29:49 PM
    Author     : mahesh
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
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content" id="outContainer">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home">Home</a></li>
                    <li><a href="../home/home">Admin</a></li>
                    <li><a href="#">Designation</a></li>
                    <li><a href="#">Add Designation</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap" >
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-6">
                            <form:form cssClass="form-horizontal" modelAttribute="designation" method="POST" name="form1" id="form1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add Designation</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Description</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="description" cssClass="form-control"/>
                                                <span class="help-block">Please enter designation</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Active</label>
                                            <div class="col-md-6 col-xs-12">
                                                Yes <form:radiobutton path="active" value="true"/>&nbsp;&nbsp;&nbsp;&nbsp;No <form:radiobutton path="active" value="false"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-2 col-xs-12 control-label">
                                                <spring:hasBindErrors name="designation">
                                                    <span class="text-danger">
                                                        <form:errors path="*"/>
                                                    </span>
                                                </spring:hasBindErrors>
                                            </div>
                                        </div>
                                    </div> 

                                    <div class="panel-footer">
                                        <div class="pull-right">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                                            <button type="submit" id="_submit" name="btnSubmit" class="btn btn-success">Submit</button>
                                            <button type="submit" id="_cancel" name="_cancel" class="btn btn-danger" formnovalidate="formnovalidate">Cancel</button>
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
                    description: {
                        required: true
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
