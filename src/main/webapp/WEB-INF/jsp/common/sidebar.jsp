<%-- 
    Document   : sidebar
    Created on : 1 Oct, 2016, 11:06:11 AM
    Author     : yogesh
--%>

<!-- START PAGE SIDEBAR -->
<div class="page-sidebar toggled">
    <!-- START X-NAVIGATION -->
    <ul class="x-navigation x-navigation-minimized">
        <li class="xn-logo">
            <a href="../home/home"><div class="xn-logoImage"></div></a>
            <a href="#" class="x-navigation-control"></a>
        </li>
        <li class="xn-profile">
            <a href="#" class="profile-mini">
                <img src="../assets/images/users/no-image.jpg" alt='<c:out value="${curUser.username}"/>'/>
            </a>
            <div class="profile">
                <div class="profile-image">
                    <img src="../assets/images/users/no-image.jpg" alt='<c:out value="${curUser.username}"/>'/>
                </div>
                <div class="profile-data">
                    <div class="profile-data-name"><c:out value="${curUser.username}"/></div>
                </div>
            </div>
        </li>

        <li class="active">
            <a href="../home/home"><span class="fa fa-home"></span> <span class="xn-text">Home</span></a>
        </li>
        <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_USER', 'ROLE_BF_ONLY_ADMIN_MASTER', 'ROLE_BF_RELOAD_APP_LAYER')">
            <li class="xn-openable">
                <a href="#" title="Admin"><span class="fa fa-user"></span><span class="xn-text">Admin</span></a>
                <ul style="width:250px;">
                    <sec:authorize access="hasAnyRole('ROLE_BF_RELOAD_APP_LAYER')"><li><a href="../admin/resideloadApplicationLayer.htm"><span class="fa fa-refresh fa-spin"></span>Reload application</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_LIST_USER')"><li><a href="../admin/userList.htm"><span class="fa fa-list-alt"></span>User</a></li></sec:authorize>
                    <%--<sec:authorize access="hasAnyRole('ROLE_BF_LIST_QUESTION_SET')"><li><a href="../admin/questionList.htm"><span class="fa fa-list-alt"></span>Question</a></li></sec:authorize>--%>
                    <sec:authorize access="hasAnyRole('ROLE_BF_LIST_DEPARTMENT')"><li><a href="../admin/departmentList.htm"><span class="fa fa-list-alt"></span>Department</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_LIST_DESIGNATION')"><li><a href="../admin/designationList.htm"><span class="fa fa-list-alt"></span>Designation</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_LIST_PROCESS')"><li><a href="../admin/processList.htm"><span class="fa fa-list-alt"></span>Process</a></li></sec:authorize>
                    <%--<sec:authorize access="hasAnyRole('ROLE_BF_LIST_CATEGORY')"><li><a href="../admin/questionCategoryList.htm"><span class="fa fa-list-alt"></span>Question category</a></li></sec:authorize>--%>
                    <%--<sec:authorize access="hasAnyRole('ROLE_BF_LIST_CATEGORY')"><li><a href="../admin/departmentCategoryMappingList.htm"><span class="fa fa-list-alt"></span>Department-Category mapping</a></li></sec:authorize>--%>
                </ul>
            </li>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('ROLE_BF_ADD_CANDIDATE,ROLE_BF_LIST_CANDIDATE')">
            <li class="xn-openable">
                <a href="#" title="Candidate"><span class="fa fa-users"></span><span class="xn-text">Candidate</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_ADD_CANDIDATE')"><li><a href="../candidate/candidateRegister"><span class="fa fa-plus"></span>Add Candidate</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_LIST_CANDIDATE')"><li><a href="../candidate/candidateList"><span class="fa fa-list-alt"></span>List Candidate</a></li></sec:authorize>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY')">
            <li class="xn-openable">
                <a href="#" title="Office"><span class="fa fa-object-group"></span><span class="xn-text">Interview Evaluation</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY_ASSIGNTOOPS', 'ROLE_BF_OFFICE_USE_ONLY_ASSIGNTOHRBYOPS')"><li><a href="../office/reassignCandidateList"><span class="fa fa-th-list"></span>Reassign Candidate</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY')"><li><a href="../office/officeUseOnlyList"><span class="fa fa-th-list"></span>Pending Candidate</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY_ASSIGNTOOPS')"><li><a href="../office/assignToOpsByHr"><span class="fa fa-th-list"></span>Assign to Ops</a></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY_ASSIGNTOHRBYOPS')"><li><a href="../office/assignToHrByOops"><span class="fa fa-th-list"></span>Reassign By Operation</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_OFFICE_USE_ONLY')"><li><a href="../office/officeUseListComplated"><span class="fa fa-th-list"></span>Completed Candidate</a></li></sec:authorize>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_BF_ADD_REQUISITUION')">
            <li class="xn-openable">
                <a href="#" title="Requisition"><span class="fa fa-random"></span><span class="xn-text">Requisition</span></a>
                <ul>
                    <!--<sec:authorize access="hasAnyRole('ROLE_BF_ADD_REQUISITUION')">  <a href="../admin/addRequisition.htm"><span class="fa fa-plus"></span>Add Requisition</a></sec:authorize>-->
                    <sec:authorize access="hasAnyRole('ROLE_BF_ADD_REQUISITUION')"><li><a href="../requisition/requisitionList.htm"><span class="fa fa-th-list"></span>Requisition List</a></li></sec:authorize>
                </ul>
            </li>
        </sec:authorize>

        <!-- ops tracker-->
        <sec:authorize access="hasAnyRole('ROLE_BF_ADD_CANDIDATE')">
            <li class="xn-openable">
                <a href="#" title="Tracker"><span class="fa fa-calendar"></span><span class="xn-text">Add Lead </span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_ADD_CANDIDATE')"><li><a href="../candidate/addCandidateOpsTracker.htm"><span class="fa fa-plus"></span>Add Lead</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_ADD_CANDIDATE')"><li><a href="../candidate/candidateOpsTrackerList.htm"><span class="fa fa-list"></span>Lead List</a></li></sec:authorize>
                    <li><a href="../candidate/jobs"><span class="fa fa-list"></span>Jobs</a></li>
                </ul>
            </li>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG,ROLE_BF_CANDIDATE_COUNT_REPORT')">
            <li class="xn-openable">
                <a href="#" title="Reports"><span class="fa fa-file-text-o"></span><span class="xn-text">Reports</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG')"><li><a href="../report/reportAccessLog"><span class="fa fa-th-list"></span>Access Log</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG')"><li><a href="../report/candidateDetailsReport"><span class="fa fa-th-list"></span>Candidate Details Report</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG')"><li><a href="../report/tatReport"><span class="fa fa-th-list"></span>TAT Report</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CANDIDATE_COUNT_REPORT')"><li><a href="../report/candidateStatusByCount"><span class="fa fa-th-list"></span>Candidate Status By Count</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CANDIDATE_COUNT_REPORT')"><li><a href="../report/requisitionReport"><span class="fa fa-th-list"></span>Requisition Report</a></li></sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CANDIDATE_COUNT_REPORT')"><li><a href="../report/candidateOpsTrackerReport"><span class="fa fa-th-list"></span>Candidate Records</a></li></sec:authorize>
                </ul>
            </li>
        </sec:authorize>
    </ul>
    <!-- END X-NAVIGATION -->
</div>
<!-- END PAGE SIDEBAR -->