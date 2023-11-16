/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import cc.altius.hrApplication.model.SimpleUserWithId;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class SimpleUserWithIdRowMapper implements RowMapper<SimpleUserWithId> {

    private final String prefix;

    @Override
    public SimpleUserWithId mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SimpleUserWithId(rs.getString(prefix + "USER_ID"), rs.getString(prefix + "NAME"), rs.getString(prefix + "I_ID"));
    }

}
