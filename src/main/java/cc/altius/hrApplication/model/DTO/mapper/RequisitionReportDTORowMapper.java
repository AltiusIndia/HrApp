/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.model.DTO.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cc.altius.hrApplication.model.DTO.RequisitionReportDTO;
import cc.altius.hrApplication.model.rowMapper.IdDescRowMapper;

/**
 *
 * @author Akil Mahimwala
 */
public class RequisitionReportDTORowMapper implements RowMapper<RequisitionReportDTO> {

    @Override
    public RequisitionReportDTO mapRow(ResultSet rs, int i) throws SQLException {
        RequisitionReportDTO reportDTO = new RequisitionReportDTO();
        reportDTO.setRequisitionId(rs.getInt("REQUISITION_ID"));
        reportDTO.setRequisitionUniqueId(rs.getString("REQUISITION_UNIQUE_ID"));
        reportDTO.setCreatedDate(rs.getDate("CREATED_DATE"));
        reportDTO.setRequisitionStatus(new IdDescRowMapper("R_").mapRow(rs, i));
        reportDTO.setProcess(new IdDescRowMapper("P_").mapRow(rs, 1));
        reportDTO.setDepartment(new IdDescRowMapper("D_").mapRow(rs, 1));
        reportDTO.setLocation(new IdDescRowMapper("L_").mapRow(rs, 1));
        reportDTO.setRecruitmentLevel(new IdDescRowMapper("RL_").mapRow(rs, 1));
        reportDTO.setHiringCount(rs.getInt("HIRING_COUNT"));
        reportDTO.setHrBufferCount(rs.getInt("HR_BUFFER_COUNT"));
        reportDTO.setTrainingStartDate(rs.getDate("TRAINING_DATE_START"));
        reportDTO.setHrComments(rs.getString("HR_COMMENTS"));
        reportDTO.setAssignedToHr(rs.getInt("CANDIDATES_ASSIGNED_TO_HR"));
        reportDTO.setInterviewsTakenByHr(rs.getInt("INTERVIEWS_TAKEN_BY_HR"));
        reportDTO.setAssignedToOps(rs.getInt("CANDIDATES_ASSIGNED_TO_OPS"));
        reportDTO.setInterviewsTakenByOps(rs.getInt("INTERVIEWS_TAKEN_BY_OPS"));
        reportDTO.setSelectedByOps(rs.getInt("SELECTED_BY_OPS"));
        reportDTO.setAchieved(rs.getInt("ACHIEVED"));
        return reportDTO;
    }

}
