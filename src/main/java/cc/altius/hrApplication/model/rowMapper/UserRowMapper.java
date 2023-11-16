/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.User;

/**
 *
 * @author Akil Mahimwala
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("USER_ID"));
        user.setName(rs.getString("NAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setActive(rs.getBoolean("ACTIVE"));
        user.setExpired(rs.getBoolean("EXPIRED"));
        user.setFailedAttempts(rs.getInt("FAILED_ATTEMPTS"));
        user.setExpiresOn(rs.getDate("EXPIRES_ON"));
        user.setRole(new RoleRowMapper().mapRow(rs, i));
        user.setOutsideAccess(rs.getBoolean("OUTSIDE_ACCESS"));
        user.setLastLoginDate(rs.getTimestamp("LAST_LOGIN_DATE"));
        user.setEmailId(rs.getString("EMAIL_ID"));
        user.setPhoneNo(rs.getString("PHONE_NO"));
        user.setLocation(new IdDescRowMapper("L_").mapRow(rs, i));
        user.setDepartment(new IdDescRowMapper("D_").mapRow(rs, i));
        user.setAddInRequisition(rs.getBoolean("ADD_IN_REQUISITION"));
        return user;
    }
}
