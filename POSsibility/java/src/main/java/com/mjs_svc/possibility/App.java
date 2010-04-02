package com.mjs_svc.possibility;

import com.mjs_svc.possibility.util.HibernateUtil;
import com.mjs_svc.possibility.models.User;

import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User m = (User) session.load(User.class, Long.valueOf(1));
        System.out.println(m.getUsername());
        try {
            System.out.println(m.authenticate("fooble"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }
}
