package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew
 */
public class Permission {
    private int id;
    private String name, codeName;
    private ContentType contentType;
    private Set users = new HashSet(), groups = new HashSet();

    public Set getGroups() {
        return groups;
    }

    public void setGroups(Set groups) {
        this.groups = groups;
    }

    public Set getUsers() {
        return users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
