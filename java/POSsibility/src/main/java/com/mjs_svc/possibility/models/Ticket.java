package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Ticket {
    private int id, paymentMethod;;
    private boolean paid;
    private Employee owner;
    private Ticket parent;
    private Set children = new HashSet(), ticketItems = new HashSet(),
            table = new HashSet(), tableGroup = new HashSet(),
            patrons = new HashSet();

    public Set getPatrons() {
        return patrons;
    }

    public void setPatrons(Set patrons) {
        this.patrons = patrons;
    }

    public Set getTable() {
        return table;
    }

    public void setTable(Set table) {
        this.table = table;
    }

    public Set getTableGroup() {
        return tableGroup;
    }

    public void setTableGroup(Set tableGroup) {
        this.tableGroup = tableGroup;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

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

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Ticket getParent() {
        return parent;
    }

    public void setParent(Ticket parent) {
        this.parent = parent;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(PaymentChoices paymentMethod) {
        this.paymentMethod = paymentMethod.getCode();
    }
    //------------------------------------------------------------------------//
    public enum PaymentChoices {
        CASH(0),
        CHECK(1),
        CHARGE(2);
        private int code;

        private PaymentChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getPaymentDisplay(int p) {
        return new String[]{"Cash", "Check", "Charge"}[p];
    }
}
