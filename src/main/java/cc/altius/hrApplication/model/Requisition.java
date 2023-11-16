/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Requisition implements Serializable {

    @EqualsAndHashCode.Include
    private int requisitionId;
    private String requisitionUniqueId;
    private IdDesc location;
    private IdDesc department;
    private SimpleUser buManager;
    private IdDescCode process;
    private IdDesc processCategory;
    private IdDesc employmentType;
    private IdDesc recruitmentType;
    private IdDesc recruitmentLevel;
    private int hiringCount;
    private List<IdDesc> languageList = new LinkedList<>();
    private String trainingStartDate;
    private String salaryBand;
    private IdDesc minQualification;
    private IdDesc typingSpeed;
    private IdDesc communicationProficiency;
    private IdDesc genderRatio;
    private String comments;
    private String operationsWindow;
    private IdDesc weekOffPattern;
    private IdDesc weekOffCount;
    private IdDesc shiftDuration;
    private SimpleUser createdBy;
    private Date createdDate;
    private SimpleUser lastModifiedBy;
    private Date lastModifiedDate;
    private IdDesc status;
    private Integer hrBufferCount;
    private String hrComments;
    private String hrClosingDate;

    public Requisition(int requisitionId) {
        this.requisitionId = requisitionId;
        this.status = new IdDesc("1", null); // Status open
    }

    public Requisition(int requisitionId, String requisitionUniqueId) {
        this.requisitionId = requisitionId;
        this.requisitionUniqueId = requisitionUniqueId;
        this.status = new IdDesc("1", null); // Status open
    }

    public String[] getLanguageIds() {
        return this.languageList.stream().map(IdDesc::getId).collect(Collectors.toList()).toArray(new String[0]);
    }

    public void setLanguageIds(String[] languageIds) {
        this.languageList.clear();
        Arrays.asList(languageIds).forEach(lId -> this.languageList.add(new IdDesc(lId, null)));
    }
    
    public String getLanguageIdsString() {
        return this.languageList.stream().map(IdDesc::getId).collect(Collectors.joining(","));
    }

}
