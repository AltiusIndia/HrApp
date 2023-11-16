/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.dao;

import java.util.List;

import cc.altius.hrApplication.model.City;
import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.IdDescActive;
import cc.altius.hrApplication.model.Process;
import cc.altius.hrApplication.model.SimpleUserWithId;

/**
 *
 * @author Akil Mahimwala
 */
public interface MasterDao {

    List<IdDesc> getSimpleLocationList(boolean active);

    List<IdDesc> getSimpleDepartmentList(boolean active);

    List<IdDesc> getSimpleDesignationList(boolean active);

    List<IdDescActive> getDepartmentList(boolean active);

    public int addDepartment(IdDescActive department);

    public int editDepartment(IdDescActive department);

    public IdDescActive getDepartmentById(int id);

    List<Process> getProcessList(boolean active);

    public int addProcess(Process process);

    public int editProcess(Process process);

    public Process getProcessById(int id);

    List<IdDescActive> getDesignationList(boolean active);

    public int addDesignation(IdDescActive department);

    public int editDesignation(IdDescActive department);

    public IdDescActive getDesignationById(int id);

    List<SimpleUserWithId> getBuManagerList();

    List<IdDesc> getProcessCategoryList(boolean active);

    List<IdDesc> getEmploymentTypeList(boolean active);

    List<IdDesc> getRecruitmentTypeList(boolean active);

    List<IdDesc> getRecruitmentLevelList(boolean active);

    List<IdDesc> getLanguageList(boolean active);

    List<IdDesc> getMinQualificationList(boolean active);

    List<IdDesc> getTypingSpeedList(boolean active);

    List<IdDesc> getCommunicationProficiencyList(boolean active);

    List<IdDesc> getGenderRatioList(boolean active);

    List<IdDesc> getWeekOffPatternList(boolean active);

    List<IdDesc> getWeekOffCountList(boolean active);

    List<IdDesc> getShiftDurationList(boolean active);
    
    List<IdDesc> getEducationStreamList(boolean active);

    List<City> getCityList(boolean active);

    List<IdDesc> getStateList(boolean active);

    List<IdDesc> getGenderList(boolean active);

    List<IdDesc> getMaritalStatusList(boolean active);

    List<IdDesc> getQualificationList(boolean active);

    List<IdDesc> getReferralList(boolean active);

    List<IdDesc> getRequisitionFinalStatusList(boolean active);

    List<IdDesc> getRequisitionStatusList(boolean active);

    List<IdDesc> getWorkList(boolean active);
    
//    List<IdDesc> getSalaryBandList(boolean active);
}
