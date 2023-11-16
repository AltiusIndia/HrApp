/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.altius.hrApplication.model.CustomUserDetails;
import cc.altius.hrApplication.model.IdDescActive;
import cc.altius.hrApplication.model.Process;
import cc.altius.hrApplication.model.User;
import cc.altius.hrApplication.service.MasterService;
import cc.altius.hrApplication.service.UserService;
import cc.altius.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Akil Mahimwala
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private MasterService masterService;

    @RequestMapping(value = "/userList.htm", method = RequestMethod.GET)
    public String showUserListPage(ModelMap model, HttpServletRequest request, HttpSession session) {
        String roleId = ServletRequestUtils.getStringParameter(request, "roleId", SessionUtils.fetchData("roleId", request, session, ""));
        String statusId = ServletRequestUtils.getStringParameter(request, "statusId", SessionUtils.fetchData("statusId", request, session, "1"));
        model.addAttribute("roleList", this.userService.getRoleList());
        model.addAttribute("userList", this.userService.getUserList(roleId, statusId));
        model.addAttribute("statusId", statusId);
        model.addAttribute("roleId", roleId);
        return "admin/userList";
    }

    @RequestMapping(value = "/userAdd.htm", method = RequestMethod.GET)
    public String showAddUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleList", this.userService.getRoleList());
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(true));
        return "admin/userAdd";
    }

    @RequestMapping(value = "/userAdd.htm", method = RequestMethod.POST)
    public String onAddUserSubmit(@ModelAttribute("user") User user, Errors errors, ModelMap model, HttpServletRequest request) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            user = null;
            return "redirect:../admin/userList.htm?msg=msg.actionCancelled";
        } else {
            if (this.userService.existUserByEmailId(user.getEmailId())) {
                errors.rejectValue("username", "msg.duplicateUser");
                return "admin/userAdd";
            }
            int userId = this.userService.addUser(user);
            if (userId == 0) {
                errors.rejectValue("username", "msg.userError");
                return "admin/userAdd";
            } else {
                return "redirect:/admin/userList.htm?msg=msg.userAddedSuccessfully";
            }
        }
    }

    @RequestMapping(value = "/userEditShow.htm", method = RequestMethod.POST)
    public String showUserEditForm(@RequestParam(value = "userId", required = true) int userId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = this.userService.getUserByUserId(userId);
        if (!this.userService.canCreateRoleByRoleId(curUser.getRole().getRoleId(), user.getRole().getRoleId())) {
            return "redirect:/admin/userList.htm?error=msg.cantEditRole";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("roleList", this.userService.getRoleList());
            model.addAttribute("locationList", this.masterService.getSimpleLocationList(false));
            model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(false));
            return "admin/userEdit";
        }
    }

    @RequestMapping(value = "/userEdit.htm", method = RequestMethod.POST)
    public String onEditUserSubmit(@ModelAttribute("user") User user, Errors errors, ModelMap model, HttpServletRequest request) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            user = null;
            return "redirect:../admin/userList.htm?msg=msg.actionCancelled";
        }
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (!this.userService.canCreateRoleByRoleId(curUser.getRole().getRoleId(), user.getRole().getRoleId())) {
            return "redirect:/admin/userList.htm?error=msg.cantEditRole";
        } else {
            try {
                this.userService.updateUser(user);
                return "redirect:/admin/userList.htm?msg=msg.userUpdatedSuccessfully";
            } catch (Exception e) {
                errors.rejectValue("username", "msg.userError");
                model.addAttribute("roleList", this.userService.getRoleList());
                model.addAttribute("locationList", this.masterService.getSimpleLocationList(false));
                model.addAttribute("departmentList", this.masterService.getSimpleDepartmentList(false));
                return "admin/userEdit";
            }
        }
    }

    @RequestMapping(value = "/userFailedAttemptsReset.htm")
    public String resetFailedAttempts(@RequestParam(value = "userId", required = true) int userId, HttpServletRequest request, HttpSession session) {
        if (this.userService.resetFailedAttemptsByUserId(userId) != 0) {
            return "redirect:../admin/userList.htm?msg=Failed attempts reset successfully";
        } else {
            return "redirect:../admin/userList.htm?error=Could not reset the Failed attempts";
        }
    }

    @RequestMapping(value = "/departmentAdd.htm", method = RequestMethod.GET)
    public String addDepartmentFormGet(ModelMap model) {
        model.addAttribute("department", new IdDescActive());
        return "admin/departmentAdd";
    }

    @RequestMapping(value = "/departmentAdd.htm", method = RequestMethod.POST)
    public String addDepartmentFormPost(@ModelAttribute("department") IdDescActive department, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            department = null;
            return "redirect:../admin/departmentList.htm?msg=msg.actionCancelled";
        }
        if (this.masterService.addDepartment(department) != 0) {
            return "redirect:/admin/departmentList.htm?msg=" + URLEncoder.encode("Department added successfully", "UTF-8");
        } else {
            errors.rejectValue("departmentDesc", "msg.processError");
            return "redirect:/department/addDepartment.htm?msg=" + URLEncoder.encode("Failed to add Department", "UTF-8");
        }
    }

    @RequestMapping(value = "/departmentList.htm", method = RequestMethod.GET)
    public String listDepartment(ModelMap model) {
        model.addAttribute("departmentList", this.masterService.getDepartmentList(false));
        return "admin/departmentList";
    }

    @RequestMapping(value = "/departmentEditShow.htm", method = RequestMethod.POST)
    public String showEditDepartmentForm(@RequestParam(value = "id", required = true) int departmentId, ModelMap model) {
        model.addAttribute("department", this.masterService.getDepartmentById(departmentId));
        return "admin/departmentEdit";
    }

    @RequestMapping(value = "/departmentEdit.htm", method = RequestMethod.POST)
    public String onEditDepartmentSubmit(@ModelAttribute("department") IdDescActive department, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            department = null;
            return "redirect:../admin/departmentList.htm?msg=msg.actionCancelled";
        }
        this.masterService.editDepartment(department);
        return "redirect:/admin/departmentList.htm?msg=msg.departmentUpdatedSuccessfully";
    }
    
    @RequestMapping(value = "/processAdd.htm", method = RequestMethod.GET)
    public String addProcessFormGet(ModelMap model) {
        model.addAttribute("process", new Process());
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(true));
        model.addAttribute("buManagerList", this.masterService.getBuManagerList());
        return "admin/processAdd";
    }

    @RequestMapping(value = "/processAdd.htm", method = RequestMethod.POST)
    public String addProcessFormPost(@ModelAttribute("process") Process process, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            process = null;
            return "redirect:../admin/processList.htm?msg=msg.actionCancelled";
        }
        if (this.masterService.addProcess(process) != 0) {
            return "redirect:/admin/processList.htm?msg=" + URLEncoder.encode("Process added successfully", "UTF-8");
        } else {
            errors.rejectValue("processDesc", "msg.processError");
            return "redirect:/process/addProcess.htm?msg=" + URLEncoder.encode("Failed to add Process", "UTF-8");
        }
    }

    @RequestMapping(value = "/processList.htm", method = RequestMethod.GET)
    public String listProcess(ModelMap model) {
        model.addAttribute("processList", this.masterService.getProcessList(false));
        return "admin/processList";
    }

    @RequestMapping(value = "/processEditShow.htm", method = RequestMethod.POST)
    public String showEditProcessForm(@RequestParam(value = "id", required = true) int processId, ModelMap model) {
        model.addAttribute("process", this.masterService.getProcessById(processId));
        model.addAttribute("locationList", this.masterService.getSimpleLocationList(false));
        model.addAttribute("buManagerList", this.masterService.getBuManagerList());
        return "admin/processEdit";
    }

    @RequestMapping(value = "/processEdit.htm", method = RequestMethod.POST)
    public String onEditProcessSubmit(@ModelAttribute("process") Process process, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            process = null;
            return "redirect:../admin/processList.htm?msg=msg.actionCancelled";
        }
        this.masterService.editProcess(process);
        return "redirect:/admin/processList.htm?msg=msg.processUpdatedSuccessfully";
    }
    
    @RequestMapping(value = "/designationAdd.htm", method = RequestMethod.GET)
    public String addDesignationFormGet(ModelMap model) {
        model.addAttribute("designation", new IdDescActive());
        return "admin/designationAdd";
    }

    @RequestMapping(value = "/designationAdd.htm", method = RequestMethod.POST)
    public String addDesignationFormPost(@ModelAttribute("designation") IdDescActive designation, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            designation = null;
            return "redirect:../admin/designationList.htm?msg=msg.actionCancelled";
        }
        if (this.masterService.addDesignation(designation) != 0) {
            return "redirect:/admin/designationList.htm?msg=" + URLEncoder.encode("Designation added successfully", "UTF-8");
        } else {
            errors.rejectValue("designationDesc", "msg.processError");
            return "redirect:/designation/addDesignation.htm?msg=" + URLEncoder.encode("Failed to add Designation", "UTF-8");
        }
    }

    @RequestMapping(value = "/designationList.htm", method = RequestMethod.GET)
    public String listDesignation(ModelMap model) {
        model.addAttribute("designationList", this.masterService.getDesignationList(false));
        return "admin/designationList";
    }

    @RequestMapping(value = "/designationEditShow.htm", method = RequestMethod.POST)
    public String showEditDesignationForm(@RequestParam(value = "id", required = true) int designationId, ModelMap model) {
        model.addAttribute("designation", this.masterService.getDesignationById(designationId));
        return "admin/designationEdit";
    }

    @RequestMapping(value = "/designationEdit.htm", method = RequestMethod.POST)
    public String onEditDesignationSubmit(@ModelAttribute("designation") IdDescActive designation, Errors errors, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        if (ServletRequestUtils.getStringParameter(request, "_cancel", null) != null) {
            designation = null;
            return "redirect:../admin/designationList.htm?msg=msg.actionCancelled";
        }
        this.masterService.editDesignation(designation);
        return "redirect:/admin/designationList.htm?msg=msg.designationUpdatedSuccessfully";
    }
}
