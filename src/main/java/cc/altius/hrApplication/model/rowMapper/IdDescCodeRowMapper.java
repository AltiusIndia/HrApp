/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.IdDescCode;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class IdDescCodeRowMapper implements RowMapper<IdDescCode> {

    private final String prefix;

    @Override
    public IdDescCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IdDescCode(rs.getString(prefix + "ID"), rs.getString(prefix + "DESCRIPTION"), rs.getString(prefix + "CODE"));
    }

}
