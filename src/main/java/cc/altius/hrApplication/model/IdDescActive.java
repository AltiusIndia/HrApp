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
public class IdDescActive extends IdDesc {

    private boolean active = true;

    public IdDescActive(String id, String description, boolean active) {
        super(id, description);
        this.active = active;
    }

    public String getActiveString() {
        return (this.active ? "Active" : "In active");
    }
}
