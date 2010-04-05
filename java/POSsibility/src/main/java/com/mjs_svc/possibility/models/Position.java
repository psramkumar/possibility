/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Position {
    private int id, type;
    private String name, shortDescription, longDescription;
    private double pay;
    private Set employees = new HashSet();

    public Set getEmployees() {
        return employees;
    }

    public void setEmployees(Set employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setType(TypeChoices type) {
        this.type = type.getCode();
    }


    //------------------------------------------------------------------------//
    public enum TypeChoices {
        VOLUNTEER (0),
        HOURLY (1),
        SALARY (2),
        CONTRACT (3);

        private final int code;
        private TypeChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    public String getTypeDisplay(int t) {
        return new String[] {
            "Volunteer", "Hourly", "Salary", "Contract"
        }[t];
    }
}
