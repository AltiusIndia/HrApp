/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.dao;

import java.util.List;

import cc.altius.hrApplication.model.Candidate;

/**
 *
 * @author Akil Mahimwala
 */
public interface CandidateDao {

    public List<Candidate> searchForCandidates(String searchText, int searchNo);
}
