/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.RequisitionDao;
import cc.altius.hrApplication.model.Requisition;
import cc.altius.hrApplication.model.DTO.RequisitionReportDTO;
import cc.altius.hrApplication.service.RequisitionService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class RequisitionServiceImpl implements RequisitionService {

    @Autowired
    private RequisitionDao requisitionDao;

    @Override
    public List<RequisitionReportDTO> getRequsitionDashboardReport(String statusId, String locationId) {
        return this.requisitionDao.getRequsitionDashboardReport(statusId, locationId);
    }

    @Override
    public List<Requisition> getRequisitionList(String locationId, String statusId, String startDate, String stopDate) {
        return this.requisitionDao.getRequisitionList(locationId, statusId, startDate, stopDate);
    }

    @Override
    public int addRequisition(Requisition requisition) {
        return this.requisitionDao.addRequisition(requisition);
    }

    @Override
    public int editRequisition(Requisition requisition) {
        return this.requisitionDao.editRequisition(requisition);
    }

    @Override
    public Requisition getRequisitionById(int requisitionId) {
        return this.requisitionDao.getRequisitionById(requisitionId);
    }

}
