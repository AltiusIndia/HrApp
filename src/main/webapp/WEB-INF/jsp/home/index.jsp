<%-- 
  Document   : home
  Created on : 30 Sep, 2016, 5:07:34 PM
  Modified   : 22 july 2022
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

        <style>
            .readonlyDiv{
                pointer-events: none;
            }
            .readOnlyColor{
                background-color: #ccc;
            }
        </style>
    </head>

    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <ul class="breadcrumb">
                    <li><a href="../home/home.jsp">Home</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- END PAGE TITLE -->

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <form name="form1" id="form1" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="row">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Interview Dashboard</h2>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div id="commonDatePickerDiv">
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label>Start Date</label>
                                                    <input name="startDate" id="startDate" class="form-control datepicker" value="${startDate}"/>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label>Stop Date</label>
                                                    <input  name="stopDate"  id="stopDate" class="form-control datepicker" value="${stopDate}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-2 btn-filter">
                                            <button type="submit" class="btn-info btn-sm" name="_go">Go</button>
                                        </div>
                                        <!-- END FILTER PANEL -->
                                    </div>
                                </div>
                            </div>
                            <%-- start home page HR count report--%>
                            <sec:authorize access="hasRole('ROLE_BF_SHOW_HOME_HR_REPORT')">
                                <div class="col-md-10">
                                    <div class="col-md-2">
                                        <!-- START WIDGET SLIDER -->
                                        <div style="min-height: 106px; "class="widget widget-default widget-carousel">
                                            <div class="owl-carousel" id="owl-example">
                                                <div onclick="location.href = '../office/officeUseOnlyList';" style="cursor: pointer;">                                    
                                                    <div class="widget-title">Assigned to HR</div>
                                                    <div class="widget-subtitle">&nbsp;</div>
                                                    <div class="widget-int">${candidateDashboard.candidatesAssignedToHr}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <!-- START WIDGET SLIDER -->
                                        <div style="min-height: 106px; "class="widget widget-default widget-carousel">
                                            <div class="owl-carousel" id="owl-example">
                                                <div>                                    
                                                    <div class="widget-title">Interviews taken by HR</div>
                                                    <div class="widget-subtitle"><fmt:formatNumber value="${candidateDashboard.interviewsTakenByHrPerc}" type="percent" minFractionDigits="2" maxFractionDigits="2"/></div>
                                                    <div class="widget-int">${candidateDashboard.interviewsTakenByHr}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <!-- START WIDGET SLIDER -->
                                        <div style="min-height: 106px; "class="widget widget-default widget-carousel">
                                            <div class="owl-carousel" id="owl-example">
                                                <div>                                    
                                                    <div class="widget-title">Assigned to Ops</div>
                                                    <div class="widget-subtitle"><fmt:formatNumber value="${candidateDashboard.candidatesAssignedToOpsPerc}" type="percent" minFractionDigits="2" maxFractionDigits="2"/></div>
                                                    <div class="widget-int">${candidateDashboard.candidatesAssignedToOps}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <!-- START WIDGET SLIDER -->
                                        <div style="min-height: 106px; "class="widget widget-default widget-carousel">
                                            <div class="owl-carousel" id="owl-example">
                                                <div>                                    
                                                    <div class="widget-title">Interviews taken by Ops</div>
                                                    <div class="widget-subtitle"><fmt:formatNumber value="${candidateDashboard.interviewsTakenByOpsPerc}" type="percent" minFractionDigits="2" maxFractionDigits="2"/></div>
                                                    <div class="widget-int">${candidateDashboard.interviewsTakenByOps}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <!-- START WIDGET SLIDER -->
                                        <div style="min-height: 106px; "class="widget widget-default widget-carousel">
                                            <div class="owl-carousel" id="owl-example">
                                                <div>                                    
                                                    <div class="widget-title">Selected by Ops</div>
                                                    <div class="widget-subtitle"><fmt:formatNumber value="${candidateDashboard.finalSelectedPerc}" type="percent" minFractionDigits="2" maxFractionDigits="2"/></div>
                                                    <div class="widget-int">${candidateDashboard.finalSelected}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </sec:authorize>

                            <div class="col-md-12">
                                <!-- start Requisition Dashboard -->
                                <sec:authorize access="hasRole('ROLE_BF_SHOW_HOME_REQUISITUION_DASHBOARD')">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <h2 class="panel-title">Requisition Dashboard</h2>
                                        </div>
                                        <!-- START FILTER PANEL -->
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group">
                                                            <label>Please select Status</label>
                                                            <select name="statusId" id="statusId" cssClass="form-control select select-field-padding" style="width: 90%;padding: 4px">
                                                                <option value="-1" <c:if test="${statusId==-1}">selected</c:if>>All</option>
                                                                <option value="1" <c:if test="${statusId==1}">selected</c:if>>Open</option>
                                                                <option value="2" <c:if test="${statusId==2}">selected</c:if>>Close</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3" id="readonlyDivId2">
                                                            <div class="form-group">
                                                                <label>Please select Location</label>
                                                            <select name="locationId" id="locationId" class="form-control" data-live-search="true">
                                                                <option value="-1" <c:if test="${locationId==-1}">selected</c:if>>All Location</option>
                                                                <c:forEach items="${locationList}" var="item">
                                                                    <option <c:if test="${item.id==locationId}">selected</c:if> value="${item.id}">${item.description}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 btn-filter">
                                                        <button type="submit" class="btn-info btn-sm" name="_go">Go</button>
                                                    </div>
                                                    <!-- END FILTER PANEL -->
                                                </div>
                                            </div>

                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered" >
                                                <thead>
                                                    <tr>
                                                        <th style="width: 90px;">Requested dt</th>
                                                        <th style="width: 60px;">Unique Requisition Id</th>
                                                        <th style="width: 100px;">Location</th>
                                                        <th style="width: 250px;">Process</th>
                                                        <th style="width: 200px;">Department</th>
                                                        <th style="width: 80px;">Required count</th>
                                                        <th style="width: 90px;">Closing dt</th>
                                                        <th style="width: 80px;">Achieved</th>
                                                        <th style="width: 80px;">Pending</th>
                                                        <th>Hr Comments</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requsitionDashboardReportList}" var="d">
                                                        <tr class="clickableRow ${rowStyle}" data-requisition-id="${d.requisitionId}">
                                                            <td>${d.createdDate}</td>
                                                            <td>${d.requisitionUniqueId}</td>
                                                            <td>${d.location.description}</td>
                                                            <td>${d.process.description}</td>
                                                            <td>${d.department.description}</td>
                                                            <td>${d.overallCount}</td>
                                                            <td>${d.hrClosingDate}</td>
                                                            <td>${d.achieved}</td>
                                                            <td>${d.pending}</td>
                                                            <td>${d.hrComments}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </sec:authorize>
                                <!-- End Requisition Dashboard-->
                                <!-- END PAGE CONTENT -->
                            </div>
                        </div>
                    </form>
                    <form name="form5" id="form5" action="" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="requisitionId" name="requisitionId"/>
                    </form>
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>

        <!-- START THIS PAGE PLUGINS-->  
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../js/plugins/owl/owl.carousel.min.js"></script> 
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>  
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>

        <!-- END TEMPLATE -->
        <script type="text/javascript">
                                                    $('.clickableRow td').click(function () {
                                                        $('#requisitionId').val($(this).parent().data("requisition-id"));
                                                        $('#form3').prop('action', '../admin/showRequistionDashboardDeatails');
                                                        $('#form3').attr('target', '_blank');
                                                        $('#form3').submit();
                                                    });
        </script>

    </body>
</html>
