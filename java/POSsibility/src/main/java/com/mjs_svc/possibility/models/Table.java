package com.mjs_svc.possibility.models;

/**
 *
 * @author Matthew Scoot
 * @version $Id$
 */
public class Table {
    private int id, seats, x, y, length, width, shape, rotation;
    private TableArea area;
    private TableGroup group;
    private Ticket ticket;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableArea getArea() {
        return area;
    }

    public void setArea(TableArea area) {
        this.area = area;
    }

    public TableGroup getGroup() {
        return group;
    }

    public void setGroup(TableGroup group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //------------------------------------------------------------------------//

}
