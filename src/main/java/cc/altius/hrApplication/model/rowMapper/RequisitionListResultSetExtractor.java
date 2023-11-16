/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.Requisition;

/**
 *
 * @author Akil Mahimwala
 */
public class RequisitionListResultSetExtractor implements ResultSetExtractor<List<Requisition>> {

    @Override
    public List<Requisition> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Requisition> rList = new LinkedList<>();
        while (rs.next()) {
            Requisition r = new Requisition(rs.getInt("REQUISITION_ID"), rs.getString("REQUISITION_UNIQUE_ID"));
            int idx = rList.indexOf(r);
            if (idx == -1) {
                r.setLocation(new IdDescRowMapper("L_").mapRow(rs, 1));
                r.setDepartment(new IdDescRowMapper("D_").mapRow(rs, 1));
                r.setBuManager(new SimpleUserRowMapper("BU_").mapRow(rs, 1));
                r.setProcess(new IdDescCodeRowMapper("P_").mapRow(rs, 1));
                r.setProcessCategory(new IdDescRowMapper("PC_").mapRow(rs, 1));
                r.setEmploymentType(new IdDescRowMapper("ET_").mapRow(rs, 1));
                r.setRecruitmentType(new IdDescRowMapper("RT_").mapRow(rs, 1));
                r.setRecruitmentLevel(new IdDescRowMapper("RL_").mapRow(rs, 1));
                r.setHiringCount(rs.getInt("HIRING_COUNT"));
                r.setTrainingStartDate(rs.getString("TRAINING_DATE_START"));
                r.setSalaryBand(rs.getString("SALARY_BAND"));
                r.setMinQualification(new IdDescRowMapper("MINQ_").mapRow(rs, 1));
                r.setTypingSpeed(new IdDescRowMapper("TS_").mapRow(rs, 1));
                r.setCommunicationProficiency(new IdDescRowMapper("CP_").mapRow(rs, 1));
                r.setGenderRatio(new IdDescRowMapper("GR_").mapRow(rs, 1));
                r.setComments(rs.getString("COMMENTS"));
                r.setOperationsWindow(rs.getString("OPERATION_WINDOW"));
                r.setWeekOffPattern(new IdDescRowMapper("WOP_").mapRow(rs, 1));
                r.setWeekOffCount(new IdDescRowMapper("WOC_").mapRow(rs, 1));
                r.setShiftDuration(new IdDescRowMapper("SD_").mapRow(rs, 1));
                r.setCreatedBy(new SimpleUserRowMapper("CB_").mapRow(rs, 1));
                r.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
                r.setLastModifiedBy(new SimpleUserRowMapper("LMB_").mapRow(rs, 1));
                r.setLastModifiedDate(rs.getTimestamp("LAST_MODIFIED_DATE"));
                r.setStatus(new IdDescRowMapper("RS_").mapRow(rs, 1));
                r.setHrBufferCount(rs.getInt("HR_BUFFER_COUNT"));
                if (rs.wasNull()) {
                    r.setHrBufferCount(null);
                }
                r.setHrComments(rs.getString("HR_COMMENTS"));
                r.setHrClosingDate(rs.getString("HR_CLOSING_DATE"));
                rList.add(r);
            } else {
                r = rList.get(idx);
            }
            IdDesc l = new IdDescRowMapper("LANG_").mapRow(rs, 1);
            if (!rs.wasNull()) {
                r.getLanguageList().add(l);
            }
        }
        return rList;
    }
}
