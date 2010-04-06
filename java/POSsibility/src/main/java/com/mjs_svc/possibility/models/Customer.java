package com.mjs_svc.possibility.models;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Customer {

    private int id;
    private User user;
    private Address billingAddress;
    private Order activeCart;
    private Set orders = new HashSet(), shippingAddresses = new HashSet();

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Set getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public Set getOrders() {
        return orders;
    }

    public void setOrders(Set orders) {
        this.orders = orders;
    }

    public Order getActiveCart() {
        return activeCart;
    }

    public void setActiveCart(Order activeCart) {
        this.activeCart = activeCart;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //------------------------------------------------------------------------//
}
