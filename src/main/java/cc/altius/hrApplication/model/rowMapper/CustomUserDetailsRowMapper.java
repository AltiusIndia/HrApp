/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.Role;

/**
 *
 * @author Akil Mahimwala
 */
public class CustomUserDetailsRowMapper implements RowMapper<CustomUserDetails> {

    @Override
    public CustomUserDetails mapRow(ResultSet rs, int i) throws SQLException {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserId(rs.getInt("USER_ID"));
        customUserDetails.setEmailId(rs.getString("EMAIL_ID"));
        customUserDetails.setName(rs.getString("NAME"));
        customUserDetails.setPassword(rs.getString("PASSWORD"));
        customUserDetails.setActive(rs.getBoolean("ACTIVE"));
        customUserDetails.setExpired(rs.getBoolean("EXPIRED"));
        customUserDetails.setFailedAttempts(rs.getInt("FAILED_ATTEMPTS"));
        customUserDetails.setExpiresOn(rs.getDate("EXPIRES_ON"));
        customUserDetails.setOutsideAccess(rs.getBoolean("OUTSIDE_ACCESS"));
        customUserDetails.setLastLoginDate(rs.getTimestamp("LAST_LOGIN_DATE"));
        customUserDetails.setDepartment(new IdDescRowMapper("D_").mapRow(rs, i));
        customUserDetails.setLocation(new IdDescRowMapper("L_").mapRow(rs, i));
        customUserDetails.setRole(new Role(rs.getString("ROLE_ID"), rs.getString("ROLE_NAME")));
        return customUserDetails;
    }
}
