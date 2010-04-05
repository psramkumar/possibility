/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew
 */
public class Group {
    private int id;
    private String name;
    private Set permissions = new HashSet(), users = new HashSet();

    public Set getUsers() {
        return users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getPermissions() {
        return permissions;
    }

    public void setPermissions(Set permissions) {
        this.permissions = permissions;
    }

}
