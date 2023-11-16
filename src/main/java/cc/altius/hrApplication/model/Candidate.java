package cc.altius.hrApplication.model;

/**
 *
 * @author Akil Mahimwala
 * @createdOn 2017/12/24
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Candidate implements Serializable {

    @EqualsAndHashCode.Include
    private int candidateId;
    private String interviewTrackerNo;
    private IdDesc gender;
    private IdDesc maritalStatus;
    private IdDesc referral;
    private String firstName;
    private String languageProficiency;
    private IdDesc location;
    private String work;
    private String lastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String bloodGroup;
    private Date dob;
    private String aadharNumber;
    private String panNumber;
    private String mobileNumber;
    private String address1;
    private String address2;
    private City city;
    private String zip;
    private String referralFrom;
    private String pfAccount;
    private String lastCompanyName;
    private String workDuration;
    private Date period;
    private Long salary;
    private Date createdDate;
    private Boolean experience;
    private String computerKnowledge;
    private String international;
    private String domestic;
    private String other;
    private Date lastModifiedDate;
    private List<CandidateDocument> candidateDocument;
    private List<CandidateQualification> candidateQualifications;
    public MultipartFile file;
    private List<MultipartFile> files;
    private IdDesc wantsToApplyFor;
    private String prevDesignation;
    private Date dateOfJoining;
    private Date dateOfLeaving;
    private IdDesc department;
    private CandidateStatus candidateStatus;
    private String fatherName1;
    private String fatherOccupation;
    private String motherName;
    private String motherOccupation;
    private int siblingsCount;
    private String familyDetails;

    private String abroadQualifictionStatus;
    private String abroadQualification;
    private String abroadStream;
    private String abroadCountry;
    private String abroadInstituteName;
    private String abroadCity;
    private String abroadYear;
    private String newLocation;

    //vacacination infomation
    private String vaccinationInformation;
    private String percentage;

    public Candidate(int candidateId) {
        this.candidateId = candidateId;
    }

}
