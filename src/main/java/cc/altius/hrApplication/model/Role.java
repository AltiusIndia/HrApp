/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {

    @EqualsAndHashCode.Include
    private String roleId;
    private String roleName;

}
