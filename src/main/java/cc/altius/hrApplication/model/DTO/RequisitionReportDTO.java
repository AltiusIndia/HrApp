package cc.altius.hrApplication.model.DTO;

import java.sql.Date;

import cc.altius.hrApplication.model.IdDesc;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 * @createdOn 2017/12/24
 */
@Data
@NoArgsConstructor
public class RequisitionReportDTO {

    private int requisitionId;
    private String requisitionUniqueId;
    private Date createdDate;
    private IdDesc requisitionStatus;
    private IdDesc process;
    private IdDesc department;
    private IdDesc location;
    private IdDesc recruitmentLevel;
    private int hiringCount;
    private int hrBufferCount;
    private Date trainingStartDate;
    private Date hrClosingDate;
    private String hrComments;
    private int assignedToHr;
    private int interviewsTakenByHr;
    private int assignedToOps;
    private int interviewsTakenByOps;
    private int selectedByOps;
    private int achieved;

    public int getOverallCount() {
        return this.hiringCount + this.hrBufferCount;
    }

    public int getPending() {
        return this.hiringCount + this.hrBufferCount - this.achieved;
    }
}
