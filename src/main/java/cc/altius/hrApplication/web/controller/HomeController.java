/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cc.altius.hrApplication.model.Candidate;
import cc.altius.hrApplication.service.CandidateService;
import cc.altius.hrApplication.service.MasterService;
import cc.altius.hrApplication.service.ReportService;
import cc.altius.hrApplication.service.RequisitionService;
import cc.altius.utils.DateUtils;
import cc.altius.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Akil Mahimwala
 */
@Controller
public class HomeController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private RequisitionService requisitionService;
    @Autowired
    private MasterService masterService;
    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/home/login.htm")
    public String getLoginPage() {
        return "home/login";
    }

    @RequestMapping("/home/index.htm")
    public String showHome(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", SessionUtils.fetchData("startDate", request, session, DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD)));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", SessionUtils.fetchData("stopDate", request, session, DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD)));
        int searchNo = ServletRequestUtils.getIntParameter(request, "searchNo", 0);
        String searchText = ServletRequestUtils.getStringParameter(request, "searchText", null);
        String locationId = ServletRequestUtils.getStringParameter(request, "locationId", SessionUtils.fetchData("locationId", request, session, "-1"));
        String statusId = ServletRequestUtils.getStringParameter(request, "statusId", SessionUtils.fetchData("statusId", request, session, "1"));
        model.addAttribute("startDate", startDate);
        model.addAttribute("stopDate", stopDate);
        model.addAttribute("searchText", searchText);
        model.addAttribute("searchNo", searchNo);
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("statusId", statusId);
        model.addAttribute("locationId", locationId);
        model.addAttribute("candidateDashboard", this.reportService.getCandidateDashboard(startDate, stopDate, locationId, ""));
        model.addAttribute("requsitionDashboardReportList", this.requisitionService.getRequsitionDashboardReport(statusId, locationId));
        return "home/index";
    }

    @RequestMapping("/home/searchCandidateNew.htm")
    public String showSearchCandidate() {
        return "home/searchCandidate";
    }

    @RequestMapping("/home/searchCandidate.htm")
    public String searchCandidate(@RequestParam(value = "searchText", required = true, defaultValue = "") String searchText, HttpSession session, ModelMap map, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        int searchNo = ServletRequestUtils.getIntParameter(request, "searchNo", 0);
        List<Candidate> lst = null;
        if (searchText.isEmpty()) {
            map.addAttribute("candidateList1", lst);
            map.addAttribute("searchText", searchText);
            map.addAttribute("searchNo", searchNo);
        } else {
            lst = this.candidateService.searchForCandidates(searchText, searchNo);
            if (lst.isEmpty()) {
                map.addAttribute("msg", "  Candidate Not Found");
            }
            map.addAttribute("candidateList1", lst);
            map.addAttribute("searchText", searchText);
            map.addAttribute("searchNo", searchNo);
        }
        return "redirect:../home/searchCandidate.htm?msg=" + URLEncoder.encode("Data is not availble", "UTF-8");
    }

}
