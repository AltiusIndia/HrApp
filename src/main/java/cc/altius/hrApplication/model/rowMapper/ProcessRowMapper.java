/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.Process;
import cc.altius.hrApplication.model.SimpleUser;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class ProcessRowMapper implements RowMapper<Process> {
    
    private final String prefix;
    
    @Override
    public Process mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Process(
                rs.getString(prefix + "ID"),
                rs.getString(prefix + "DESCRIPTION"),
                rs.getString(prefix + "CODE"),
                rs.getBoolean(prefix + "ACTIVE"),
                new SimpleUser(rs.getString(prefix + "BU_USER_ID"), prefix + rs.getString("BU_NAME")),
                new IdDesc(rs.getString(prefix + "L_ID"), rs.getString(prefix + "L_DESCRIPTION")));
    }
    
}
