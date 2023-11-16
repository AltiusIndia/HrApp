/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cc.altius.hrApplication.service;

import java.util.List;

import cc.altius.hrApplication.model.Role;
import cc.altius.hrApplication.model.SimpleUser;
import cc.altius.hrApplication.model.User;

/**
 *
 * @author Akil Mahimwala
 */
public interface UserService {

    public List<SimpleUser> getHrSimpleUserList(boolean active);

    public List<SimpleUser> getOpsSimpleUserList(boolean active);

    public List<User> getUserList(String roleId, String statusId);

    public List<Role> getRoleList();
    
    public boolean existUserByEmailId(String emailId);
    
    public int addUser(User user);
    
    public User getUserByUserId(int userId);
    
    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId);
    
    public int updateUser(User user);
    
    public int resetFailedAttemptsByUserId(int userId);
}
