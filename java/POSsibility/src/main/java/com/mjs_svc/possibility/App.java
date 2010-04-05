package com.mjs_svc.possibility;

import com.mjs_svc.possibility.util.HibernateUtil;
import com.mjs_svc.possibility.models.*;
import com.mjs_svc.possibility.views.*;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import javax.swing.*;

/**
 * Soon to be the main app, but now just a tiny testbed
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class App extends JFrame {
    private JMenuBar menuBar;
    private JDesktopPane desktop;

    public static void main( String[] args )
    {
        /*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User m = (User) session.load(User.class, Long.valueOf(1));
        System.out.println(m.getUsername());
        try {
            System.out.println(m.authenticate("fooble"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(m.getCreator());
        session.getTransaction().commit();*/
        new App().setVisible(true);
    }

    public App() {
        initComponents();
        JInternalFrame login = new JInternalFrame("Login");
        login.setContentPane(new Login());
        login.setVisible(true);
        login.pack();
        desktop.add(login);
    }

    private void initComponents() {
        desktop = new JDesktopPane();
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }
}
