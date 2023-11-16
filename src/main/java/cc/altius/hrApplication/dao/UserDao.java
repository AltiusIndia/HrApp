/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.dao;

import java.util.List;

import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.Role;
import cc.altius.hrApplication.model.SimpleUser;
import cc.altius.hrApplication.model.User;

/**
 *
 * @author Akil Mahimwala
 */
public interface UserDao {

    public CustomUserDetails getCustomUserDetailsByEmailId(String emailId);

    public List<String> getBusinessFunctionsForUserId(int userId);

    public List<SimpleUser> getSimpleUserList(String roleList, boolean active);

    public List<User> getUserList(String roleId, String statusId);

    public List<Role> getRoleList();
    
    public boolean existUserByEmailId(String emailId);
    
    public int addUser(User user);
    
    public User getUserByUserId(int userId);
    
    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId);
    
    public int updateUser(User user);
    
    public int resetFailedAttemptsByUserId(int userId);
}
