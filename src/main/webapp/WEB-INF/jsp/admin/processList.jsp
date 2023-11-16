<%-- 
    Document   : processList
    Created on : 1 Jan, 2018, 11:35:34 PM
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
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home">Home</a></li>
                    <li><a href="../home/home">Admin</a></li>
                    <li><a href="../home/home">Process</a></li>
                    <li><a href="#">Process List</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-8">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Process list</h3>
                                    <ul class="panel-controls">
                                        <li><a href="../admin/processAdd.htm"><span class="fa fa-plus"></span></a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered" >
                                                <thead>
                                                    <tr>
                                                        <th>Process name</th>
                                                        <th>Code</th>
                                                        <th>Location</th>
                                                        <th>BU manager</th>
                                                        <th style="text-align: center;">Active</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${processList}" var="processItem">
                                                        <c:choose>
                                                            <c:when test="${processItem.active}"><c:set var="rowStyle" value="clickableRow"/></c:when>
                                                            <c:otherwise><c:set var="rowStyle" value="rowColor clickableRow"/></c:otherwise>
                                                        </c:choose>
                                                        <tr id="process" data-process-id="${processItem.id}" class="${rowStyle}">
                                                            <td><c:out value="${processItem.description}"/></td>
                                                            <td><c:out value="${processItem.code}"/></td>
                                                            <td><c:out value="${processItem.location.description}"/></td>
                                                            <td><c:out value="${processItem.buManager.name}"/></td>
                                                            <td style="text-align: center;">${processItem.activeString}</td>
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
                <input type="hidden" id="id" name="id"/>
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
        $('#process td').click(function () {
            var processId = $(this).parent().data("process-id");
            $('#id').val(processId);
            $('#form2').prop('action', '../admin/processEditShow.htm');
            $('#form2').submit();
        });
    </script>
</body>
</html>
