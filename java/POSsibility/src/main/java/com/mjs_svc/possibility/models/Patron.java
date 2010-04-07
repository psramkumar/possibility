package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Patron {
    private int id;
    private String location, notes;
    private Ticket ticket;
    private Set ticketItems = new HashSet();

    public Set getTicketItems() {
        return ticketItems;
    }

    public void setTicketItems(Set ticketItems) {
        this.ticketItems = ticketItems;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    //------------------------------------------------------------------------//
}
