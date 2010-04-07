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
    private List timeClockEvents;
    private Set troubleTickets = new HashSet(), tickets = new HashSet();
    private Boolean _isClockedIn;

    public Set getTickets() {
        return tickets;
    }

    public void setTickets(Set tickets) {
        this.tickets = tickets;
    }

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

    public List getTimeClockEvents() {
        return timeClockEvents;
    }

    public void setTimeClockEvents(List timeClockEvents) {
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
    public boolean getIsClockedIn() {
        try {
            _isClockedIn = Boolean.valueOf(((TimeClockEvent) timeClockEvents.get(0)).isClockin());
        } catch (Throwable t) {
            return false;
        }
        return _isClockedIn.booleanValue();
    }

    public void setIsClockedIn(boolean isClockedIn) {
        this._isClockedIn = Boolean.valueOf(isClockedIn);
    }
}
