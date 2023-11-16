/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.dao;

import cc.altius.hrApplication.model.DTO.CandidateDashboardDTO;

/**
 *
 * @author Akil Mahimwala
 */
public interface ReportDao {

    public CandidateDashboardDTO getCandidateDashboard(String startDate, String stopDate, String locationId, String processCode);
}
