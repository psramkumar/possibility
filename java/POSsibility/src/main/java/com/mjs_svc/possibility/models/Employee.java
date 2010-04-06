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
    private Address address;
    private Set timeClockEvents = new HashSet(), troubleTickets = new HashSet();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //------------------------------------------------------------------------//

}
