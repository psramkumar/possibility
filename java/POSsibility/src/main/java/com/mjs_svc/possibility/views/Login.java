package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.App;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mjs_svc.possibility.models.User;
import com.mjs_svc.possibility.util.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Login extends JPanel {

    private ResourceBundle panelRB = ResourceBundle.getBundle(
            "PanelTexts",
            Locale.getDefault());
    public static String title;
    public static final boolean resizable = false;
    public static final boolean closable = true;
    public static final boolean maximizable = false;
    public static final boolean iconifiable = false;

    private JLabel username_label = new JLabel(panelRB.getString("login.username")),
            password_label = new JLabel(panelRB.getString("login.password")),
            error = new JLabel();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton submit = new JButton(panelRB.getString("login"));
    private UserListener a;

    public Login() {
        title = panelRB.getString("login");
        setLayout(new GridLayout(0, 2));
        setSize(250, 100);
        username_label.setFont(new Font(null, Font.BOLD, 14));
        password_label.setFont(new Font(null, Font.BOLD, 14));

        // Fire the submit button's action listener when enter is pressed
        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit.getActionListeners()[0].actionPerformed(e);
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
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
                error.setText(panelRB.getString("login.success"));
                if (a instanceof UserListener) {
                    a.setUser(user);
                }
                ((JInternalFrame) this
                        .getParent() // JLayeredPane
                        .getParent() // JRootPane
                        .getParent())// JInternalFrame
                        .dispose();
            } else {
                error.setBackground(Color.MAGENTA);
                error.setText(panelRB.getString("login.failure"));
            }
        } catch (NoSuchAlgorithmException e) {
            //
        } catch (NullPointerException e) {
            //
        }
    }

    public void setUserListener(UserListener a) {
        this.a = a;
    }
}
