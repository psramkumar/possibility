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
public class Employee {
    private int id;
    private User user;
    private Position position;
    private String phone, address1, address2, city, state, zipcode, country;
    private Set timeClockEvents = new HashSet(), troubleTickets = new HashSet();

    public Set getTroubleTickets() {
        return troubleTickets;
    }

    public void setTroubleTickets(Set troubleTickets) {
        this.troubleTickets = troubleTickets;
    }

    public Set getTimeClockEvents() {
        return timeClockEvents;
    }

    public void setTimeClockEvents(Set timeClockEvents) {
        this.timeClockEvents = timeClockEvents;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    //------------------------------------------------------------------------//

}
