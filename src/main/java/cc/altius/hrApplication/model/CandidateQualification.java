package cc.altius.hrApplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 * @createdOn 2017/12/24
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CandidateQualification {

    @EqualsAndHashCode.Include
    private int candidateEducationId;
    private Candidate candidate;
    private IdDesc qualification;
    private String year;
    private IdDesc stream;
    private String instituteName;
    private String otherQualification;
    private IdDesc state;
    private City cityId;

}
