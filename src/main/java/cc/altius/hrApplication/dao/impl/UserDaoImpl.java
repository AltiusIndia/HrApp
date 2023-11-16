/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cc.altius.hrApplication.dao.UserDao;
import cc.altius.hrApplication.framework.GlobalConstants;
import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.Role;
import cc.altius.hrApplication.model.SimpleUser;
import cc.altius.hrApplication.model.User;
import cc.altius.hrApplication.model.rowMapper.CustomUserDetailsRowMapper;
import cc.altius.hrApplication.model.rowMapper.RoleRowMapper;
import cc.altius.hrApplication.model.rowMapper.SimpleUserRowMapper;
import cc.altius.hrApplication.model.rowMapper.UserRowMapper;
import cc.altius.hrApplication.utils.LogUtils;
import cc.altius.utils.DateUtils;

/**
 *
 * @author Akil Mahimwala
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    private static final String sqlUserString = "SELECT "
            + "u.USER_ID, u.NAME, u.PASSWORD, u.ACTIVE, u.EXPIRED, "
            + "u.FAILED_ATTEMPTS, u.EXPIRES_ON, r.ROLE_ID, r.ROLE_NAME, u.OUTSIDE_ACCESS, "
            + "u.LAST_LOGIN_DATE, u.EMAIL_ID, u.PHONE_NO, l.`ID` `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION`, "
            + "d.`ID` `D_ID`, d.`DESCRIPTION` `D_DESCRIPTION`, u.ADD_IN_REQUISITION "
            + "FROM us_user u "
            + "LEFT JOIN us_user_role ur ON u.USER_ID=ur.USER_ID "
            + "LEFT JOIN us_role r ON ur.ROLE_ID=r.ROLE_ID "
            + "LEFT JOIN mst_department d ON u.DEPARTMENT_ID=d.`ID` "
            + "LEFT JOIN mst_location l ON u.LOCATION_ID=l.`ID` ";

    @Override
    public CustomUserDetails getCustomUserDetailsByEmailId(String emailId) {
        String sql = "SELECT "
                + "u.USER_ID, u.NAME, u.PASSWORD, u.ACTIVE, u.EXPIRES_ON, u.EMAIL_ID, "
                + "u.EXPIRED, u.FAILED_ATTEMPTS, u.LAST_LOGIN_DATE, u.OUTSIDE_ACCESS, d.ID `D_ID`, "
                + "d.DESCRIPTION `D_DESCRIPTION`, r.ROLE_ID, r.ROLE_NAME, l.ID `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION` "
                + "FROM `us_user` u "
                + "LEFT JOIN `us_user_role` ur ON u.`USER_ID`=ur.`USER_ID` "
                + "LEFT JOIN  us_role r ON ur.`ROLE_ID`=r.`ROLE_ID` "
                + "LEFT JOIN mst_department d ON u.`DEPARTMENT_ID`=d.`ID` "
                + "LEFT JOIN mst_location l on u.LOCATION_ID=l.`ID` "
                + " WHERE u.`EMAIL_ID`=:emailId";
        Map<String, Object> params = new HashMap<>();
        params.put("emailId", emailId);
        try {
            logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, sql, params));
            CustomUserDetails user = this.namedParameterJdbcTemplate.queryForObject(sql, params, new CustomUserDetailsRowMapper());
            if (user == null) {
                throw new EmptyResultDataAccessException(1);
            }
            logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, "User found " + user.toString()));
            return user;
        } catch (EmptyResultDataAccessException erda) {
            logger.error(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, erda));
            return null;
        } catch (Exception e) {
            logger.error(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, e));
            return null;
        }
    }

    @Override
    public List<String> getBusinessFunctionsForUserId(int userId) {
        try {
            String sqlString = "SELECT BUSINESS_FUNCTION_ID FROM us_user_role ur LEFT JOIN us_role_business_function rbf ON ur.ROLE_ID=rbf.ROLE_ID WHERE ur.USER_ID=?";
            return this.jdbcTemplate.queryForList(sqlString, String.class, userId);
        } catch (DataAccessException e) {
            logger.error(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, e));
            return null;
        }
    }

    @Override
    public List<SimpleUser> getSimpleUserList(String roleList, boolean active) {
        String sqlString = "SELECT u.USER_ID, u.NAME FROM us_user u LEFT JOIN us_user_role ur ON u.USER_ID=ur.USER_ID WHERE FIND_IN_SET(ur.ROLE_ID, :roleList) AND (:active=false OR u.ACTIVE)";
        Map<String, Object> params = new HashMap<>();
        params.put("roleList", roleList);
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query(sqlString, params, new SimpleUserRowMapper(""));
    }

    @Override
    public List<User> getUserList(String roleId, String statusId) {
        String sqlString = UserDaoImpl.sqlUserString + " WHERE (ur.ROLE_ID=:roleId OR :roleId='') AND (:statusId=-1 OR u.ACTIVE=:statusId)";
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("statusId", statusId);
        return this.namedParameterJdbcTemplate.query(sqlString, params, new UserRowMapper());
    }

    @Override
    public List<Role> getRoleList() {
        String sqlString = "SELECT r.ROLE_ID, r.ROLE_NAME FROM us_role r";
        return this.jdbcTemplate.query(sqlString, new RoleRowMapper());
    }

    @Override
    public boolean existUserByEmailId(String emailId) {
        String sqlString = "SELECT COUNT(*) FROM us_user u WHERE u.EMAIL_ID=:emailId";
        Map<String, Object> params = new HashMap<>();
        params.put("emailId", emailId);
        Integer count = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, Integer.class);
        return (count != null && count >0);
    }

    @Override
    @Transactional
    public int addUser(User user) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("us_user").usingGeneratedKeyColumns("USER_ID");
        Map<String, Object> params = new HashMap<>();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPass = encoder.encode("pass");
        params.put("EMAIL_ID", user.getEmailId());
        params.put("NAME", user.getName());
        params.put("PASSWORD", hashPass);
        params.put("ACTIVE", user.isActive());
        params.put("EXPIRED", false);
        params.put("EXPIRES_ON", DateUtils.addDays(DateUtils.getCurrentDateObject(DateUtils.IST), 30));
        params.put("FAILED_ATTEMPTS", 0);
        params.put("OUTSIDE_ACCESS", user.isOutsideAccess());
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("PHONE_NO", user.getPhoneNo());
        params.put("LOCATION_ID", user.getLocation().getId());
        params.put("DEPARTMENT_ID", user.getDepartment().getId());
        params.put("ADD_IN_REQUISITION", user.isAddInRequisition());
        logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, "Insert into user : ", params));
        int userId = userInsert.executeAndReturnKey(params).intValue();
        params.clear();
        String sqlString = "INSERT INTO us_user_role (USER_ID, ROLE_ID) VALUES(?, ?)";
        logger.info(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, sqlString, new Object[]{userId, user.getRole().getRoleId()}));
        try {
            this.jdbcTemplate.update(sqlString, userId, user.getRole().getRoleId());
            return userId;
        } catch (DataAccessException e) {
            logger.error(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, e));
            return 0;
        }
    }

    @Override
    public User getUserByUserId(int userId) {
        String sqlString = UserDaoImpl.sqlUserString + " WHERE u.USER_ID=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        try {
            return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new UserRowMapper());
        } catch (Exception e) {
            logger.error(LogUtils.buildStringForLog(GlobalConstants.TAG_SYSTEM, e));
            return null;
        }
    }

    @Override
    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId) {
        String sqlString = "SELECT count(*) from us_can_create_roles ccr where ccr.ROLE_ID=:roleId AND ccr.CAN_CREATE_ROLE=:canCreateRoleId";
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("canCreateRoleId", canCreateRoleId);
        Integer i = this.namedParameterJdbcTemplate.queryForObject(sqlString, params, Integer.class);
        return (i != null && i> 0);
    }

    @Override
    public int updateUser(User user) {
        Map<String, Object> params = new HashMap<>();
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String sqlString = "UPDATE us_user SET NAME=:name, PHONE_NO=:phoneNo, ACTIVE=:active, OUTSIDE_ACCESS=:outsideAccess, LAST_MODIFIED_BY=:curUser, LAST_MODIFIED_DATE=:curDate, LOCATION_ID=:locationId, DEPARTMENT_ID=:departmentId, ADD_IN_REQUISITION=:addInRequisition WHERE USER_ID=:userId";
        params.put("phoneNo", user.getPhoneNo());
        params.put("name", user.getName());
        params.put("active", user.isActive());
        params.put("outsideAccess", user.isOutsideAccess());
        params.put("curUser", curUser);
        params.put("curDate", curDate);
        params.put("locationId", user.getLocation().getId());
        params.put("departmentId", user.getDepartment().getId());
        params.put("addInRequisition", user.isAddInRequisition());
        params.put("userId", user.getUserId());
        params.put("roleId", user.getRole().getRoleId());
        int rowsEffected = this.namedParameterJdbcTemplate.update(sqlString, params);
        sqlString = "UPDATE us_user_role ur SET ur.ROLE_ID=:roleId WHERE USER_ID=:userId";
        rowsEffected += this.namedParameterJdbcTemplate.update(sqlString, params);
        return rowsEffected;
    }

    @Override
    public int resetFailedAttemptsByUserId(int userId) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        Map<String, Object> params = new HashMap<>();
        params.put("curUser", curUser);
        params.put("curDate", curDate);
        params.put("userId", userId);
        String sqlString = "UPDATE `us_user` SET FAILED_ATTEMPTS=0, LAST_MODIFIED_DATE=:curDate, LAST_MODIFIED_BY=:curUser WHERE USER_ID=:userId";
        return this.namedParameterJdbcTemplate.update(sqlString, params);
    }
}
