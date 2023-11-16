/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    @EqualsAndHashCode.Include
    private int userId;
    private String emailId;
    private String name;
    private String password;
    private boolean active = true;
    private boolean expired;
    private int failedAttempts;
    private Date expiresOn;
    private Role role;
    private boolean outsideAccess;
    private Date lastLoginDate;
    private String phoneNo;
    private IdDesc location;
    private IdDesc department;
    private boolean addInRequisition;

    public String getActiveString() {
        return (this.active ? "Active" : "In active");
    }

}
