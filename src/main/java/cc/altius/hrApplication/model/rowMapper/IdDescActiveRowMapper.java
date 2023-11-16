/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.IdDescActive;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class IdDescActiveRowMapper implements RowMapper<IdDescActive> {

    private final String prefix;

    @Override
    public IdDescActive mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IdDescActive(rs.getString(prefix + "ID"), rs.getString(prefix + "DESCRIPTION"), rs.getBoolean(prefix + "ACTIVE"));
    }

}
