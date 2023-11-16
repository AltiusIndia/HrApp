/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.City;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@RequiredArgsConstructor
public class CityRowMapper implements RowMapper<City> {

    private final String prefixCity;
    private final String prefixState;

    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new City(rs.getString(prefixCity + "ID"), rs.getString(prefixState + "DESCRIPTION"), new IdDescRowMapper(this.prefixState).mapRow(rs, rowNum));
    }

}
