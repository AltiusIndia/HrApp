/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cc.altius.hrApplication.dao.RequisitionDao;
import cc.altius.hrApplication.exception.GeneralException;
import cc.altius.hrApplication.framework.GlobalConstants;
import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.Requisition;
import cc.altius.hrApplication.model.DTO.RequisitionReportDTO;
import cc.altius.hrApplication.model.DTO.mapper.RequisitionReportDTORowMapper;
import cc.altius.hrApplication.model.rowMapper.RequisitionListResultSetExtractor;
import cc.altius.hrApplication.model.rowMapper.RequisitionResultSetExtractor;
import cc.altius.utils.DateUtils;
import cc.altius.utils.StringUtils;

/**
 *
 * @author Akil Mahimwala
 */
@Repository
public class RequisitionDaoImpl implements RequisitionDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    private final static String requisitionSql = "SELECT  "
            + "    r.REQUISITION_ID, r.REQUISITION_UNIQUE_ID, r.HIRING_COUNT, r.HR_BUFFER_COUNT, r.HR_CLOSING_DATE,  "
            + "    r.HR_COMMENTS, r.TRAINING_DATE_START, r.SALARY_BAND, r.COMMENTS, r.OPERATION_WINDOW,  "
            + "    l.`ID` `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION`, d.`ID` `D_ID`, d.`DESCRIPTION` `D_DESCRIPTION`, "
            + "    bu.USER_ID `BU_USER_ID`, bu.`NAME` `BU_NAME`, p.`ID` `P_ID`, p.`CODE` `P_CODE`, p.`DESCRIPTION` `P_DESCRIPTION`, "
            + "    pc.`ID` `PC_ID`, pc.`DESCRIPTION` `PC_DESCRIPTION`, et.`ID` `ET_ID`, et.`DESCRIPTION` `ET_DESCRIPTION`, "
            + "    rt.`ID` `RT_ID`, rt.`DESCRIPTION` `RT_DESCRIPTION`, rl.`ID` `RL_ID`, rl.`DESCRIPTION` `RL_DESCRIPTION`, "
            + "    lang.`ID` `LANG_ID`, lang.`DESCRIPTION` `LANG_DESCRIPTION`, minq.`ID` `MINQ_ID`, minq.`DESCRIPTION` `MINQ_DESCRIPTION`, "
            + "    ts.`ID` `TS_ID`, ts.`DESCRIPTION` `TS_DESCRIPTION`, cp.`ID` `CP_ID`, cp.`DESCRIPTION` `CP_DESCRIPTION`, "
            + "    gr.`ID` `GR_ID`, gr.`DESCRIPTION` `GR_DESCRIPTION`, woc.ID `WOC_ID`, woc.`DESCRIPTION` `WOC_DESCRIPTION`,  "
            + "    wop.ID `WOP_ID`, wop.`DESCRIPTION` `WOP_DESCRIPTION`, sd.ID `SD_ID`, sd.`DESCRIPTION` `SD_DESCRIPTION`, "
            + "    r.CREATED_DATE, cb.USER_ID `CB_USER_ID`, cb.`NAME` `CB_NAME`, r.LAST_MODIFIED_DATE, lmb.USER_ID `LMB_USER_ID`, lmb.`NAME` `LMB_NAME`, "
            + "    rs.`ID` `RS_ID`, rs.`DESCRIPTION` `RS_DESCRIPTION` "
            + "FROM requisition r  "
            + "LEFT JOIN mst_location l ON r.LOCATION_ID=l.`ID` "
            + "LEFT JOIN mst_department d ON r.DEPARTMENT_ID=d.`ID` "
            + "LEFT JOIN us_user bu ON r.BU_MANAGER_ID = bu.USER_ID "
            + "LEFT JOIN mst_process p ON r.PROCESS_ID=p.`ID` "
            + "LEFT JOIN mst_process_category pc ON r.PROCESS_CATEGORY_ID=pc.`ID` "
            + "LEFT JOIN mst_employment_type et ON r.EMPLOYMENT_TYPE_ID=et.`ID` "   
            + "LEFT JOIN mst_recruitment_type rt ON r.RECRUITMENT_TYPE_ID=rt.`ID` "
            + "LEFT JOIN mst_recruitment_level rl ON r.RECRUITMENT_LEVEL_ID=rl.`ID` "
            + "LEFT JOIN mst_language lang ON FIND_IN_SET(lang.`ID`,r.`LANGUAGE_IDS`) "
            + "LEFT JOIN mst_min_qualification minq ON r.MIN_QUALIFICATION_ID=minq.`ID` "
            + "LEFT JOIN mst_typing_speed ts ON r.TYPING_SPEED_ID=ts.`ID` "
            + "LEFT JOIN mst_communication_proficiency cp ON r.COMMUNICATION_PROFICIENCY_ID=cp.`ID` "
            + "LEFT JOIN mst_gender_ratio gr ON r.GENDER_RATIO_ID=gr.`ID` "
            + "LEFT JOIN mst_week_off_pattern wop ON r.WEEK_OFF_PATTERN_ID=wop.ID "
            + "LEFT JOIN mst_week_off_count woc ON r.WEEK_OFF_COUNT_ID=woc.ID "
            + "LEFT JOIN mst_shift_duration sd ON r.SHIFT_DURATION_ID=sd.ID "
            + "LEFT JOIN us_user cb ON r.CREATED_BY=cb.USER_ID "
            + "LEFT JOIN us_user lmb ON r.LAST_MODIFIED_BY=lmb.USER_ID  "
            + "LEFT JOIN mst_requisition_status rs ON r.REQUISITION_STATUS_ID=rs.`ID` "
            + "WHERE  TRUE ";

    @Override
    public List<RequisitionReportDTO> getRequsitionDashboardReport(String statusId, String locationId) {
        CustomUserDetails curUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT "
                + "    r.REQUISITION_ID, r.REQUISITION_UNIQUE_ID, r.CREATED_DATE, "
                + "    rs.`ID` `R_ID`, rs.`DESCRIPTION` `R_DESCRIPTION`, "
                + "    p.`ID` `P_ID`, p.`DESCRIPTION` `P_DESCRIPTION`, "
                + "    d.`ID` `D_ID`, d.`DESCRIPTION` `D_DESCRIPTION`, "
                + "    l.`ID` `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION`, "
                + "    rl.`ID` `RL_ID`, rl.`DESCRIPTION` `RL_DESCRIPTION`, "
                + "    r.HIRING_COUNT, r.HR_BUFFER_COUNT, "
                + "    r.TRAINING_DATE_START, r.HR_COMMENTS, "
                + "    COUNT(oyo.CANDIDATE_STATUS_ID_HR) `CANDIDATES_ASSIGNED_TO_HR`, "
                + "    SUM(IF(oyo.CANDIDATE_STATUS_ID_HR in (2,3,4,5,6,7,11,14,15,16), 1,0)) `INTERVIEWS_TAKEN_BY_HR`, "
                + "    SUM(IF(oyo.CANDIDATE_STATUS_ID_HR =14, 1,0)) `CANDIDATES_ASSIGNED_TO_OPS`, "
                + "    SUM(IF(oyo.CANDIDATE_STATUS_ID_HR in (4,5,6,7,8,9), 1,0)) `INTERVIEWS_TAKEN_BY_OPS`, "
                + "    SUM(IF(oyo.CANDIDATE_STATUS_ID_OOPS in(7,10), 1,0)) `SELECTED_BY_OPS`, "
                + "    SUM(IF(oyo.REQUISITION_FINAL_STATUS_ID=0,0,1)) `ACHIEVED` "
                + "FROM requisition r "
                + " LEFT JOIN `mst_requisition_status` rs ON r.REQUISITION_STATUS_ID=rs.`ID` "
                + " LEFT JOIN `mst_process` p ON r.PROCESS_ID=p.`ID` "
                + " LEFT JOIN `mst_department` d ON r.DEPARTMENT_ID=d.`ID` "
                + " LEFT JOIN `mst_location` l ON r.LOCATION_ID=l.`ID` "
                + " LEFT JOIN `mst_recruitment_level` rl ON r.RECRUITMENT_LEVEL_ID=rl.`ID` "
                + " LEFT JOIN office_use_only oyo ON r.REQUISITION_ID=oyo.REQUISITION_ID "
                + "WHERE TRUE "
                + "  AND (:statusId=-1 OR r.`REQUISITION_STATUS_ID`=:statusId) "
                + "  AND (:locationId=-1 OR r.`LOCATION_ID`=:locationId) ");
        params.put("statusId", statusId);
        params.put("locationId", locationId);
        if (curUser.getRole().getRoleId().equals("ROLE_OPERATION_EXECUTIVE")) {
            sb.append(" AND r.CREATED_BY=:curUser");
            params.put("curUser", curUser.getUserId());
        }
        switch (curUser.getRole().getRoleId()) {
            case "ROLE_SUPER", "ROLE_HR_HEAD" -> {
            }
            case "ROLE_OPERATION_MANAGER" -> {
                sb.append(" AND r.`DEPARTMENT_ID`=:departmentId");
                params.put("departmentId", curUser.getDepartment().getId());
            }
            case "ROLE_HR_MANAGER", "ROLE_HR_EXECUTIVE" -> {
                sb.append(" and r.`LOCATION_ID`=:curLocation");
                params.put("curLocation", curUser.getLocation().getId());
            }
            default -> {
            }
        }
        // Dont do anything
        sb.append(" GROUP BY r.`REQUISITION_UNIQUE_ID`");
        sb.append(" ORDER BY r.`CREATED_DATE` DESC");
        return this.namedParameterJdbcTemplate.query(sb.toString(), params, new RequisitionReportDTORowMapper());
    }

    @Override
    public List<Requisition> getRequisitionList(String locationId, String statusId, String startDate, String stopDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("locationId", locationId);
        params.put("statusId", statusId);
        params.put("startDate", startDate + " 00:00:00");
        params.put("stopDate", stopDate + " 23:59:59");
        String sqlString = RequisitionDaoImpl.requisitionSql
                + " AND r.CREATED_DATE BETWEEN :startDate AND :stopDate  "
                + " AND (:locationId=-1 OR r.LOCATION_ID=:locationId) "
                + " AND (:statusId=-1 OR :statusId=r.REQUISITION_STATUS_ID) "
                + "ORDER BY r.REQUISITION_ID, lang.`ID`";
        return this.namedParameterJdbcTemplate.query(sqlString, params, new RequisitionListResultSetExtractor());
    }

    @Override
    @Transactional
    public int addRequisition(Requisition r) throws GeneralException {
        SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("requisition").usingGeneratedKeyColumns("REQUISITION_ID");
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String curDateDMY = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.DMY);
        String sql = "SELECT (COUNT(*) + 1)  FROM `requisition` r WHERE r.`CREATED_DATE` BETWEEN '" + curDate.substring(0, 10) + " 00:00:00' AND '" + curDate.substring(0,10) + " 23:59:59'";
        String count = this.jdbcTemplate.queryForObject(sql, String.class);
        Map<String, Object> params = new HashMap<>();
        params.put("id", r.getLocation().getId());
        String locationDescription = this.namedParameterJdbcTemplate.queryForObject("SELECT l.DESCRIPTION FROM mst_location l WHERE l.ID=:id", params, String.class);
        if (locationDescription==null) {
            throw new GeneralException("The Location Id provided does not exist");
        }
        String requisitionCode = "RQ" + locationDescription.substring(0, 3).toUpperCase() + curDateDMY.substring(0, 6).replaceAll("-", "") + curDateDMY.substring(8, 10) + StringUtils.pad(count, '0', 3, 1);
        params.put("REQUISITION_UNIQUE_ID", requisitionCode);
        params.put("LOCATION_ID", r.getLocation().getId());
        params.put("DEPARTMENT_ID", r.getDepartment().getId());
        params.put("BU_MANAGER_ID", r.getBuManager().getUserId());
        params.put("PROCESS_ID", r.getProcess().getId());
        params.put("PROCESS_CATEGORY_ID", r.getProcessCategory().getId());
        params.put("EMPLOYMENT_TYPE_ID", r.getEmploymentType().getId());
        params.put("RECRUITMENT_TYPE_ID", r.getRecruitmentType().getId());
        params.put("RECRUITMENT_LEVEL_ID", r.getRecruitmentLevel().getId());
        params.put("HIRING_COUNT", r.getHiringCount());
        params.put("LANGUAGE_IDS", r.getLanguageIdsString());
        params.put("TRAINING_DATE_START", r.getTrainingStartDate());
        params.put("SALARY_BAND", r.getSalaryBand());
        params.put("MIN_QUALIFICATION_ID", r.getMinQualification().getId());
        params.put("TYPING_SPEED_ID", r.getTypingSpeed().getId());
        params.put("COMMUNICATION_PROFICIENCY_ID", r.getCommunicationProficiency().getId());
        params.put("GENDER_RATIO_ID", r.getGenderRatio().getId());
        params.put("COMMENTS", r.getComments());
        params.put("OPERATION_WINDOW", r.getOperationsWindow());
        params.put("WEEK_OFF_PATTERN_ID", r.getWeekOffPattern().getId());
        params.put("WEEK_OFF_COUNT_ID", r.getWeekOffCount().getId());
        params.put("SHIFT_DURATION_ID", r.getShiftDuration().getId());
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("REQUISITION_STATUS_ID", GlobalConstants.REQUISITION_STATUS_OPEN);
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public int editRequisition(Requisition r) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String sqlString = "UPDATE requisition r SET r.HIRING_COUNT=:hiringCount, r.TRAINING_DATE_START=:trainingDateStart, r.COMMENTS=:comments, r.REQUISITION_STATUS_ID=:statusId, r.LAST_MODIFIED_DATE=:curDate, r.LAST_MODIFIED_BY=:curUser, r.HR_COMMENTS=:hrComments, r.HR_BUFFER_COUNT=:hrBufferCount, r.HR_CLOSING_DATE=:hrClosingDate WHERE r.REQUISITION_ID=:requisitionId";
        Map<String, Object> params = new HashMap<>();
        params.put("requisitionId", r.getRequisitionId());
        params.put("hiringCount", r.getHiringCount());
        params.put("trainingDateStart", r.getTrainingStartDate());
        params.put("comments", r.getComments());
        params.put("statusId", r.getStatus().getId());
        params.put("curDate", curDate);
        params.put("curUser", curUser);
        params.put("hrComments", r.getHrComments());
        params.put("hrBufferCount", r.getHrBufferCount());
        params.put("hrClosingDate", r.getHrClosingDate());
        return this.namedParameterJdbcTemplate.update(sqlString, params);
    }

    @Override
    public Requisition getRequisitionById(int requisitionId) {
        Map<String, Object> params = new HashMap<>();
        params.put("requisitionId", requisitionId);
        String sqlString = RequisitionDaoImpl.requisitionSql
                + " AND r.REQUISITION_ID = :requisitionId "
                + "ORDER BY r.REQUISITION_ID, lang.`ID`";
        return this.namedParameterJdbcTemplate.query(sqlString, params, new RequisitionResultSetExtractor());
    }

}
