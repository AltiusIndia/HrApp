<%-- 
    Document   : topbar
    Created on : 1 Oct, 2016, 11:08:59 AM
    Author     : shrutika
--%>

<!-- START X-NAVIGATION VERTICAL -->
<ul class="x-navigation x-navigation-horizontal x-navigation-panel" >
    <!-- TOGGLE NAVIGATION -->
    <li class="xn-icon-button">
        <a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
    </li>
    <li class="xn-titleText">
        HR Application
    </li>

    <!-- SIGN OUT -->

    <li class="xn-icon-button pull-right">

        <a href="#" class="mb-control" data-box="#mb-signout" title="Logout"><span class="fa fa-sign-out"></span></a>                        
    </li> 
    <sec:authorize access="hasAnyRole('ROLE_BF_CHANGE_PASSWORD')"><li class="xn-icon-button pull-right">
            <a href="../home/changePassword" title="Change Password"><span class="fa fa-key"></span> <span class="xn-text"></span></a>
        </li>
    </sec:authorize>
    <li class="xn-text" style="padding-left:  75%; padding-top: -25%;margin-top:-1%">
        <a href="http://www.altius.cc" style="width:100px; height:28px;margin-top:-17px;padding-left:  0%; "><img src="../img/whitelogo.png" style="width:100px; height:25px; margin-top:-25px;"></a>
    </li>

    <!-- END SIGN OUT -->
    <!-- END TOGGLE NAVIGATION -->
</ul>
<!-- END X-NAVIGATION VERTICAL --> 