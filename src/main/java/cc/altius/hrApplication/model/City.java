/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Akil Mahimwala
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class City extends IdDesc {

    private IdDesc state;

    public City(String id, String value, IdDesc state) {
        super(id, value);
        this.state = state;
    }

    public City(String key, String value) {
        super(key, value);
    }

}
