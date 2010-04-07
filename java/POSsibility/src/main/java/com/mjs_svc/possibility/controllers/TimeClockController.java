package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.*;
import com.mjs_svc.possibility.util.HibernateUtil;
import com.mjs_svc.possibility.util.UserListener;
import org.hibernate.Session;
import java.util.Date;
import org.hibernate.HibernateException;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class TimeClockController {

    private UserListener a;

    public boolean doClockIn(User user) {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        Employee emp = user.getEmployee();
        if (emp instanceof Employee) {
            if (!emp.getIsClockedIn()) {
                TimeClockEvent evt = new TimeClockEvent();
                evt.setUser(emp);
                evt.setClockin(true);
                evt.setCtime(new Date());
                emp.getTimeClockEvents().add(evt);
                user.setEmployee(emp);
                org.hibernate.Transaction tx = null;
                try {
                    tx = sess.beginTransaction();
                    sess.save(evt);
                    sess.update(emp);
                    sess.update(user);
                    tx.commit();
                } catch (HibernateException he) {
                    if (tx != null) {
                        tx.rollback();
                    }
                    System.err.println(he.getMessage());
                    return false;
                }
                a.setUser(user);
                return true;
            }
            System.err.println("Already clocked in?");
        }
        return false;
    }

    public boolean doClockOut(User user) {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        Employee emp = user.getEmployee();
        if (emp instanceof Employee) {
            if (emp.getIsClockedIn()) {
                TimeClockEvent evt = new TimeClockEvent();
                evt.setUser(emp);
                evt.setClockin(false);
                evt.setCtime(new Date());
                emp.getTimeClockEvents().add(evt);
                user.setEmployee(emp);
                org.hibernate.Transaction tx = null;
                try {
                    tx = sess.beginTransaction();
                    sess.save(evt);
                    sess.update(emp);
                    sess.update(user);
                    tx.commit();
                } catch (HibernateException he) {
                    if (tx != null) {
                        tx.rollback();
                    }
                    System.err.println(he.getMessage());
                    return false;
                }
                a.setUser(user);
                return true;
            }
            System.err.println("Already clocked out?");
        }
        return false;
    }

    public void setUserListener(UserListener a) {
        this.a = a;
    }
}
