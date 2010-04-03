/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjs_svc.possibility.models;

import java.util.Date;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Order {

    private int id, localStatus;
    private User owner, isActive;
    private boolean isShared;
    private Date ctime, mtime;
    private String state, payment, googleId, cartXml, notes;

    public String getCartXml() {
        return cartXml;
    }

    public void setCartXml(String cartXml) {
        this.cartXml = cartXml;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

