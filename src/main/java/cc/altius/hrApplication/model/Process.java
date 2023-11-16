/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Process extends IdDescCodeActive {

    private SimpleUser buManager;
    private IdDesc location;

    public Process(String id, String description, String code, boolean active, SimpleUser buManager, IdDesc location) {
        super(id, description, code, active);
        this.buManager = buManager;
        this.location = location;
    }

}
