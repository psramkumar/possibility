package com.mjs_svc.possibility.models;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class TroubleTicket {
    private int id, status, priority;
    private Order order;
    private Employee owner;
    private String problem, solution;
    private Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setPriority(PriorityChoices priority) {
        this.priority = priority.getCode();
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(StatusChoices status) {
        this.status = status.getCode();
    }

    //------------------------------------------------------------------------//
    public enum StatusChoices {

        UNRESOLVED(0),
        ASSIGNED(1),
        PARTIALLY_RESOLVED(2),
        RESOLVED(3);
        private int code;

        private StatusChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getStatusDisplay(int s) {
        return new String[] {
            "Unresolved", "Assigned", "Partially Resolved", "Resolved"
        }[s];
    }

    public enum PriorityChoices {

        LOW(0),
        NORMAL(1),
        HIGH(2),
        URGENT(3);
        private int code;

        private PriorityChoices(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getPriorityDisplay(int p) {
        return new String[] {"Low", "Normal", "High", "URGENT"}[p];
    }
}
