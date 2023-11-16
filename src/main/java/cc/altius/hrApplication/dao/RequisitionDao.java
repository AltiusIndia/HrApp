/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.dao;

import java.util.List;

import cc.altius.hrApplication.model.Requisition;
import cc.altius.hrApplication.model.DTO.RequisitionReportDTO;

/**
 *
 * @author Akil Mahimwala
 */
public interface RequisitionDao {

    public List<RequisitionReportDTO> getRequsitionDashboardReport(String statusId, String locationId);
    
    public List<Requisition> getRequisitionList(String locationId, String statusId, String startDate, String stopDate);

    public int addRequisition(Requisition requisition);

    public int editRequisition(Requisition requisition);

    public Requisition getRequisitionById(int requisitionId);
}
