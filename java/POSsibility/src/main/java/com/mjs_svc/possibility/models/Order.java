package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Order {

    private int id, localStatus;
    private User owner, isActive;
    private boolean isShared, isGift;
    private Date ctime, mtime;
    private String name, notes;
    private Site site;
    private Address shipTo, billTo;
    private Set troubleTickets = new HashSet();

    public boolean isIsGift() {
        return isGift;
    }

    public void setIsGift(boolean isGift) {
        this.isGift = isGift;
    }

    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public Set getTroubleTickets() {
        return troubleTickets;
    }

    public void setTroubleTickets(Set troubleTickets) {
        this.troubleTickets = troubleTickets;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIsActive() {
        return isActive;
    }

    public void setIsActive(User isActive) {
        this.isActive = isActive;
    }

    public boolean isIsShared() {
        return isShared;
    }

    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }

    public int getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(int localStatus) {
        this.localStatus = localStatus;
    }

    public void setLocalStatus(StatusChoices localStatus) {
        this.localStatus = localStatus.getCode();
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    //------------------------------------------------------------------------//
    public enum StatusChoices {

        FINISHED_SHOPPING(0),
        STILL_SHOPPING(1),
        SYSTEM_ERROR(2),
        USER_PROBLEM(3),
        OFFLINE_ORDER(4);
        private final int code;

        private StatusChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getStatusDisplay(int s) {
        return new String[] {
            "Finished shopping", "Still shopping", "System error",
            "User reported problem", "Offline order"
        }[s];
    }
}

