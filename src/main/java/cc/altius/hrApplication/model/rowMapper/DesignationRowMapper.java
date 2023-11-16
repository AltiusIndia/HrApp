/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.IdDescCodeActive;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class DesignationRowMapper implements RowMapper<IdDescCodeActive> {

    private final String prefix;

    @Override
    public IdDescCodeActive mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IdDescCodeActive(rs.getString(prefix + "ID"), rs.getString(prefix + "VALUE"), rs.getString(prefix + "CODE"), rs.getBoolean(prefix + "ACTIVE"));
    }

}
