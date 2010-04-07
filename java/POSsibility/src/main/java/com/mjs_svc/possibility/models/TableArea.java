package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class TableArea {
    private int id;
    private Employee owner;
    private Set tables = new HashSet();

    public Set getTables() {
        return tables;
    }

    public void setTables(Set tables) {
        this.tables = tables;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }
    //------------------------------------------------------------------------//
}
