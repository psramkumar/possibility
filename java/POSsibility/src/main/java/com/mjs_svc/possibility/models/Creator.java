package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Creator {
    private int id;
    private String profile;
    private User user;
    private Address address;
    private Set products = new HashSet();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set getProducts() {
        return products;
    }

    public void setProducts(Set products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //------------------------------------------------------------------------//
}
