package com.mjs_svc.possibility.models;

import java.util.Date;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class TimeClockEvent {
    private int id;
    private User user;
    private Date ctime;
    private boolean clockin;

    public boolean isClockin() {
        return clockin;
    }

    public void setClockin(boolean clockin) {
        this.clockin = clockin;
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

    private void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
