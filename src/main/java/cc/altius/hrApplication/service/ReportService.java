/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.service;

import cc.altius.hrApplication.model.DTO.CandidateDashboardDTO;

/**
 *
 * @author Akil Mahimwala
 */
public interface ReportService {

    public CandidateDashboardDTO getCandidateDashboard(String startDate, String stopDate, String locationId, String processCode);
}
