///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.altius.hrApplication;
//
//import cc.altius.hrApplication.framework.GlobalConstants;
//import cc.altius.hrApplication.model.CustomUserDetails;
//import cc.altius.hrApplication.service.UserService;
//import cc.altius.hrApplication.utils.LogUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author Akil Mahimwala
// */
//@Component
//public class SuccessEvent implements ApplicationListener<AuthenticationSuccessEvent> {
//
//    @Autowired
//    private UserService userService;
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent e) {
//        CustomUserDetails curUser = (CustomUserDetails) e.getAuthentication().getPrincipal();
////        this.userService.loginSuccessUpdateForUserId(curUser.getUserId());
//        logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_LOGIN, ((WebAuthenticationDetails) e.getAuthentication().getDetails()).getRemoteAddress(), curUser.getUsername(), "Success"));
//    }
//
//}
