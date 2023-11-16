/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.framework;

/**import cc.altius.hrApplication.model.Department;
import cc.altius.hrApplication.model.DepartmentCategory;
import cc.altius.hrApplication.model.DifficultyLevel;
import cc.altius.hrApplication.model.Gender;
import cc.altius.hrApplication.model.MaritalStatus;
import cc.altius.hrApplication.model.QuestionCategory;
import cc.altius.hrApplication.model.QuestionType;
import cc.altius.hrApplication.model.Referral;
import cc.altius.hrApplication.model.RequisitionCommunicationPro;
import cc.altius.hrApplication.model.RequisitionEmpType;
import cc.altius.hrApplication.model.RequisitionFinalStatusForOffice;
import cc.altius.hrApplication.model.RequisitionGenderRatio;
import cc.altius.hrApplication.model.RequisitionLanguage;
import cc.altius.hrApplication.model.RequisitionProcessCategory;
import cc.altius.hrApplication.model.RequisitionQualification;
import cc.altius.hrApplication.model.RequisitionRecruitmentLevel;
import cc.altius.hrApplication.model.RequisitionRecrutmentType;
import cc.altius.hrApplication.model.RequisitionTyping;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
*/
/**
 *
 * @author Akil Mahimwala
 */
/**@Component
@Scope("application")
public class ApplicationSession {

    @Autowired
    private ApplicationLoadService applicationLoadService;
    private List<Gender> genderList = new LinkedList<Gender>();
    private List<Referral> referralList = new LinkedList<Referral>();
    private List<MaritalStatus> maritalStatusList = new LinkedList<MaritalStatus>();
    private List<QuestionType> questionTypeList = new LinkedList<QuestionType>();
    private List<DifficultyLevel> difficultyLevelList = new LinkedList<DifficultyLevel>();
    private final ListMultimap<Boolean, QuestionCategory> questionCategoryList = LinkedListMultimap.create();
    private List<Department> departmentList = new LinkedList<Department>();
    private List<DepartmentCategory> departmentCategoryList = new LinkedList<DepartmentCategory>();
    
    private List<RequisitionEmpType> requisitionEmployeeTypeList = new LinkedList<RequisitionEmpType>();
    private List<RequisitionRecrutmentType> requisitionRecrutmentListAll = new LinkedList<RequisitionRecrutmentType>();
    private List<RequisitionRecruitmentLevel> requisitionRecruitmentLevel = new LinkedList<RequisitionRecruitmentLevel>();
    private List<RequisitionLanguage> requisitionLanguagelist = new LinkedList<RequisitionLanguage>();
    private List<RequisitionTyping> requisitionTypinglist = new LinkedList<RequisitionTyping>();
    private List<RequisitionCommunicationPro> requisitionCommProList = new LinkedList<RequisitionCommunicationPro>();
    
    private List<RequisitionGenderRatio> requisitionGenderRatioList = new LinkedList<RequisitionGenderRatio>();
    private List<RequisitionQualification> requisitionQualificationList = new LinkedList<RequisitionQualification>();
    
    private List<RequisitionProcessCategory> requisitionProcessCategoryList = new LinkedList<RequisitionProcessCategory>();
    
    private List<RequisitionFinalStatusForOffice> requisitionFinalStatusListForOffice = new LinkedList<RequisitionFinalStatusForOffice>();

    public static ApplicationSession getCurrent() throws BeansException {
        return ApplicationContextProvider.getApplicationContext().getBean(ApplicationSession.class);
    }

    public void reloadAll() {
        reloadGenderList();
        reloadReferralList();
        reloadMaritalStatusList();
        reloadQuestionTypeList();
        reloadQuestionCategoryList();
        reloadDifficultyLevelList();
        reloadDepartmentList();
        reloadDepartmentCategoryList();
        
        reloadRequisitionEmpTypeList();
        reloadRequisitionRecrutmentTypeList();
        reloadRequisitionRecruitmentLevelList();
        reloadRequisitionLanguage();
        reloadRequisitionTyping();
        reloadRequisitioncommPro();
        reloadRequisitionGenderRatio();
        
        reloadRequisitionQualification();
        reloadRequisitionProcessCatgoryList();
        
        reloadRequisitionFinalStatusForOffice();
        
    }

    public void reloadGenderList() {
        this.genderList = null;
        this.genderList = applicationLoadService.getGenderList();
    }

    public List<Gender> getGenderList() {
        if (this.genderList != null && !this.genderList.isEmpty()) {
            return this.genderList;
        } else {
            this.genderList = applicationLoadService.getGenderList();
            return this.genderList;
        }
    }

    public Gender getGenderById(int genderId) {
        Gender gender;
        if (genderId <= 0) {
            return null;
        }
        gender = new Gender();
        gender.setGenderId(genderId);
        int idx = this.genderList.indexOf(gender);
        if (idx >= 0) {
            return this.genderList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadReferralList() {
        this.referralList = null;
        this.referralList = applicationLoadService.getReferralList();
    }

    public List<Referral> getReferralList() {
        if (this.referralList != null && !this.referralList.isEmpty()) {
            return this.referralList;
        } else {
            this.referralList = applicationLoadService.getReferralList();
            return this.referralList;
        }
    }

    public Referral getReferralById(int referralId) {
        Referral referral;
        if (referralId <= 0) {
            return null;
        }
        referral = new Referral();
        referral.setReferralId(referralId);
        int idx = this.referralList.indexOf(referral);
        if (idx >= 0) {
            return this.referralList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadMaritalStatusList() {
        this.maritalStatusList = null;
        this.maritalStatusList = applicationLoadService.getMaritalStatusList();
    }

    public List<MaritalStatus> getMaritalStatusList() {
        if (this.maritalStatusList != null && !this.maritalStatusList.isEmpty()) {
            return this.maritalStatusList;
        } else {
            this.maritalStatusList = applicationLoadService.getMaritalStatusList();
            return this.maritalStatusList;
        }
    }

    public MaritalStatus getMaritalStatusById(int maritalStatusId) {
        MaritalStatus maritalStatus;
        if (maritalStatusId <= 0) {
            return null;
        }
        maritalStatus = new MaritalStatus();
        maritalStatus.setMaritalStatusId(maritalStatusId);
        int idx = this.maritalStatusList.indexOf(maritalStatus);
        if (idx >= 0) {
            return this.maritalStatusList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadQuestionTypeList() {
        this.questionTypeList = null;
        this.questionTypeList = applicationLoadService.getQuestionTypeList();
    }

    public List<QuestionType> getQuestionTypeList() {
        if (this.questionTypeList != null && !this.questionTypeList.isEmpty()) {
            return this.questionTypeList;
        } else {
            this.questionTypeList = applicationLoadService.getQuestionTypeList();
            return this.questionTypeList;
        }
    }

    public QuestionType getQuestionTypeById(int questionTypeId) {
        QuestionType questionType;
        if (questionTypeId <= 0) {
            return null;
        }
        questionType = new QuestionType();
        questionType.setQuestionTypeId(questionTypeId);
        int idx = this.questionTypeList.indexOf(questionType);
        if (idx >= 0) {
            return this.questionTypeList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadDifficultyLevelList() {
        this.difficultyLevelList = null;
        this.difficultyLevelList = applicationLoadService.getDifficultyLevelList();
    }

    public List<DifficultyLevel> getDifficultyLevelList() {
        if (this.difficultyLevelList != null && !this.difficultyLevelList.isEmpty()) {
            return this.difficultyLevelList;
        } else {
            this.difficultyLevelList = applicationLoadService.getDifficultyLevelList();
            return this.difficultyLevelList;
        }
    }

    public DifficultyLevel getDifficultyLevelById(int difficultyLevelId) {
        DifficultyLevel difficultyLevel;
        if (difficultyLevelId <= 0) {
            return null;
        }
        difficultyLevel = new DifficultyLevel();
        difficultyLevel.setDifficultyLevelId(difficultyLevelId);
        int idx = this.difficultyLevelList.indexOf(difficultyLevel);
        if (idx >= 0) {
            return this.difficultyLevelList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadQuestionCategoryList() {
        setQuestionCategoryList(applicationLoadService.getQuestionCategoryList());
    }

    private void setQuestionCategoryList(List<QuestionCategory> questionCategoryList) {
        this.questionCategoryList.clear();
        for (QuestionCategory tp : questionCategoryList) {
            if (tp.isActive()) {
                this.questionCategoryList.put(Boolean.TRUE, tp);
                this.questionCategoryList.put(Boolean.FALSE, tp);
            } else {
                this.questionCategoryList.put(Boolean.FALSE, tp);
            }
        }
    }

    public List<QuestionCategory> getActiveQuestionCategoryList() {
        if (this.questionCategoryList.isEmpty()) {
            reloadQuestionCategoryList();
        }
        return this.questionCategoryList.get(Boolean.TRUE);
    }

    public List<QuestionCategory> getAllQuestionCategoryList() {
        if (this.questionCategoryList.isEmpty()) {
            reloadQuestionCategoryList();
        }
        return this.questionCategoryList.get(Boolean.FALSE);
    }

    public QuestionCategory getQuestionCategoryById(Integer questionCategoryId) {
        QuestionCategory questionCategory;
        if (questionCategoryId == null || questionCategoryId.equals(0)) {
            return null;
        }
        questionCategory = new QuestionCategory();
        questionCategory.setQuestionCategoryId(questionCategoryId);
        List<QuestionCategory> fullQuestionCategoryList = getAllQuestionCategoryList();
        int idx = fullQuestionCategoryList.indexOf(questionCategory);
        if (idx >= 0) {
            return fullQuestionCategoryList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadDepartmentList() {
        this.departmentList = null;
        this.departmentList = applicationLoadService.getDepartmentList();
    }

    public List<Department> getDepartmentList() {
        if (this.departmentList != null && !this.departmentList.isEmpty()) {
            return this.departmentList;
        } else {
            this.departmentList = applicationLoadService.getDepartmentList();
            return this.departmentList;
        }
    }

    public Department getDepartmentById(int departmentId) {
        Department department;
        if (departmentId <= 0) {
            return null;
        }
        department = new Department();
        department.setDepartmentId(departmentId);
        int idx = this.departmentList.indexOf(department);
        if (idx >= 0) {
            return this.departmentList.get(idx);
        } else {
            return null;
        }
    }

    public void reloadDepartmentCategoryList() {
        this.departmentCategoryList = null;
        this.departmentCategoryList = applicationLoadService.getDepartmentCategoryList();
    }

    public List<DepartmentCategory> getDepartmentCategoryList() {
        if (this.departmentCategoryList != null && !this.departmentCategoryList.isEmpty()) {
            return this.departmentCategoryList;
        } else {
            this.departmentCategoryList = applicationLoadService.getDepartmentCategoryList();
            return this.departmentCategoryList;
        }
    }

    public DepartmentCategory getDepartmentCategoryById(int departmentCategoryId) {
        DepartmentCategory departmentCategory;
        if (departmentCategoryId <= 0) {
            return null;
        }
        departmentCategory = new DepartmentCategory();
        departmentCategory.setDepartmentCategoryId(departmentCategoryId);
        int idx = this.departmentCategoryList.indexOf(departmentCategory);
        if (idx >= 0) {
            return this.departmentCategoryList.get(idx);
        } else {
            return null;
        }
    }
    
    
    public void reloadRequisitionEmpTypeList() {
        this.requisitionEmployeeTypeList = null;
        this.requisitionEmployeeTypeList = applicationLoadService.getRequisitionEmpTypes();
    }

    public List<RequisitionEmpType> getRequisitionEmplType() {
        if (this.requisitionEmployeeTypeList != null && !this.requisitionEmployeeTypeList.isEmpty()) {
            return this.requisitionEmployeeTypeList;
        } else {
            this.requisitionEmployeeTypeList = applicationLoadService.getRequisitionEmpTypes();
            return this.requisitionEmployeeTypeList;
        }
    }
    
    
     public void reloadRequisitionRecrutmentTypeList() {
        this.requisitionRecrutmentListAll = null;
        this.requisitionRecrutmentListAll = applicationLoadService.getRequisitionRecrutmentType();
    }

    public List<RequisitionRecrutmentType> getRequisitionRecrutmentType() {
        if (this.requisitionRecrutmentListAll != null && !this.requisitionEmployeeTypeList.isEmpty()) {
            return this.requisitionRecrutmentListAll;
        } else {
            this.requisitionRecrutmentListAll = applicationLoadService.getRequisitionRecrutmentType();
            return this.requisitionRecrutmentListAll;
        }
    }
    
    
    public void reloadRequisitionRecruitmentLevelList() {
        this.requisitionRecruitmentLevel = null;
        this.requisitionRecruitmentLevel = applicationLoadService.getRequisitionRecruitmentLevel();
    }

    public List<RequisitionRecruitmentLevel> getRequisitionRecruitmentLevel() {
        if (this.requisitionRecruitmentLevel != null && !this.requisitionEmployeeTypeList.isEmpty()) {
            return this.requisitionRecruitmentLevel;
        } else {
            this.requisitionRecruitmentLevel = applicationLoadService.getRequisitionRecruitmentLevel();
            return this.requisitionRecruitmentLevel;
        }
    }
    
    public void reloadRequisitionLanguage() {
        this.requisitionLanguagelist = null;
        this.requisitionLanguagelist = applicationLoadService.getRequistionLanguageList();
    }

    public List<RequisitionLanguage> getRequisitionLangauageFor() {
        if (this.requisitionLanguagelist != null && !this.requisitionEmployeeTypeList.isEmpty()) {
            return this.requisitionLanguagelist;
        } else {
            this.requisitionLanguagelist = applicationLoadService.getRequistionLanguageList();
            return this.requisitionLanguagelist;
        }
    }
    
    
    public void reloadRequisitionTyping() {
        this.requisitionTypinglist = null;
        this.requisitionTypinglist = applicationLoadService.getRequstionTypingSpeed();
    }

    public List<RequisitionTyping> getRequisitionTyping() {
        if (this.requisitionTypinglist != null && !this.requisitionTypinglist.isEmpty()) {
            return this.requisitionTypinglist;
        } else {
            this.requisitionTypinglist = applicationLoadService.getRequstionTypingSpeed();
            return this.requisitionTypinglist;
        }
    }
    
    
    public void reloadRequisitioncommPro() {
        this.requisitionCommProList = null;
        this.requisitionCommProList = applicationLoadService.getRequsitionCommProList();
    }

    public List<RequisitionCommunicationPro> getRequisitionCommPro() {
        if (this.requisitionCommProList != null && !this.requisitionCommProList.isEmpty()) {
            return this.requisitionCommProList;
        } else {
            this.requisitionCommProList = applicationLoadService.getRequsitionCommProList();
            return this.requisitionCommProList;
        }
    }
    
    public void reloadRequisitionGenderRatio() {
        this.requisitionGenderRatioList = null;
        this.requisitionGenderRatioList = applicationLoadService.getRequisitionGenderRatios();
    }

    public List<RequisitionGenderRatio> getRequisitionGenderRatio() {
        if (this.requisitionGenderRatioList != null && !this.requisitionGenderRatioList.isEmpty()) {
            return this.requisitionGenderRatioList;
        } else {
            this.requisitionGenderRatioList = applicationLoadService.getRequisitionGenderRatios();
            return this.requisitionGenderRatioList;
        }
    }
    
    public void reloadRequisitionQualification() {
        this.requisitionQualificationList = null;
        this.requisitionQualificationList = applicationLoadService.getRequsitionQualiList();
    }

    public List<RequisitionQualification> getRequisitionQualification() {
        if (this.requisitionQualificationList != null && !this.requisitionQualificationList.isEmpty()) {
            return this.requisitionQualificationList;
        } else {
            this.requisitionQualificationList = applicationLoadService.getRequsitionQualiList();
            return this.requisitionQualificationList;
        }
    }
    
    
    public void reloadRequisitionProcessCatgoryList() {
        this.requisitionProcessCategoryList = null;
        this.requisitionProcessCategoryList = applicationLoadService.getRequsitionProcessCategoryActiveList();
    }

    public List<RequisitionProcessCategory> getRequisitionProcessCatgory() {
        if (this.requisitionProcessCategoryList != null && !this.requisitionProcessCategoryList.isEmpty()) {
            return this.requisitionProcessCategoryList;
        } else {
            this.requisitionProcessCategoryList = applicationLoadService.getRequsitionProcessCategoryActiveList();
            return this.requisitionProcessCategoryList;
        }
    }
    
    public void reloadRequisitionFinalStatusForOffice() {
        this.requisitionFinalStatusListForOffice = null;
        this.requisitionFinalStatusListForOffice = applicationLoadService.getRequisitionFinalStatusActive();
    }

    public List<RequisitionFinalStatusForOffice> getRequisitionFinalStatusForOffice() {
        if (this.requisitionFinalStatusListForOffice != null && !this.requisitionFinalStatusListForOffice.isEmpty()) {
            return this.requisitionFinalStatusListForOffice;
        } else {
            this.requisitionFinalStatusListForOffice = applicationLoadService.getRequisitionFinalStatusActive();
            return this.requisitionFinalStatusListForOffice;
        }
    }
   
    
}*/
