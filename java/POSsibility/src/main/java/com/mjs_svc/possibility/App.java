package com.mjs_svc.possibility;

import com.mjs_svc.possibility.util.*;
import com.mjs_svc.possibility.views.*;
import com.mjs_svc.possibility.models.*;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Soon to be the main app, but now just a tiny testbed
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class App extends JFrame implements UserListener {
    private User user;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu,
            viewPeople, vpEmployees, vpCustomers, vpCreators,
            viewProducts, viewOrders, viewTransactions, viewTroubleTickets;
    private JMenuItem
            f_settings, f_login, f_logout, f_clockin, f_clockout, f_exit, //file
            vpe_all, vpe_filter, vpe_new,           //employees
            vpcus_all, vpcus_filter,                //customers
            vpcr_all, vpcr_filter, vpcr_new,        //creators
            vpr_all, vpr_filter, vpr_new,           //products
            vo_all, vo_filter,                      //orders
            vtr_all, vtr_filter,                    //transactions
            vtt_all, vtt_mine, vtt_filter, vtt_new; //troubletickets
    private JDesktopPane desktop;
    private Login loginPanel = new Login(); // needed for setting user listener
    private StatusBar statusBar = new StatusBar();
    private ResourceBundle menuRB = ResourceBundle.getBundle(
            "MenuTexts",
            Locale.getDefault()),
            statusRB = ResourceBundle.getBundle(
            "StatusTexts",
            Locale.getDefault());

    public static void main( String[] args ) {
        new App().setVisible(true);
    }

    public App() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        loginPanel.setUserListener(this);
        initComponents();
    }

    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void initComponents() {
        desktop = new JDesktopPane();
        menuBar = new JMenuBar();

        fileMenu = new JMenu(menuRB.getString("file"));
        f_login = new JMenuItem(menuRB.getString("file.login"));
        f_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("static-access")
                JInternalFrame login = new JInternalFrame(
                        loginPanel.title,
                        loginPanel.resizable,
                        loginPanel.closable,
                        loginPanel.maximizable,
                        loginPanel.iconifiable);
                login.setContentPane(loginPanel);
                login.pack();
                login.setVisible(true);
                login.setSize(loginPanel.getSize());
                desktop.add(login);
            }
        });
        fileMenu.add(f_login);
        f_logout = new JMenuItem(menuRB.getString("file.logout"));
        f_logout.setEnabled(false);
        fileMenu.add(f_logout);
        f_clockin = new JMenuItem(menuRB.getString("file.clockin"));
        f_clockin.setEnabled(false);
        fileMenu.add(f_clockin);
        f_clockout = new JMenuItem(menuRB.getString("file.clockout"));
        f_clockout.setEnabled(false);
        fileMenu.add(f_clockout);
        f_settings = new JMenuItem(menuRB.getString("file.settings"));
        fileMenu.add(f_settings);
        fileMenu.addSeparator();
        f_exit = new JMenuItem(menuRB.getString("file.exit"));
        f_exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        fileMenu.add(f_exit);
        menuBar.add(fileMenu);

        editMenu = new JMenu(menuRB.getString("edit"));
        menuBar.add(editMenu);
        viewMenu = new JMenu(menuRB.getString("view"));
        menuBar.add(viewMenu);
        helpMenu = new JMenu(menuRB.getString("help"));
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.getTransaction().commit();
                    session.close();
                } catch (HibernateException exc) {
                    //
                }
                System.exit(0);
            }
        });

        statusBar.setStatus(statusRB.getString("welcome"));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(desktop, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
            .addComponent(statusBar, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(desktop, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
            .addComponent(statusBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        setTitle("POSsibility");

        pack();
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        setTitle(this.getTitle() + " (" + user.getUsername() + ")");
        statusBar.setStatus(statusRB.getString("onlogin").replace("{0}", user.getUsername()));
        f_login.setEnabled(false);
        f_logout.setEnabled(true);
        if (user.getEmployee() instanceof Employee) {
            // TODO: check whether clocked in, enable accordingly
            f_clockin.setEnabled(true);
            f_clockout.setEnabled(true);
        }
    }
}
