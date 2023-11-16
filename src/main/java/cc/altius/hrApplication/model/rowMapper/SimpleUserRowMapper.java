/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.SimpleUser;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class SimpleUserRowMapper implements RowMapper<SimpleUser> {

    private final String prefix;

    @Override
    public SimpleUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SimpleUser(rs.getString(prefix + "USER_ID"), rs.getString(prefix + "NAME"));
    }

}
