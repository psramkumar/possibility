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
public class Item {
    private int id, quantity;
    private Order order;
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //------------------------------------------------------------------------//
}
