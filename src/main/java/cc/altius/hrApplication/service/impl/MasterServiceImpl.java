/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.MasterDao;
import cc.altius.hrApplication.model.City;
import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.IdDescActive;
import cc.altius.hrApplication.model.Process;
import cc.altius.hrApplication.model.SimpleUserWithId;
import cc.altius.hrApplication.service.MasterService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterDao masterDao;

    @Override
    public List<IdDesc> getSimpleLocationList(boolean active) {
        return this.masterDao.getSimpleLocationList(active);
    }

    @Override
    public List<IdDesc> getSimpleDepartmentList(boolean active) {
        return this.masterDao.getSimpleDepartmentList(active);
    }

    @Override
    public List<IdDesc> getSimpleDesignationList(boolean active) {
        return this.masterDao.getSimpleDesignationList(active);
    }

    @Override
    public List<IdDescActive> getDepartmentList(boolean active) {
        return this.masterDao.getDepartmentList(active);
    }

    @Override
    public int addDepartment(IdDescActive department) {
        return this.masterDao.addDepartment(department);
    }

    @Override
    public int editDepartment(IdDescActive department) {
        return this.masterDao.editDepartment(department);
    }

    @Override
    public IdDescActive getDepartmentById(int id) {
        return this.masterDao.getDepartmentById(id);
    }

    @Override
    public List<Process> getProcessList(boolean active) {
        return this.masterDao.getProcessList(active);
    }

    @Override
    public int addProcess(Process process) {
        return this.masterDao.addProcess(process);
    }

    @Override
    public int editProcess(Process process) {
        return this.masterDao.editProcess(process);
    }

    @Override
    public Process getProcessById(int id) {
        return this.masterDao.getProcessById(id);
    }

    @Override
    public List<IdDescActive> getDesignationList(boolean active) {
        return this.masterDao.getDesignationList(active);
    }

    @Override
    public int addDesignation(IdDescActive designation) {
        return this.masterDao.addDesignation(designation);
    }

    @Override
    public int editDesignation(IdDescActive designation) {
        return this.masterDao.editDesignation(designation);
    }

    @Override
    public IdDescActive getDesignationById(int id) {
        return this.masterDao.getDesignationById(id);
    }

    @Override
    public List<SimpleUserWithId> getBuManagerList() {
        return this.masterDao.getBuManagerList();
    }

    @Override
    public List<IdDesc> getProcessCategoryList(boolean active) {
        return this.masterDao.getProcessCategoryList(active);
    }

    @Override
    public List<IdDesc> getEmploymentTypeList(boolean active) {
        return this.masterDao.getEmploymentTypeList(active);
    }

    @Override
    public List<IdDesc> getRecruitmentTypeList(boolean active) {
        return this.masterDao.getRecruitmentTypeList(active);
    }

    @Override
    public List<IdDesc> getRecruitmentLevelList(boolean active) {
        return this.masterDao.getRecruitmentLevelList(active);
    }

    @Override
    public List<IdDesc> getLanguageList(boolean active) {
        return this.masterDao.getLanguageList(active);
    }

    @Override
    public List<IdDesc> getMinQualificationList(boolean active) {
        return this.masterDao.getMinQualificationList(active);
    }

    @Override
    public List<IdDesc> getTypingSpeedList(boolean active) {
        return this.masterDao.getTypingSpeedList(active);
    }

    @Override
    public List<IdDesc> getCommunicationProficiencyList(boolean active) {
        return this.masterDao.getCommunicationProficiencyList(active);
    }

    @Override
    public List<IdDesc> getGenderRatioList(boolean active) {
        return this.masterDao.getGenderRatioList(active);
    }

    @Override
    public List<IdDesc> getWeekOffPatternList(boolean active) {
        return this.masterDao.getWeekOffPatternList(active);
    }

    @Override
    public List<IdDesc> getWeekOffCountList(boolean active) {
        return this.masterDao.getWeekOffCountList(active);
    }

    @Override
    public List<IdDesc> getShiftDurationList(boolean active) {
        return this.masterDao.getShiftDurationList(active);
    }

//    @Override
//    public List<IdDesc> getSalaryBandList(boolean active) {
//        return this.masterDao.getSalaryBandList(active);
//    }
    @Override
    public List<IdDesc> getEducationStreamList(boolean active) {
        return this.masterDao.getEducationStreamList(active);
    }

    @Override
    public List<City> getCityList(boolean active) {
        return this.masterDao.getCityList(active);
    }

    @Override
    public List<IdDesc> getStateList(boolean active) {
        return this.masterDao.getStateList(active);
    }

    @Override
    public List<IdDesc> getGenderList(boolean active) {
        return this.masterDao.getGenderList(active);
    }

    @Override
    public List<IdDesc> getMaritalStatusList(boolean active) {
        return this.masterDao.getMaritalStatusList(active);
    }

    @Override
    public List<IdDesc> getQualificationList(boolean active) {
        return this.masterDao.getQualificationList(active);
    }

    @Override
    public List<IdDesc> getReferralList(boolean active) {
        return this.masterDao.getReferralList(active);
    }

    @Override
    public List<IdDesc> getRequisitionFinalStatusList(boolean active) {
        return this.masterDao.getRequisitionFinalStatusList(active);
    }

    @Override
    public List<IdDesc> getRequisitionStatusList(boolean active) {
        return this.masterDao.getRequisitionStatusList(active);
    }

    @Override
    public List<IdDesc> getWorkList(boolean active) {
        return this.masterDao.getWorkList(active);
    }

}
