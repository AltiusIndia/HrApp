/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.ReportDao;
import cc.altius.hrApplication.model.DTO.CandidateDashboardDTO;
import cc.altius.hrApplication.service.ReportService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public CandidateDashboardDTO getCandidateDashboard(String startDate, String stopDate, String locationId, String processCode) {
        return this.reportDao.getCandidateDashboard(startDate, stopDate, locationId, processCode);
    }

}
