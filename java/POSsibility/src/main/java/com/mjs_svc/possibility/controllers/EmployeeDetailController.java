package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.exceptions.PermissionDeniedException;
import com.mjs_svc.possibility.models.*;
import com.mjs_svc.possibility.util.*;
import java.util.*;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeeDetailController {

    public static Employee createEmployee(String username, String firstname,
            String lastname, String email, Position p)
            throws PermissionDeniedException {
        // Check permissions
        if (!UserContainer.getUser().hasPermission("create_employee")) {
            throw new PermissionDeniedException();
        }

        ResourceBundle rb = ResourceBundle.getBundle(
                "FieldTitles", Locale.getDefault());

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        // Create a new Employee and associated user and address
        User u = new User();
        Address a = new Address();
        Employee emp = new Employee();

        // Set user information
        u.setUsername(username);
        u.setFirstName(firstname);
        u.setLastName(lastname);
        u.setEmail(email);
        u.setPassword(u.generateNewPasswordHash(username));
        u.setLastLogin(new Date());
        u.setDateJoined(new Date());

        // Set address information
        a.setName(rb.getString("address.name"));
        a.setPhone(rb.getString("address.phone"));
        a.setAddress1(rb.getString("address.address1"));
        a.setCity(rb.getString("address.city"));
        a.setState(rb.getString("address.state"));
        a.setCountry("United States of America");

        // Set employee information
        emp.setUser(u);
        emp.setAddress(a);
        emp.setPosition(p);

        // Save everything
        sess.save(u);
        sess.save(a);
        sess.save(emp);

        // Commit
        sess.getTransaction().commit();

        // Return our new employee
        return emp;
    }

    public static Employee updateEmployee(int id, String username, String firstname,
            String lastname, String email, Position p)
            throws PermissionDeniedException {
        // Check permissions
        if (!UserContainer.getUser().hasPermission("change_employee")) {
            throw new PermissionDeniedException();
        }

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        // Get the employee and associated user
        Employee emp = (Employee) sess.load(Employee.class, id);
        User u = emp.getUser();

        // Set user data
        u.setUsername(username);
        u.setFirstName(firstname);
        u.setLastName(lastname);
        u.setEmail(email);

        // Set position
        emp.setPosition(p);

        // Update in db
        sess.update(u);
        sess.update(emp);

        // Commit
        sess.getTransaction().commit();

        // Return updated employee
        return emp;
    }

    public static void deleteEmployee(int id) {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        Employee emp = (Employee) sess.load(Employee.class, id);
        sess.delete(emp);

        sess.getTransaction().commit();
    }
}
