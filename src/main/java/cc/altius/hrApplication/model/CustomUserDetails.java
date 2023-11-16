/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cc.altius.utils.DateUtils;
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
public class CustomUserDetails implements UserDetails {

    @EqualsAndHashCode.Include
    private int userId;
    private String emailId;
    private String name;
    private String password;
    private boolean active;
    private boolean expired;
    private int failedAttempts;
    private boolean outsideAccess;
    private Date expiresOn;
    private Date lastLoginDate;
    private Role role;
    private IdDesc department;
    private List<SimpleGrantedAuthority> businessFunction;
    private IdDesc location;

    public void setBusinessFunction(List<String> businessFunction) {
        List<SimpleGrantedAuthority> finalBusinessFunction;
        finalBusinessFunction = new ArrayList<>();
        for (String bf : businessFunction) {
            finalBusinessFunction.add((new SimpleGrantedAuthority(bf)));
        }
        this.businessFunction = finalBusinessFunction;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return businessFunction;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return failedAttempts <= 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public boolean isPasswordExpired() {
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        return DateUtils.compareDates(DateUtils.formatDate(this.expiresOn, DateUtils.YMD), curDate) <= 0;
    }

}
