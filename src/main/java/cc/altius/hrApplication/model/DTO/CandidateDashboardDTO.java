/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model.DTO;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author Akil Mahimwala
 */
@Data
public class CandidateDashboardDTO implements Serializable {

    private final int candidatesAssignedToHr;
    private final int interviewsTakenByHr;
    private final int candidatesAssignedToOps;
    private final int interviewsTakenByOps;
    private final int finalSelected;

    public double getInterviewsTakenByHrPerc() {
        if (candidatesAssignedToHr != 0) {
            return (double) interviewsTakenByHr / (double) candidatesAssignedToHr;
        } else {
            return 0.0;
        }
    }

    public double getCandidatesAssignedToOpsPerc() {
        if (interviewsTakenByHr != 0) {
            return (double) candidatesAssignedToOps / (double) interviewsTakenByHr;
        } else {
            return 0.0;
        }
    }

    public double getInterviewsTakenByOpsPerc() {
        if (candidatesAssignedToOps != 0) {
            return (double) interviewsTakenByOps / (double) candidatesAssignedToOps;
        } else {
            return 0.0;
        }
    }

    public double getFinalSelectedPerc() {
        if (interviewsTakenByOps != 0) {
            return (double) finalSelected / (double) interviewsTakenByOps;
        } else {
            return 0.0;
        }
    }
}
