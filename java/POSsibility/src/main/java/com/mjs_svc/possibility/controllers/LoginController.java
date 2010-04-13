package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.User;
import com.mjs_svc.possibility.util.*;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;

/**
 * Controller for logging users in
 * @author Matthew Scott
 * @version $Id$
 */
public class LoginController {
    public User user;

    /**
     * Given a username and a password, try to authenticate a user
     * @param username The username
     * @param password The plain-text password
     * @return True if the login was successful, false otherwise
     */
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
            // We'd better have it...
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Fetch the newly logged in user
     * @return A User object
     */
    public User getUser() {
        return user;
    }
}
