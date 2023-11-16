/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import cc.altius.hrApplication.dao.ReportDao;
import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.DTO.CandidateDashboardDTO;
import cc.altius.hrApplication.model.DTO.mapper.CandidateDashboardDTORowMapper;

/**
 *
 * @author Akil Mahimwala
 */
@Repository
public class ReportDaoImpl implements ReportDao {

    private DataSource dataSource;
    // private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        // this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Override
    public CandidateDashboardDTO getCandidateDashboard(String startDate, String stopDate, String locationId, String processCode) {
        CustomUserDetails curUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate + " 00:00:00");
        params.put("stopDate", stopDate + " 23:59:50");
        params.put("processCode", processCode);
        StringBuilder sb = new StringBuilder("SELECT "
                + "    COUNT(csoyohr.CANDIDATE_STATUS_ID) `candidatesAssignedToHr`,"
                + "    SUM(IF(csoyohr.CANDIDATE_STATUS_ID in (2,3,4,5,6,7,11,14,15,16,21), 1,0)) `interviewsTakenByHr`,"
                + "    SUM(IF(csoyohr.CANDIDATE_STATUS_ID =14, 1,0)) `candidatesAssignedToOps`,"
                + "    SUM(IF(csoyoop.CANDIDATE_STATUS_ID in (4,5,6,7,8,9,10), 1,0)) `interviewsTakenByOps`,"
                + "    SUM(IF(csoyoop.CANDIDATE_STATUS_ID =7, 1,0)) `finalSelected` "
                + "FROM candidate c "
                + " LEFT JOIN office_use_only oyo ON c.CANDIDATE_ID=oyo.CANDIDATE_ID "
                + " LEFT JOIN candidate_status csoyohr ON oyo.CANDIDATE_STATUS_ID_HR=csoyohr.CANDIDATE_STATUS_ID "
                + " LEFT JOIN candidate_status csoyoop ON oyo.CANDIDATE_STATUS_ID_OOPS=csoyoop.CANDIDATE_STATUS_ID "
                + "WHERE c.`CREATED_DATE` BETWEEN :startDate AND :stopDate "
                + "   AND (:processCode='' OR oyo.`PROCESS_APPLIED_FOR`=:processCode)");
        if (curUser.getRole().getRoleId().equals("ROLE_SUPER") || curUser.getRole().getRoleId().equals("ROLE_HR_HEAD")) {

        } else if (curUser.getRole().getRoleId().equals("ROLE_HR_MANAGER")) {
            sb.append(" AND c.`LOCATION_ID`=:locationId");
            params.put("locationId", curUser.getLocation().getId());
        }
        if (curUser.getRole().getRoleId().equals("ROLE_HR_EXECUTIVE")) {
            sb.append(" AND ou.`ASSIGN_TO_HR_ID_1`=:curUser");
            params.put("curUser", curUser.getUserId());
        }
        return this.namedParameterJdbcTemplate.queryForObject(sb.toString(), params, new CandidateDashboardDTORowMapper());
    }

}
