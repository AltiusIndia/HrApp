/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.altius.hrApplication.dao.UserDao;
import cc.altius.hrApplication.model.Role;
import cc.altius.hrApplication.model.SimpleUser;
import cc.altius.hrApplication.model.User;
import cc.altius.hrApplication.service.UserService;

/**
 *
 * @author Akil Mahimwala
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<SimpleUser> getHrSimpleUserList(boolean active) {
        return this.userDao.getSimpleUserList("ROLE_HR,ROLE_HR_EXECUTIVE", active);
    }

    @Override
    public List<SimpleUser> getOpsSimpleUserList(boolean active) {
        return this.userDao.getSimpleUserList("ROLE_OPERATIONS,ROLE_TEAM_LEAD,ROLE_SR_TEAM_LEAD,ROLE_OPERATION_EXECUTIVE", active);
    }

    @Override
    public List<Role> getRoleList() {
        return this.userDao.getRoleList();
    }

    @Override
    public List<User> getUserList(String roleId, String statusId) {
        return this.userDao.getUserList(roleId, statusId);
    }

    @Override
    public boolean existUserByEmailId(String emailId) {
        return this.userDao.existUserByEmailId(emailId);
    }

    @Override
    public int addUser(User user) {
        try {
            return this.userDao.addUser(user);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public User getUserByUserId(int userId) {
        return this.userDao.getUserByUserId(userId);
    }

    @Override
    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId) {
        return this.userDao.canCreateRoleByRoleId(roleId, canCreateRoleId);
    }

    @Override
    public int updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public int resetFailedAttemptsByUserId(int userId) {
        return this.userDao.resetFailedAttemptsByUserId(userId);
    }

}
