
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
                    <li><a href="#">Process</a></li>
                    <li><a href="#">Edit Process</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap" >
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-6">
                            <form:form cssClass="form-horizontal" modelAttribute="process" method="POST" name="form1" id="form1" action="../admin/processEdit.htm">
                                <form:hidden path="id"/>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Edit Process</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Description</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="description" cssClass="form-control"/>
                                                <span class="help-block">Please enter process</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Process code</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="code" cssClass="form-control"/>
                                                <span class="help-block">Please enter Process code</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Location</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="location.id" cssClass="form-control" onchange="updateBuManagerList()" readonly="true">
                                                    <form:options items="${locationList}" itemValue="id" itemLabel="description"/>
                                                </form:select>
                                                <span class="help-block">Please select location</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">BU Manager</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="buManager.userId" cssClass="form-control">
                                                    <form:option value="" label="Please select"/>
                                                    <c:forEach items="${buManagerList}" var="bu">
                                                        <c:if test="${process.location.id==bu.id}">
                                                            <form:option value="${bu.userId}" label="${bu.name}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </form:select>
                                                <span class="help-block">Please select BU Manager</span>
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
                                                <spring:hasBindErrors name="process">
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
                                            <button type="submit" id="_submit" name="btnSubmit"  class="btn btn-success">Submit</button>
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
                    'description': {
                        required: true
                    },
                    'location\\.id': {
                        required: true
                    },
                    'buManager\\.userId': {
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

            function updateBuManagerList() {
                var locationId = document.getElementById("location\\.id").value;
                var $buDropDown = $("#buManager\\.userId");
                $buDropDown.empty(); // remove old options
            <c:forEach items="${buManagerList}" var="bu">
                if (locationId == '${bu.id}') {
                    $buDropDown.append($("<option value='"${bu.userId}"'>" +${bu.name} + "</option>"));
                }
            </c:forEach>
            }
        </script>
    </body>
</html>
