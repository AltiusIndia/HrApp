<%-- 
    Document   : requisitionList
    Created on :2022, 11:35:34 PM
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
    <body>
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
                    <li><a href="../home/home">Requisition</a></li>
                    <li><a href="#">Requisition List</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">

                                <div class="panel-heading">

                                    <ul class="panel-controls">
                                        <li><a href="../requisition/requisitionAdd.htm"><span class="fa fa-plus"></span></a></li>
                                        <li><a href="#" onclick="$('#excelForm').submit();" title="Export to excel"><span class="fa fa-file-excel-o"></span></a></li>
                                    </ul>

                                    <h3 class="panel-title">Requisition List</h3>
                                    <ul class="panel-controls">
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <form  name="form1" id="form1" method="post" action="../requisition/requisitionList.htm">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />   
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-Fields">         
                                                    <div class="col-md-1">
                                                        <div class="form-group">
                                                            <div class="right-inner-addon input-container">
                                                                Start date<br/><input name="startDate" id="startDate" value="${startDate}" class="form-control datepicker" autocomplete="off" placeholder="Please select start Date"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <div class="form-group">
                                                            <div class="right-inner-addon input-container">
                                                                Stop date<br/>
                                                                <input  name="stopDate"  id="stopDate" class="form-control datepicker" value="${stopDate}" placeholder="Please select stop Date"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            Location<br/>
                                                            <select name="locationId" id="locationId" class="form-control select select-field-padding">
                                                                <option <c:if test="${locationId==-1}">selected</c:if> value="-1">All locations</option>
                                                                <c:forEach items="${locationList}" var="i">
                                                                    <option <c:if test="${locationId==i.id}">selected</c:if> value="${i.id}">${i.description}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            Status<br/>
                                                            <select name="statusId" id="statusId" class="form-control select select-field-padding">
                                                                <option <c:if test="${statusId==-1}">selected</c:if> value="-1">All statuses</option>
                                                                <c:forEach items="${statusList}" var="s">
                                                                    <option <c:if test="${statusId==s.id}">selected</c:if> value="${s.id}">${s.description}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 btn-filter" style="margin-top: 0px;">
                                                        <button type="submit" class="btn-info btn-sm" name="_go">Go</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered" >
                                                <thead>
                                                    <tr>
                                                        <th style="text-align: center; width: 100px;">Unique Id</th>
                                                        <th style="text-align: center; width: 100px;">Created date</th>
                                                        <th style="text-align: center; width: 120px;">Location</th>
                                                        <th style="text-align: center; width: 150px;">Department</th>
                                                        <th style="text-align: center; width: 150px;">Process</th>
                                                        <th style="text-align: center; width: 150px;">BU manager</th>
                                                        <th style="text-align: center; width: 100px;">Hiring count</th>
                                                            <sec:authorize access="hasAnyRole('ROLE_BF_VIEW_HR')">
                                                            <th style="text-align: center; width: 100px;">HR Buffer Count</th>
                                                            <th style="text-align: center; width: 100px;">HR Closing Date</th>
                                                            </sec:authorize>
                                                        <th style="text-align: center; width: 100px;">Status</th>
                                                        <th style="text-align: center; width: 150px;">Created By</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requisitionList}" var="r">
                                                        <c:choose>
                                                            <c:when test="${r.status.id==1}">
                                                                <c:set var="rowStyle" value="clickableRow editRow"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="rowStyle" value="clickableRow viewRow rowColor"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <tr data-requisition-id="${r.requisitionId}" class="${rowStyle}">
                                                            <td style="text-align: center;">${r.requisitionUniqueId}</td>
                                                            <td style="text-align: center;"><fmt:formatDate value="${r.createdDate}" pattern="dd-MMM-YY"/></td>
                                                            <td style="text-align: center;">${r.location.description}</td>
                                                            <td style="text-align: center;">${r.department.description}</td>
                                                            <td style="text-align: center;">${r.process.description}</td>
                                                            <td style="text-align: center;">${r.buManager.name}</td>
                                                            <td style="text-align: center;">${r.hiringCount}</td>
                                                            <sec:authorize access="hasAnyRole('ROLE_BF_VIEW_HR')"> 
                                                                <td style="text-align: center;">${r.hrBufferCount}</td>   
                                                                <td style="text-align: center;">${r.hrClosingDate}</td>   
                                                            </sec:authorize>
                                                            <td style="text-align: center;">${r.status.description}</td>
                                                            <td style="text-align: center;">${r.createdBy.name}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- edit form page-->
            <form name="form2" id="form2" action="" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                <input type="hidden" id="requisitionId" name="requisitionId"/>
            </form>

            <form name="excelForm" id="excelForm" method="get" target="_blank" action="../requisition/requsitionListExcel.htm">
                <input type="hidden" name="startDate" value="${startDate}"/>
                <input type="hidden" name="stopDate" value="${stopDate}"/>
                <input type="hidden" name="activityId" value="${activityId}"/>

            </form>
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
    <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
    <!-- END THIS PAGE PLUGINS-->        

    <!-- START TEMPLATE -->
    <script type="text/javascript" src="../js/plugins.js"></script>        
    <script type="text/javascript" src="../js/actions.js"></script>
    <!-- END TEMPLATE -->

    <script type="text/javascript" defer="defer">
                                            $('.editRow td').click(function () {
                                                $('#requisitionId').val($(this).parent().data("requisition-id"));
                                                $('#form2').prop('action', '../requisition/requisitionEditShow.htm');
                                                $('#form2').submit();
                                            });

                                            $('.viewRow td').click(function () {
                                                $('#requisitionId').val($(this).parent().data("requisition-id"));
                                                $('#form2').prop('action', '../requisition/requisitionView.htm');
                                                $('#form2').submit();
                                            });

                                            $('.table').dataTable({
                                                "order": []
                                            });
    </script>
    <script>
        $('#startDate').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
        $('#stopDate').datepicker({
            startDate: 'd',
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    </script>
</body>
</html>
