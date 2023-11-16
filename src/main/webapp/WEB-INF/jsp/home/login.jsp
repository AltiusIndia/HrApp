<%-- 
    Document   : login
    Created on : 30 Sep, 2016, 11:02:12 PM
    Author     : shrutika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head><!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->
        <!-- CSS INCLUDE -->        
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->                                    
    </head>
    <body onload="document.getElementById('username').focus();">
        <div class="login-container">

            <div class="login-box animated fadeInDown">
                <a href="http://www.altius.cc"><div class="login-logo"></div></a>

                <div class="login-body">

                    <div class="login-title"><strong>HR Application</strong></div>

                    <!-- START LOGIN FORM HERE -->
                    <form name="loinForm" id="loginForm" action="../home/login.htm" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="text" class="form-control" placeholder="EmaiId" id="username" name="username" value="${sessionScope[SPRING_SECURITY_LAST_USERNAME]}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="password" class="form-control" placeholder="Password" id="password" name="password"/>
                            </div>
                        </div>

                        <div class="remember-section-wthree">
                            <span class="frt_text"><a href="../home/forgotPassword">Forgot password?</a></span>
                            <div class="clear"> </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6"></div>
                            <div class="col-md-6">
                                <button class="btn btn-info btn-block" onclick="document.getElementById('#loginForm').submit();">Log In</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <c:if test="${param.login_error=='true'}">
                                    <span class="text-danger">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        <p>Built by - <span class="text-builtBy">Altius Customer Services Pvt Ltd</span></p>
                        <p><a href="http://www.altius.cc">About</a> | <a href="http://www.altius.cc/contactus.html">Contact Us</a></p>
                    </div>
                    <div class="pull-right">
                        <p class="text-builtBy">ver ${minorVersion}</p>
                    </div>
                </div>
                <!-- MESSAGE SECTION -->
                <%@include file="../common/message.jsp"%>
                <!-- END MESSAGE SECTION -->
            </div>
        </div>
    </body>
</html>