package com.mjs_svc.possibility.models;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Restaurant {
    private int id;
    private String geometry;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
