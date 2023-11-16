/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.framework;

/**import cc.altius.hrApplication.dao.ApplicationLoadDao;
import cc.altius.hrApplication.dao.DepartmentCategoryDao;
import cc.altius.hrApplication.dao.DepartmentDao;
import cc.altius.hrApplication.dao.DifficultyLevelDao;
import cc.altius.hrApplication.dao.GenderDao;
import cc.altius.hrApplication.dao.MaritalStatusDao;
import cc.altius.hrApplication.dao.QuestionCategoryDao;
import cc.altius.hrApplication.dao.QuestionTypeDao;
import cc.altius.hrApplication.dao.ReferralDao;
import cc.altius.hrApplication.dao.RequisitionDao;
import cc.altius.hrApplication.model.Department;
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
import cc.altius.hrApplication.model.Zip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
*/
/**
 *
 * @author Akil Mahimwala
 */
/**@Service
public class ApplicationLoadService {

    @Autowired
    ApplicationLoadDao applicationLoadDao;
    @Autowired
    GenderDao genderDao;
    @Autowired
    ReferralDao referralDao;
    @Autowired
    MaritalStatusDao maritalStatusDao;
    @Autowired
    DifficultyLevelDao difficultyLevel;
    @Autowired
    QuestionCategoryDao questionCategory;
    @Autowired
    QuestionTypeDao questionTypeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    DepartmentCategoryDao departmentCategoryDao;
    @Autowired
    RequisitionDao requisitionDao;
    
    
    @Transactional
    public void fetchData() {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
    }

    public List<Gender> getGenderList() {
        return this.genderDao.getGenderList();
    }

    public List<Referral> getReferralList() {
        return this.referralDao.getReferralList();
    }

    public List<MaritalStatus> getMaritalStatusList() {
        return this.maritalStatusDao.getMaritalStatusList();
    }

    public List<DifficultyLevel> getDifficultyLevelList() {
        return this.difficultyLevel.getDifficultyLevelList();
    }

    public List<QuestionCategory> getQuestionCategoryList() {
        return this.questionCategory.getQuestionCategoryList();
    }

    public List<QuestionType> getQuestionTypeList() {
        return this.questionTypeDao.getQuestionTypeList();
    }

    public Zip getZipByZipCode(String zipCode) {
        return this.applicationLoadDao.getZipByZipCode(zipCode);
    }

    List<Department> getDepartmentList() {
        return this.departmentDao.getDepartmentList();
    }

    List<DepartmentCategory> getDepartmentCategoryList() {
        return this.departmentCategoryDao.getDepartmentCategoryList();
    }
    
    List<RequisitionEmpType> getRequisitionEmpTypes(){
        return this.requisitionDao.getRequisitionEmpType();
    }
    
    List<RequisitionRecrutmentType> getRequisitionRecrutmentType()
    {
     return this.requisitionDao.getRequisitionRecrutmentTypeList();
    }
    
    List<RequisitionRecruitmentLevel> getRequisitionRecruitmentLevel(){
        return this.requisitionDao.getRequisitionRecruitmentLevel();
    }
    
    List<RequisitionLanguage> getRequistionLanguageList(){
     return this.requisitionDao.getRequistionLanguageAll();
    }
    
    List<RequisitionTyping> getRequstionTypingSpeed(){
        return this.requisitionDao.getTypingSpeed();
    }
    
    List<RequisitionCommunicationPro> getRequsitionCommProList(){
        return this.requisitionDao.getCommunicationPro();
    }
    
    
    List<RequisitionGenderRatio> getRequisitionGenderRatios(){
        return this.requisitionDao.getGenderRation();
    }
    
    List<RequisitionQualification> getRequsitionQualiList(){
        return this.requisitionDao.getQualification();
    }
    
    List<RequisitionProcessCategory> getRequsitionProcessCategoryActiveList(){
        return this.requisitionDao.getRequsitionProcessCategory();
    }
    
    List<RequisitionFinalStatusForOffice> getRequisitionFinalStatusActive(){
        return this.requisitionDao.getFinalStatusListForOffice();
    }
}*/
