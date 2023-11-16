/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cc.altius.hrApplication.dao.CandidateDao;
import cc.altius.hrApplication.model.Candidate;
import cc.altius.hrApplication.model.rowMapper.CandidateRowMapper;

/**
 *
 * @author Akil Mahimwala
 */
@Repository
public class CandidateDaoImpl implements CandidateDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    // private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        // this.namedParameterJdbcTemplate = new
        // NamedParameterJdbcTemplate(this.dataSource);
    }

    public List<Candidate> searchForCandidates(String searchText, int searchNo) {
        String sql = "SELECT  "
                + "    c.CANDIDATE_ID, c.INTERVIEW_TRACKER_NO, c.PERCENTAGE,  "
                + "    g.GENDER_ID `G_ID`, g.GENDER_DESC `G_VALUE`, "
                + "    ms.MARITAL_STATUS_ID `MS_ID`, ms.MARITAL_STATUS_DESC `MS_VALUE`, "
                + "    r.REFERRAL_ID `R_ID`, r.REFERRAL_DESC `R_VALUE`, "
                + "    c.FIRST_NAME, c.LAST_NAME, c.DOB, c.ADHAR_NUMBER, c.MOBILE_NUMBER,  "
                + "    c.BLOOD_GROUP, c.PRIMARY_EMAIL, c.SECONDARY_EMAIL, c.ADDRESS1, c.ADDRESS2,  "
                + "    c.EXPERIENCE, c.PAN_NUMBER, c.COMPUTER_KNOWLEDGE, c.WORK_DURATION,  "
                + "    c.`WORK`, c.LANGUAGE_ID,  "
                + "    l.LOCATION_ID `L_ID`, l.LOCATION_NAME `L_VALUE`, "
                + "    c.REFERRAL_FROM, c.LAST_COMPANY_NAME, c.SALARY, c.CREATED_DATE, c.PF_ACCOUNT,  "
                + "    c.ZIP_ID, c.LAST_MODIFIED_DATE, c.VACCINATION_INFORMATION, c.ABROAD_CITY, c.ABROAD_COUNTRY, "
                + "    c.ABROAD_INSTITUTE_NAME, c.ABROAD_STREAM, c.ABROAD_QUALIFICATION, c.ABROAD_YEAR,  "
                + "    d.DEPARTMENT_ID `D_ID`, d.DEPARTMENT_DESC `D_VALUE`, "
                + "    city.CITY_ID `CITY_ID`, city.CITY_DESC `CITY_VALUE`, s.STATE_ID `CITY_S_ID`, s.STATE_NAME `CITY_S_VALUE`, "
                + "    c.FATHER_NAME_1, c.FATHER_OCCUPATION, c.MOTHER_NAME, c.MOTHER_OCCUPATION, c.SIBLINGS_COUNT,  "
                + "    c.FAMILY_DETAILS "
                + "FROM candidate c  "
                + "LEFT JOIN gender g ON c.GENDER_ID=g.GENDER_ID "
                + "LEFT JOIN marital_status ms ON c.MARITAL_STATUS_ID=ms.MARITAL_STATUS_ID "
                + "LEFT JOIN referral r ON c.REFERAL_ID=r.REFERRAL_ID "
                + "LEFT JOIN location l ON l.LOCATION_ID=c.LOCATION_ID "
                + "LEFT JOIN department d ON c.DEPARTMENT_ID=d.DEPARTMENT_ID "
                + "LEFT JOIN city ON c.CITY_ID=city.CITY_ID "
                + "LEFT JOIN state s ON city.STATE_ID=s.STATE_ID"
                + "WHERE ";
        switch (searchNo) {
            case 0:
                sql += "c.`FIRST_NAME` LIKE '%" + searchText + "%' OR  c.`LAST_NAME` LIKE '%" + searchText
                        + "%'  OR c.`MOBILE_NUMBER`='" + searchText + "'  OR c.`PRIMARY_EMAIL`='" + searchText
                        + "' OR c.`SECONDARY_EMAIL`='" + searchText + "'; ";
                break;
            case 1:
                sql += "c.`MOBILE_NUMBER`='" + searchText + "' ;";
                break;
            case 2:
                sql += " c.`FIRST_NAME` LIKE '%" + searchText + "%' OR  c.`LAST_NAME` LIKE '%" + searchText + "%'; ";
                break;
            case 3:
                sql += " c.`PRIMARY_EMAIL`='" + searchText + "' OR c.`SECONDARY_EMAIL`='" + searchText + "'; ";
                break;
        }
        return this.jdbcTemplate.query(sql, new CandidateRowMapper());
    }

}
