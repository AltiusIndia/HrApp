/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.web.controller;

import cc.altius.hrApplication.model.IdDesc;
import cc.altius.hrApplication.model.IdDescCode;
import cc.altius.hrApplication.model.Requisition;
import cc.altius.hrApplication.model.SimpleUser;
import cc.altius.hrApplication.service.MasterService;
import cc.altius.hrApplication.service.RequisitionService;
import cc.altius.utils.DateUtils;
import cc.altius.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Akil Mahimwala
 */
@Controller
@RequestMapping("/requisition")
public class RequisitionController {

    @Autowired
    private RequisitionService requisitionService;
    @Autowired
    private MasterService masterService;

    @RequestMapping(value = "/requisitionAdd.htm", method = RequestMethod.GET)
    public String addRequisition(ModelMap model) throws ParseException {
        Requisition r = new Requisition();
        r.setLocation(new IdDesc("2", ""));
        r.setDepartment(new IdDesc("1", ""));
        r.setProcess(new IdDescCode("192", "", ""));
        r.setBuManager(new SimpleUser("81", ""));
        r.setProcessCategory(new IdDesc("2", ""));
        r.setEmploymentType(new IdDesc("1", ""));
        r.setRecruitmentType(new IdDesc("1", ""));
        r.setRecruitmentLevel(new IdDesc("1", ""));
        r.setHiringCount(5);
        r.setLanguageIds(new String[]{"1", "2", "5"});
        r.setTrainingStartDate("2023-11-30");
        r.setSalaryBand("15k to 18k");
        r.setMinQualification(new IdDesc("1", ""));
        r.setTypingSpeed(new IdDesc("1", ""));
        r.setCommunicationProficiency(new IdDesc("4", ""));
        r.setGenderRatio(new IdDesc("6", ""));
        r.setComments("Testing");
        r.setOperationsWindow("10am to 6pm");
        r.setWeekOffPattern(new IdDesc("1", ""));
        r.setWeekOffCount(new IdDesc("1", ""));
        r.setShiftDuration(new IdDesc("2", ""));
        model.addAttribute("requisition", r);

        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(true));
        model.addAttribute("buManagerList", this.masterService.getBuManagerList());
        model.addAttribute("processList", this.masterService.getProcessList(true));
        model.addAttribute("employmentTypeList", this.masterService.getEmploymentTypeList(true));
        model.addAttribute("processCategoryList", this.masterService.getProcessCategoryList(true));
        model.addAttribute("recruitmentTypeList", this.masterService.getRecruitmentTypeList(true));
        model.addAttribute("recruitmentLevelList", this.masterService.getRecruitmentLevelList(true));
        model.addAttribute("minQualificationList", this.masterService.getMinQualificationList(true));
        model.addAttribute("typingSpeedList", this.masterService.getTypingSpeedList(true));
        model.addAttribute("communicationProffeciencyList", this.masterService.getCommunicationProficiencyList(true));
        model.addAttribute("genderRatioList", this.masterService.getGenderRatioList(true));
        model.addAttribute("weekOffPatternList", this.masterService.getWeekOffPatternList(true));
        model.addAttribute("weekOffCountList", this.masterService.getWeekOffCountList(true));
        model.addAttribute("shiftDurationList", this.masterService.getShiftDurationList(true));
        model.addAttribute("languageList", this.masterService.getLanguageList(true));
        return "requisition/requisitionAdd";
    }

    @RequestMapping(value = "/requisitionAdd.htm", method = RequestMethod.POST)
    public String addRequisitionPost(@ModelAttribute("requisition") Requisition requisition, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            requisition = null;
            return "redirect:../requisition/requisitionList.htm?msg=msg.actionCancelled";
        }
        if (this.requisitionService.addRequisition(requisition) != 0) {
            return "redirect:/requisition/requisitionList.htm?msg=" + URLEncoder.encode("Requisition added successfully", "UTF-8");
        } else {
            errors.rejectValue("location.id", "msg.requisitionError");
            return "redirect:/requisition/requisitionAdd.htm?msg=" + URLEncoder.encode("Failed to add Requisition", "UTF-8");
        }
    }

    @RequestMapping(value = "/requisitionList.htm")
    public String listRequisition(ModelMap model, HttpServletRequest request, HttpSession session) {
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", SessionUtils.fetchData("startDate", request, session, DateUtils.getStartOfMonthString(DateUtils.YMD)));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", SessionUtils.fetchData("stopDate", request, session, DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD)));
        String statusId = ServletRequestUtils.getStringParameter(request, "statusId", SessionUtils.fetchData("statusId", request, session, "1"));
        String locationId = ServletRequestUtils.getStringParameter(request, "locationId", SessionUtils.fetchData("locationId", request, session, "-1"));
        if (statusId.equals("0")) {
            statusId = "-1";
            SessionUtils.putData("statusId", session, statusId);
        }
        model.addAttribute("requisitionList", this.requisitionService.getRequisitionList(locationId, statusId, startDate, stopDate));
        model.addAttribute("startDate", startDate);
        model.addAttribute("stopDate", stopDate);
        model.addAttribute("locationId", locationId);
        model.addAttribute("statusId", statusId);
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("statusList", this.masterService.getRequisitionStatusList(true));
        return "requisition/requisitionList";
    }

    @RequestMapping(value = "/requisitionEditShow.htm", method = RequestMethod.POST)
    public String showEditRequisitionsForm(@RequestParam(value = "requisitionId", required = true) int requisitionId, ModelMap model) {
        Requisition r = this.requisitionService.getRequisitionById(requisitionId);
        model.addAttribute("requisition", r);
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(true));
        model.addAttribute("buManagerList", this.masterService.getBuManagerList());
        model.addAttribute("processList", this.masterService.getProcessList(true));
        model.addAttribute("employmentTypeList", this.masterService.getEmploymentTypeList(true));
        model.addAttribute("processCategoryList", this.masterService.getProcessCategoryList(true));
        model.addAttribute("recruitmentTypeList", this.masterService.getRecruitmentTypeList(true));
        model.addAttribute("recruitmentLevelList", this.masterService.getRecruitmentLevelList(true));
        model.addAttribute("minQualificationList", this.masterService.getMinQualificationList(true));
        model.addAttribute("typingSpeedList", this.masterService.getTypingSpeedList(true));
        model.addAttribute("communicationProffeciencyList", this.masterService.getCommunicationProficiencyList(true));
        model.addAttribute("genderRatioList", this.masterService.getGenderRatioList(true));
        model.addAttribute("weekOffPatternList", this.masterService.getWeekOffPatternList(true));
        model.addAttribute("weekOffCountList", this.masterService.getWeekOffCountList(true));
        model.addAttribute("shiftDurationList", this.masterService.getShiftDurationList(true));
        model.addAttribute("languageList", this.masterService.getLanguageList(true));
        model.addAttribute("requisitionStatusList", this.masterService.getRequisitionStatusList(true));
        return "requisition/requisitionEdit";
    }

    @RequestMapping(value = "/requisitionEdit.htm", method = RequestMethod.POST)
    public String onEditRequisitionSubmit(@ModelAttribute("requisition") Requisition requisition, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            requisition = null;
            return "redirect:../requisition/requisitionList.htm?msg=msg.actionCancelled";
        }
        this.requisitionService.editRequisition(requisition);
        return "redirect:../requisition/requisitionList.htm?msg=msg.requisitionUpdatedSuccessfully";
    }

    @RequestMapping(value = "/requisitionView.htm")
    public String showEditRequisitionView(@RequestParam(value = "requisitionId", required = true) int requisitionId, ModelMap model) {
        Requisition r = this.requisitionService.getRequisitionById(requisitionId);
        model.addAttribute("requisition", r);
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(true));
        model.addAttribute("buManagerList", this.masterService.getBuManagerList());
        model.addAttribute("processList", this.masterService.getProcessList(true));
        model.addAttribute("employmentTypeList", this.masterService.getEmploymentTypeList(true));
        model.addAttribute("processCategoryList", this.masterService.getProcessCategoryList(true));
        model.addAttribute("recruitmentTypeList", this.masterService.getRecruitmentTypeList(true));
        model.addAttribute("recruitmentLevelList", this.masterService.getRecruitmentLevelList(true));
        model.addAttribute("minQualificationList", this.masterService.getMinQualificationList(true));
        model.addAttribute("typingSpeedList", this.masterService.getTypingSpeedList(true));
        model.addAttribute("communicationProffeciencyList", this.masterService.getCommunicationProficiencyList(true));
        model.addAttribute("genderRatioList", this.masterService.getGenderRatioList(true));
        model.addAttribute("weekOffPatternList", this.masterService.getWeekOffPatternList(true));
        model.addAttribute("weekOffCountList", this.masterService.getWeekOffCountList(true));
        model.addAttribute("shiftDurationList", this.masterService.getShiftDurationList(true));
        model.addAttribute("languageList", this.masterService.getLanguageList(true));
        model.addAttribute("requisitionStatusList", this.masterService.getRequisitionStatusList(true));
        return "requisition/requisitionView";
    }

}
