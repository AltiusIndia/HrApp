<%-- 
    Document   : userList
    Created on : 1 Oct, 2016, 1:19:41 PM
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
                    <li><a href="../home/home">User</a></li>
                    <li><a href="#">User list</a></li>
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
                                    <h3 class="panel-title">User list</h3>
                                    <ul class="panel-controls">
                                        <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_USER')">
                                            <li><a href="../admin/userAdd.htm"><span class="fa fa-plus"></span></a></li>
                                                </sec:authorize>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <!-- START FILTER PANEL -->
                                    <div class="panel panel-warning">
                                        <div class="panel-body">
                                            <form name="form1" id="form1">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label>Role</label>
                                                            <select name="roleId" Class="form-control select">
                                                                <option value="">-All-</option>
                                                                <c:forEach items="${roleList}" var="item">
                                                                    <option value="${item.roleId}" <c:if test="${roleId==item.roleId}">selected</c:if>>${item.roleName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="statusId" id="statusId" class="form-control select">
                                                                <option value="-1" <c:if test="${statusId==-1}">selected</c:if>>All</option>
                                                                <option value="1" <c:if test="${statusId==1}">selected</c:if>>Active</option>
                                                                <option value="0" <c:if test="${statusId==2}">selected</c:if>>Inactive</option>
                                                                </select>
                                                            </div>        
                                                        </div>   
                                                        <div class="col-md-2 btn-filter">
                                                            <button type="submit"  class="btn-info btn-sm" name="btnSubmit">Go</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- END FILTER PANEL -->

                                        <div class="row">
                                            <div class="col-md-12 scrollable">
                                                <table class="table datatable table-bordered" >
                                                    <thead>
                                                        <tr>
                                                            <th>Email Id</th>
                                                            <th>Name</th>
                                                            <th>Mobile no</th>
                                                            <th>Department</th>
                                                            <th>Location</th>
                                                            <th>Role</th>
                                                            <th>Status</th>
                                                            <th width="50px">Outside access</th>
                                                            <th>Expires on</th>
                                                            <th width="40px">Failed attempts</th>
                                                            <th>Last login</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${userList}" var="userItem">
                                                        <c:choose>
                                                            <c:when test="${userItem.active}"><c:set var="rowStyle" value="clickableRow"/></c:when>
                                                            <c:otherwise><c:set var="rowStyle" value="rowColor clickableRow"/></c:otherwise>
                                                        </c:choose>
                                                        <tr id="user" data-user-id="${userItem.userId}" class="${rowStyle}">
                                                            <td><c:out value="${userItem.emailId}"/></td>
                                                            <td><c:out value="${userItem.name}"/></td>
                                                            <td><c:out value="${userItem.phoneNo}"/></td>
                                                            <td><c:out value="${userItem.department.description}"/></td>
                                                            <td><c:out value="${userItem.location.description}"/></td>
                                                            <td><c:out value="${userItem.role.roleName}"/></td>
                                                            <td><c:out value="${userItem.activeString}"/></td>
                                                            <td><c:out value="${userItem.outsideAccess}"/></td>
                                                            <td><fmt:formatDate value="${userItem.expiresOn}" pattern="dd-MM-yyyy"/></td>
                                                            <td><c:out value="${userItem.failedAttempts}"/>
                                                                <c:if test="${userItem.failedAttempts>=3 }">
                                                                    <a href="#" class="resetLink pull-right" title="Reset failed attempts"><img src="../images/reset.png"/></a>
                                                                    </c:if>
                                                            </td>
                                                            <td><fmt:formatDate value="${userItem.lastLoginDate}" pattern="dd-MM-yyyy"/></td>

                                                            <td>
                                                                <c:if test="${userItem.active==true}">
                                                                    <a href="#" class="resetLink" title="Reset failed attempts"><img src="../images/reset.png"/></a>
                                                                    </c:if>
                                                            </td>
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

                    <!-- edit form page-->
                    <form name="form2" id="form2" action="" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="userId" name="userId"/>
                    </form> 
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
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript" defer="defer">
            //functions for edit user and reset failed attempts links
            <sec:authorize access="hasAnyRole('ROLE_BF_EDIT_USER')">
            $('#user td').click(function () {
                $('#userId').val($(this).parent().data("user-id"));
                $('#form2').prop('action', '../admin/userEditShow.htm');
                $('#form2').submit();
            });
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_BF_RESET_PASSWORD')">
            $('a.resetLink').click(function (event) {
                event.stopPropagation();
                if (confirm("Are you sure you want to reset the failed attempts on this user?")) {
                    $('#userId').val($(this).parent().parent().data('user-id'));
                    $('#form2').prop('action', '../admin/userFailedAttemptsReset.htm');
                    $('#form2').submit();
                }
            });

            </sec:authorize>

            //disble automatic table sorting
            $('.table').dataTable({
                "order": []
            });
        </script>
    </body>
</html>
