/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjs_svc.possibility.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mjs_svc.possibility.models.User;
import com.mjs_svc.possibility.util.HibernateUtil;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Login extends JPanel {

    private JLabel username_label = new JLabel("User Name:"),
            password_label = new JLabel("Password:"),
            error = new JLabel();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton submit = new JButton("Login");

    public Login() {
        setLayout(new GridLayout(0, 2));
        username_label.setFont(new Font(null, Font.BOLD, 14));
        password_label.setFont(new Font(null, Font.BOLD, 14));
        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
        add(username_label);
        add(username);
        add(password_label);
        add(password);
        add(error);
        add(submit);
    }

    public void doLogin() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("from User as u where username = ?").setString(0, username.getText()).uniqueResult();
        try {
            if (user.authenticate(new String(password.getPassword()))) {
                error.setBackground(password.getBackground());
                error.setText("Login succeeded");
            } else {
                error.setBackground(Color.MAGENTA);
                error.setText("Login failed, try again");
            }
        } catch (NoSuchAlgorithmException e) {
            //
        } catch (NullPointerException e) {
            //
        }
    }
}
