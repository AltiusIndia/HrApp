/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CandidateStatus implements Serializable {

    @EqualsAndHashCode.Include
    private int candidateStatusId;
    private String candidateStatusDesc;
    private boolean active;
    private boolean forHr;
    private boolean forOperations;
    private boolean forHrTest;
    private boolean forHrSalary;
}
