///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.altius.hrApplication;
//
//import cc.altius.hrApplication.framework.GlobalConstants;
//import cc.altius.hrApplication.service.UserService;
//import cc.altius.hrApplication.utils.LogUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author Akil Mahimwala
// */
//@Component
//public class BadCredentialsEvent implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
//
//    @Autowired
//    private UserService userService;
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
//        String curUser = (String) e.getAuthentication().getPrincipal();
//        logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_LOGIN, ((WebAuthenticationDetails) e.getAuthentication().getDetails()).getRemoteAddress(), curUser, "Incorrect username and password"));
////        this.userService.incrementFailedCountForEmailId(curUser);
//    }
//}
