/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjs_svc.possibility.models;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Transaction {
    private int id, type;
    private Order order;
    private String description;
    private double amount;
    private Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setType(TypeChoices type) {
        this.type = type.getCode();
    }

    //------------------------------------------------------------------------//
    public enum TypeChoices {

        SALE(0),
        EMPLOYEE_PAYMENT(1),
        CREATOR_PAYMENT(2),
        CREDIT(4),
        EXPENSE(5),
        ASSET_PURCHASE(6),
        PRODUCT_PURCHASE(7);
        private final int code;

        private TypeChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getTypeDisplay(int t) {
        return new String[]{"Sale", "Employee Payment", "Creator Payment", "Store credit",
                    "Expense", "Asset purchase", "Product purchase"}[t];
    }
}
