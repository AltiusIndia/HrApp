/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.CandidateDao;
import cc.altius.hrApplication.model.Candidate;
import cc.altius.hrApplication.service.CandidateService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;
    
    @Override
    public List<Candidate> searchForCandidates(String searchText, int searchNo) {
        return this.candidateDao.searchForCandidates(searchText, searchNo);
    }

}
