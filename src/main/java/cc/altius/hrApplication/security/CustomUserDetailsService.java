/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cc.altius.hrApplication.dao.UserDao;
import cc.altius.hrApplication.framework.GlobalConstants;
import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.utils.LogUtils;
import cc.altius.utils.IPUtils;


/**
 * @author Akil Mahimwala

*/
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
    private final Set<String> allowedIpRange;

    public CustomUserDetailsService() {
        this.allowedIpRange = new HashSet<>();
        this.allowedIpRange.addAll(Arrays.asList(GlobalConstants.ALLOWED_IP_RANGE));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ipAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        try {
            CustomUserDetails user = this.userDao.getCustomUserDetailsByEmailId(username);
            if (!user.isActive()) {
                logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_ACCESS, ipAddress, username, "Account disabled"));
            } else if (!user.isAccountNonLocked()) {
                logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_ACCESS, ipAddress, username, "Account locked"));
            } else if (!(user.isOutsideAccess() || checkIfIpIsFromAllowedRange(ipAddress))) {
                user.setActive(false);
                logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_ACCESS, ipAddress, username, "Outside access"));
            } else {
                if (user.isPasswordExpired()) {
                    // only insert the ROLE_BF_PASSWORD_EXPIRED
                    logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_ACCESS, "Credentials are Expired so only put in ROLE_BF_PASSWORD_EXPIRED into Authoirites"));
                    List<String> businessFunctions = new LinkedList<>();
                    businessFunctions.add("ROLE_BF_PASSWORD_EXPIRED");
                    user.setBusinessFunction(businessFunctions);
                } else {
                    user.setBusinessFunction(this.userDao.getBusinessFunctionsForUserId(user.getUserId()));

                }

            }
            return user;
        } catch (EmptyResultDataAccessException erda) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private boolean checkIfIpIsFromAllowedRange(String ipToCheck) {
        for (String curRange : this.allowedIpRange) {
            IPUtils curIpRange = new IPUtils(curRange);
            if (curIpRange.checkIP(ipToCheck)) {
                return true;
            }
        }
        return false;
    }
}
