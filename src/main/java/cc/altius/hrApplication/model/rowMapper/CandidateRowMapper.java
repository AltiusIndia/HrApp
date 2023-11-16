package cc.altius.hrApplication.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Akil Mahimwala
 * @createdOn 2017/12/24
 */
import cc.altius.hrApplication.model.Candidate;

public class CandidateRowMapper implements RowMapper<Candidate> {

    @Override
    public Candidate mapRow(ResultSet rs, int i) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(rs.getInt("CANDIDATE_ID"));
        candidate.setInterviewTrackerNo(rs.getString("INTERVIEW_TRACKER_NO"));
        candidate.setPercentage(rs.getString("PERCENTAGE"));
        candidate.setGender(new IdDescRowMapper("G_").mapRow(rs, i));
        candidate.setMaritalStatus(new IdDescRowMapper("MS_").mapRow(rs, i));
        candidate.setReferral(new IdDescRowMapper("R_").mapRow(rs, i));
        candidate.setFirstName(rs.getString("FIRST_NAME"));
        candidate.setLastName(rs.getString("LAST_NAME"));
        candidate.setDob(rs.getDate("DOB"));
        candidate.setAadharNumber(rs.getString("ADHAR_NUMBER"));
        candidate.setMobileNumber(rs.getString("MOBILE_NUMBER"));
        candidate.setBloodGroup(rs.getString("BLOOD_GROUP"));
        candidate.setPrimaryEmail(rs.getString("PRIMARY_EMAIL"));
        candidate.setSecondaryEmail(rs.getString("SECONDARY_EMAIL"));
        candidate.setAddress1(rs.getString("ADDRESS1"));
        candidate.setAddress2(rs.getString("ADDRESS2"));
        candidate.setExperience(rs.getBoolean("EXPERIENCE"));
        candidate.setPanNumber(rs.getString("PAN_NUMBER"));
        candidate.setComputerKnowledge(rs.getString("COMPUTER_KNOWLEDGE"));
        candidate.setWorkDuration(rs.getString("WORK_DURATION"));
        candidate.setWork(rs.getString("WORK"));
        candidate.setLanguageProficiency(rs.getString("LANGUAGE_ID"));
        candidate.setLocation(new IdDescRowMapper("L_").mapRow(rs, i));
        candidate.setReferralFrom(rs.getString("REFERRAL_FROM"));
        candidate.setLastCompanyName(rs.getString("LAST_COMPANY_NAME"));
        candidate.setSalary(rs.getLong("SALARY"));
        candidate.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
        candidate.setPfAccount(rs.getString("PF_ACCOUNT"));
        candidate.setZip(rs.getString("ZIP_ID"));
        candidate.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        candidate.setVaccinationInformation(rs.getString("VACCINATION_INFORMATION"));
        candidate.setAbroadCity(rs.getString("ABROAD_CITY"));
        candidate.setAbroadCountry(rs.getString("ABROAD_COUNTRY"));
        candidate.setAbroadInstituteName(rs.getString("ABROAD_INSTITUTE_NAME"));
        candidate.setAbroadStream(rs.getString("ABROAD_STREAM"));
        candidate.setAbroadQualification(rs.getString("ABROAD_QUALIFICATION"));
        candidate.setAbroadYear(rs.getString("ABROAD_YEAR"));
        candidate.setDepartment(new IdDescRowMapper("D_").mapRow(rs, i));
        candidate.setCity(new CityRowMapper("CITY_", "CITY_S_").mapRow(rs, i));
        candidate.setFatherName1(rs.getString("FATHER_NAME_1"));
        candidate.setFatherOccupation(rs.getString("FATHER_OCCUPATION"));
        candidate.setMotherName(rs.getString("MOTHER_NAME"));
        candidate.setMotherOccupation(rs.getString("MOTHER_OCCUPATION"));
        candidate.setSiblingsCount(rs.getInt("SIBLINGS_COUNT"));
        candidate.setFamilyDetails(rs.getString("FAMILY_DETAILS"));
        return candidate;
    }
}
