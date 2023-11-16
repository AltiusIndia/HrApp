
<%-- 
   
    Author     : gopal
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
    <body onload="setFocus();">
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
                    <li><a href="#">Requisition</a></li>
                    <li><a href="#">View Requisition</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap" >
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="requisition" method="POST" name="form1" id="form1" action="../requisition/requisitionList.htm">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Location</label>
                                            <div class="col-md-4 col-xs-12">
                                                <form:select path="location.id" cssClass="form-control select" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${locationList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Department</label>
                                            <div class="col-md-4 col-xs-12">
                                                <form:select path="department.id" cssClass="form-control select" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${departmentList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Process</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="process.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${processList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">BU Manager</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="buManager.userId" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${buManagerList}" itemLabel="name" itemValue="userId"/>
                                                </form:select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Process category</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="processCategory.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${processCategoryList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Employment type</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="employmentType.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${employmentTypeList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Recruitment type</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="recruitmentType.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${recruitmentTypeList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Recruitment level</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="recruitmentLevel.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${recruitmentLevelList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Hiring count</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:input path="hiringCount" cssClass="form-control" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Languages</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="languageIds" cssClass="form-control select select-field-padding" multiple="true" items="${languageList}" itemLabel="description" itemValue="id" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Training start date </label>
                                            <div class="col-md-4  col-xs-12"> 
                                                <form:input path="trainingStartDate" class="form-control" autocomplete="off" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Salary band</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:input path="salaryBand"  cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Minimum Qualification</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="minQualification.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${minQualificationList}" itemLabel="description" itemValue="id" disabled="true"/>
                                                </form:select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Typing speed</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="typingSpeed.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${typingSpeedList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Communication proficiency</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="communicationProficiency.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${communicationProffeciencyList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Gender ratio</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="genderRatio.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${genderRatioList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>   
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Comments</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:textarea path="comments"  cssClass="form-control" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Operations availability window</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:textarea path="operationsWindow" cssClass="form-control" disabled="true"/>
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Week-off pattern</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="weekOffPattern.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${weekOffPatternList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>   
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Week-off count</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="weekOffCount.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${weekOffCountList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>   
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Shift duration</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:select path="shiftDuration.id" cssClass="form-control select select-field-padding" disabled="true">
                                                    <form:option value="" label="Nothing selected"/>
                                                    <form:options items="${shiftDurationList}" itemLabel="description" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>   
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">HR Comments</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:textarea path="hrComments"  cssClass="form-control" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">HR Buffer count</label>
                                            <div class="col-md-4  col-xs-12">
                                                <form:input path="hrBufferCount" cssClass="form-control" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">HR closing date </label>
                                            <div class="col-md-4  col-xs-12"> 
                                                <form:input path="hrClosingDate" class="form-control" autocomplete="off" disabled="true"/>
                                            </div>
                                        </div>
                                            <div class="form-group">
                                            <label class="req col-md-3 col-xs-12 control-label">Status</label>
                                            <div class="col-md-4  col-xs-12"> 
                                                <form:select path="status.id" class="form-control select select-field-padding" items="${requisitionStatusList}" itemLabel="description" itemValue="id" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-3 col-xs-12 control-label">
                                                <spring:hasBindErrors name="requisition">
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
                                            <button type="submit" id="_cancel" name="btnCancel"  class="btn btn-danger">Close</button>
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
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-datepicker.js"></script>
        <!-- END THIS PAGE PLUGINS-->

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script>
        function setFocus() {
            $('select[name="location.id"]').focus();
        }

        $(document).ready(function () {
            $('#trainingStartDate').datepicker({
                format: "yyyy-mm-dd",
                autoclose: true
            });
        });
        </script>
    </body>
</html>
