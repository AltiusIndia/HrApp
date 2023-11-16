/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.DTO.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.DTO.CandidateDashboardDTO;

/**
 *
 * @author Akil Mahimwala
 */
public class CandidateDashboardDTORowMapper implements RowMapper<CandidateDashboardDTO> {

    @Override
    public CandidateDashboardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CandidateDashboardDTO(
                rs.getInt("candidatesAssignedToHr"),
                rs.getInt("interviewsTakenByHr"),
                rs.getInt("candidatesAssignedToOps"),
                rs.getInt("interviewsTakenByOps"),
                rs.getInt("finalSelected")
        );
    }

}
