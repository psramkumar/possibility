package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.User;
import com.mjs_svc.possibility.util.*;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class LoginController {
    public User user;

    public boolean doLogin(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        user = (User) session.createQuery("from User where username = ?").setString(0, username).uniqueResult();
        try {
            if (user.authenticate(password)) {
                //session.getTransaction().commit();
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            //
        }
        return false;
    }

    public User getUser() {
        return user;
    }
}
