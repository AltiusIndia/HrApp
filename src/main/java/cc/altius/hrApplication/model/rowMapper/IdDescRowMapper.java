/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.IdDesc;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class IdDescRowMapper implements RowMapper<IdDesc> {

    private final String prefix;

    @Override
    public IdDesc mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IdDesc(rs.getString(prefix + "ID"), rs.getString(prefix + "DESCRIPTION"));
    }

}
