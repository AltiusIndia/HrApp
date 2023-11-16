/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.dao.impl;

import cc.altius.hrApplication.dao.MasterDao;
import cc.altius.hrApplication.model.City;
import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.IdDescActive;
import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.SimpleUserWithId;
import cc.altius.hrApplication.model.Process;
import cc.altius.hrApplication.model.rowMapper.CityRowMapper;
import cc.altius.hrApplication.model.rowMapper.IdDescActiveRowMapper;
import cc.altius.hrApplication.model.rowMapper.IdDescRowMapper;
import cc.altius.hrApplication.model.rowMapper.ProcessRowMapper;
import cc.altius.hrApplication.model.rowMapper.SimpleUserWithIdRowMapper;
import cc.altius.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Akil Mahimwala
 */
@Repository
public class MasterDaoImpl implements MasterDao {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Override
    public List<IdDesc> getSimpleLocationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT l.`ID`, l.`DESCRIPTION` FROM mst_location l WHERE (:active=false OR l.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getSimpleDepartmentList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT d.`ID`, d.`DESCRIPTION` FROM mst_department d WHERE (:active=false OR d.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getSimpleDesignationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT d.`ID`, d.`DESCRIPTION` FROM mst_designation d WHERE (:active=false OR d.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDescActive> getDepartmentList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT d.`ID`, d.`DESCRIPTION`, d.`ACTIVE` FROM mst_department d WHERE (:active=false OR d.ACTIVE) ORDER BY d.`DESCRIPTION`", params, new IdDescActiveRowMapper(""));
    }

    @Override
    public int addDepartment(IdDescActive department) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(this.dataSource).withTableName("mst_department").usingGeneratedKeyColumns("ID");
        Map<String, Object> params = new HashMap<>();
        int curUser =  ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        params.put("DESCRIPTION", department.getDescription());
        params.put("ACTIVE", department.isActive());
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public int editDepartment(IdDescActive department) {
        String sql = "UPDATE mst_department d SET `DESCRIPTION`=:departmentDesc, `ACTIVE`=:active, LAST_MODIFIED_BY=:curUser, LAST_MODIFIED_DATE=:curDate WHERE `ID`=:departmentId";
        Map<String, Object> params = new HashMap<>();
        int curUser =  ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        params.put("departmentDesc", department.getDescription());
        params.put("active", department.isActive());
        params.put("departmentId", department.getId());
        params.put("curUser", curUser);
        params.put("curDate", curDate);
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public IdDescActive getDepartmentById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return this.namedParameterJdbcTemplate.queryForObject("SELECT d.`ID`, d.`DESCRIPTION`, d.`ACTIVE` FROM mst_department d WHERE d.`ID`=:id", params, new IdDescActiveRowMapper(""));
    }

    @Override
    public List<Process> getProcessList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT p.`ID`, p.`CODE`, p.`DESCRIPTION`, l.`ID` `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION`, p.`ACTIVE`, u.USER_ID `BU_USER_ID`, u.NAME `BU_NAME` FROM mst_process p LEFT JOIN us_user u ON p.BU_MANAGER_ID=u.USER_ID LEFT JOIN mst_location l ON p.`LOCATION_ID`=l.`ID` WHERE (:active=false OR p.ACTIVE) ORDER BY p.`DESCRIPTION`", params, new ProcessRowMapper(""));
    }

    @Override
    public int addProcess(Process process) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(this.dataSource).withTableName("mst_process").usingGeneratedKeyColumns("ID");
        Map<String, Object> params = new HashMap<>();
        int curUser =  ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        params.put("DESCRIPTION", process.getDescription());
        params.put("CODE", process.getCode());
        params.put("ACTIVE", process.isActive());
        params.put("BU_MANAGER_ID", process.getBuManager().getUserId());
        params.put("LOCATION_ID", process.getLocation().getId());
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public int editProcess(Process process) {
        String sql = "UPDATE mst_process p SET `DESCRIPTION`=:processDesc, `CODE`=:processCode, `BU_MANAGER_ID`=:buManager, `ACTIVE`=:active, LAST_MODIFIED_BY=:curUser, LAST_MODIFIED_DATE=:curDate WHERE `ID`=:processId";
        Map<String, Object> params = new HashMap<>();
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        params.put("processDesc", process.getDescription());
        params.put("processCode", process.getCode());
        params.put("active", process.isActive());
        params.put("buManager", process.getBuManager().getUserId());
        params.put("processId", process.getId());
        params.put("curUser", curUser);
        params.put("curDate", curDate);
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Process getProcessById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("processId", id);
        return this.namedParameterJdbcTemplate.queryForObject("SELECT p.`ID`, p.`CODE`, p.`DESCRIPTION`, l.`ID` `L_ID`, l.`DESCRIPTION` `L_DESCRIPTION`, p.`ACTIVE`, u.USER_ID `BU_USER_ID`, u.NAME `BU_NAME` FROM mst_process p LEFT JOIN us_user u ON p.BU_MANAGER_ID=u.USER_ID LEFT JOIN mst_location l ON p.LOCATION_ID=l.`ID` WHERE p.`ID`=:processId", params, new ProcessRowMapper(""));
    }

    @Override
    public List<IdDescActive> getDesignationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT d.`ID`, d.`DESCRIPTION`, d.`ACTIVE` FROM mst_designation d WHERE (:active=false OR d.ACTIVE) ORDER BY d.`DESCRIPTION`", params, new IdDescActiveRowMapper(""));
    }

    @Override
    public int addDesignation(IdDescActive designation) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(this.dataSource).withTableName("mst_designation").usingGeneratedKeyColumns("ID");
        Map<String, Object> params = new HashMap<>();
        params.put("DESCRIPTION", designation.getDescription());
        params.put("ACTIVE", designation.isActive());
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public int editDesignation(IdDescActive designation) {
        String sql = "UPDATE mst_designation d SET `DESCRIPTION`=:designationDesc, `ACTIVE`=:active WHERE `ID`=:designationId";
        Map<String, Object> params = new HashMap<>();
        params.put("designationDesc", designation.getDescription());
        params.put("active", designation.isActive());
        params.put("designationId", designation.getId());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public IdDescActive getDesignationById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("designationId", id);
        return this.namedParameterJdbcTemplate.queryForObject("SELECT d.`ID`, d.`DESCRIPTION`, d.`ACTIVE` FROM mst_designation d WHERE d.`ID`=:designationId", params, new IdDescActiveRowMapper(""));
    }

    @Override
    public List<SimpleUserWithId> getBuManagerList() {
        String sqlString = "SELECT u.USER_ID, u.NAME, u.LOCATION_ID `I_ID` FROM us_user u LEFT JOIN us_user_role ur ON u.USER_ID=ur.USER_ID WHERE u.ADD_IN_REQUISITION=1 AND u.ACTIVE";
        Map<String, Object> params = new HashMap<>();
        return this.namedParameterJdbcTemplate.query(sqlString, params, new SimpleUserWithIdRowMapper(""));
    }

    @Override
    public List<IdDesc> getProcessCategoryList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT pc.`ID`, pc.`DESCRIPTION` FROM mst_process_category pc WHERE (:active=false OR pc.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getEmploymentTypeList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT et.`ID`, et.`DESCRIPTION` FROM mst_employment_type et WHERE (:active=false OR et.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getRecruitmentTypeList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT rt.`ID`, rt.`DESCRIPTION` FROM mst_recruitment_type rt WHERE (:active=false OR rt.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getRecruitmentLevelList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT rl.`ID`, rl.`DESCRIPTION` FROM mst_recruitment_level rl WHERE (:active=false OR rl.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getLanguageList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT l.`ID`, l.`DESCRIPTION` FROM mst_language l WHERE TRUE", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getMinQualificationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT mq.`ID`, mq.`DESCRIPTION` FROM mst_min_qualification mq WHERE (:active=false OR mq.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getTypingSpeedList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT ts.`ID`, ts.`DESCRIPTION` FROM mst_typing_speed ts WHERE (:active=false OR ts.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getCommunicationProficiencyList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT cp.`ID`, cp.`DESCRIPTION` FROM mst_communication_proficiency cp WHERE (:active=false OR cp.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getGenderRatioList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT gr.`ID`, gr.`DESCRIPTION` FROM mst_gender_ratio gr WHERE (:active=false OR gr.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getWeekOffPatternList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT wop.`ID`, wop.`DESCRIPTION` FROM mst_week_off_pattern wop WHERE (:active=false OR wop.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getWeekOffCountList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT woc.`ID`, woc.`DESCRIPTION` FROM mst_week_off_count woc WHERE (:active=false OR woc.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getShiftDurationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT sd.`ID`, sd.`DESCRIPTION` FROM mst_shift_duration sd WHERE (:active=false OR sd.ACTIVE)", params, new IdDescRowMapper(""));
    }

//    @Override
//    public List<IdDesc> getSalaryBandList(boolean active) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public List<IdDesc> getEducationStreamList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT es.`ID`, es.`DESCRIPTION` FROM mst_education_strea es WHERE (:active=false OR es.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<City> getCityList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT c.`ID`, c.`DESCRIPTION`, s.`ID` `S_ID`, s.`DESCRIPTION` `S_DESC` FROM mst_city c LEFT JOIN mst_state s ON c.STATE_ID=s.`ID` WHERE (:active=false OR c.ACTIVE)", params, new CityRowMapper("", "S_"));
    }

    @Override
    public List<IdDesc> getStateList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT s.`ID`, s.`DESCRIPTION` FROM mst_state s WHERE (:active=false OR s.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getGenderList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT g.`ID`, g.`DESCRIPTION` FROM mst_gender g WHERE (:active=false OR g.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getMaritalStatusList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT ms.`ID`, ms.`DESCRIPTION` FROM mst_marital_status ms WHERE (:active=false OR ms.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getQualificationList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT q.`ID`, q.`DESCRIPTION` FROM mst_min_qualification q WHERE (:active=false OR q.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getReferralList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT r.`ID`, r.`DESCRIPTION` FROM mst_referral r WHERE (:active=false OR r.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getRequisitionFinalStatusList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT rs.`ID`, rs.`DESCRIPTION` FROM mst_requisition_final_status rs WHERE (:active=false OR rs.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getRequisitionStatusList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT rs.`ID`, rs.`DESCRIPTION` FROM mst_requisition_status rs WHERE (:active=false OR rs.ACTIVE)", params, new IdDescRowMapper(""));
    }

    @Override
    public List<IdDesc> getWorkList(boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("active", active);
        return this.namedParameterJdbcTemplate.query("SELECT w.`ID`, w.`DESCRIPTION` FROM mst_work w WHERE (:active=false OR w.ACTIVE)", params, new IdDescRowMapper(""));
    }

}
