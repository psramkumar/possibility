package com.mjs_svc.possibility.models;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class TicketItem {
    private int id;
    private Patron patron;
    private Ticket ticket;
    private Product product;
    private double priceModifier;
    private boolean serverOnly; // i.e. not sent to kitchen
    private String notes;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isServerOnly() {
        return serverOnly;
    }

    public void setServerOnly(boolean serverOnly) {
        this.serverOnly = serverOnly;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    //------------------------------------------------------------------------//
}
