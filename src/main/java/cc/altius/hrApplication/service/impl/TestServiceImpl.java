/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.TestDao;
import cc.altius.hrApplication.service.TestService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDao testDao;
    
    @Override
    public List<String> getUserList() {
        return this.testDao.getUserList();
    }
    
}
