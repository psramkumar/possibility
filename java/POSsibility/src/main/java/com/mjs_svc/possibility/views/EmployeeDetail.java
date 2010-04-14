package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.models.*;
import com.mjs_svc.possibility.util.*;
import com.mjs_svc.possibility.controllers.EmployeeDetailController;
import com.mjs_svc.possibility.exceptions.PermissionDeniedException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.GridBagLayout;
import javax.swing.event.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeeDetail extends JPanel {
    private JLabel lId, lUsername, lFirstName, lLastName, lEmail, lPosition;
    private JTextField username, firstName, lastName, email;
    private JSpinner id;
    private JComboBox position;
    private AddressDetail address;
    private JButton create, update, delete;

    private Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
    private Employee employee;
    private int employeeId;
    private ResourceBundle rb = ResourceBundle.getBundle("FieldTitles");

    public static boolean closable = true;
    public static boolean maximizable = false;
    public static boolean resizable = false;
    public static boolean iconifiable = true;

    /**
     *
     * @param empId
     */
    public EmployeeDetail(int empId) {
        employeeId = empId;
        
        lId = new JLabel(rb.getString("employee.id"));
        lUsername = new JLabel(rb.getString("user.username"));
        lFirstName = new JLabel(rb.getString("user.firstname"));
        lLastName = new JLabel(rb.getString("user.familyname"));
        lEmail = new JLabel(rb.getString("user.email"));
        lPosition = new JLabel(rb.getString("employee.position"));

        username = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();

        sess.beginTransaction();
        id = new JSpinner(new SpinnerListModel(sess.createQuery("select id from Employee").list()));
        id.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                employeeId = (Integer)
                        ((SpinnerListModel)
                        ((JSpinner) e.getSource()).getModel()).getValue();
                loadData();
            }
        });
        position = new JComboBox(sess.createQuery("select name from Position").list().toArray());

        address = new AddressDetail(new Address(), false, true, false);
        sess.getTransaction().commit();

        create = new JButton(rb.getString("create"));
        create.setEnabled(UserContainer.getUser().hasPermission("add_employee"));
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sess = HibernateUtil.getSessionFactory().getCurrentSession();
                    sess.beginTransaction();
                    employee = EmployeeDetailController.createEmployee(
                            username.getText(),
                            firstName.getText(),
                            lastName.getText(),
                            email.getText(),
                            (Position) sess.createQuery("from Position where name = :n")
                            .setParameter("n", (String) position.getSelectedItem())
                            .uniqueResult());
                    //sess.getTransaction().commit(); // createEmployee calls this
                } catch (PermissionDeniedException exc) {
                    // do somethign
                } finally {
                    loadData();
                    PingPublisher.ping();
                }
            }
        });
        update = new JButton(rb.getString("update"));
        update.setEnabled(UserContainer.getUser().hasPermission("change_employee"));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sess = HibernateUtil.getSessionFactory().getCurrentSession();
                    sess.beginTransaction();
                    employee = EmployeeDetailController.updateEmployee(
                            employee.getId(),
                            username.getText(),
                            firstName.getText(),
                            lastName.getText(),
                            email.getText(),
                            (Position) sess.createQuery("from Position where name = :n")
                            .setParameter("n", (String) position.getSelectedItem())
                            .uniqueResult());
                    //sess.getTransaction().commit(); // createEmployee calls this
                } catch (PermissionDeniedException exc) {
                    // do somethign
                } finally {
                    loadData();
                    PingPublisher.ping();
                }
            }
        });
        delete = new JButton(rb.getString("delete"));
        delete.setEnabled(UserContainer.getUser().hasPermission("delete_employee"));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeDetailController.deleteEmployee(employee.getId());
                if (id.getModel().getNextValue() != null) {
                    id.getModel().setValue(id.getModel().getNextValue());
                } else if (id.getModel().getPreviousValue() != null) {
                    id.getModel().setValue(id.getModel().getNextValue());
                }
            }
        });
        
        loadData();

        setLayout(new GridBagLayout());
        GridBagConstraints labels = new GridBagConstraints();
        labels.gridx = 0;
        labels.ipadx = 2;
        labels.anchor = GridBagConstraints.LINE_END;
        GridBagConstraints fields = new GridBagConstraints();
        fields.gridx = 1;
        fields.weightx = 1;
        fields.gridwidth = 2;
        fields.fill = GridBagConstraints.HORIZONTAL;

        labels.gridy = 0;
        add(lId, labels);
        fields.gridy = 0;
        add(id, fields);

        labels.gridy = 1;
        add(lUsername, labels);
        fields.gridy = 1;
        add(username, fields);

        labels.gridy = 2;
        add(lFirstName, labels);
        fields.gridy = 2;
        add(firstName, fields);

        labels.gridy = 3;
        add(lLastName, labels);
        fields.gridy = 3;
        add(lastName, fields);

        labels.gridy = 4;
        add(lEmail, labels);
        fields.gridy = 4;
        add(email, fields);

        labels.gridy = 5;
        add(lPosition, labels);
        fields.gridy = 5;
        add(position, fields);

        labels.gridy = 6;
        labels.gridwidth = 3;
        add(address, labels);

        labels.gridwidth = 1;
        labels.gridy = 7;
        labels.anchor = GridBagConstraints.LINE_START;
        add(create, labels);
        fields.gridwidth = 1;
        fields.gridy = 7;
        add(update, fields);
        fields.gridx = 2;
        add(delete, fields);
    }

    /**
     * 
     */
    private void loadData() {
        sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        
        employee = (Employee) sess.load(Employee.class, employeeId);

        id.getModel().setValue(employee.getId());
        username.setText(employee.getUser().getUsername());
        firstName.setText(employee.getUser().getFirstName());
        lastName.setText(employee.getUser().getLastName());
        email.setText(employee.getUser().getEmail());

        for (int i = 0; i < position.getItemCount(); i++) {
            if (employee.getPosition().getName().equals(position.getItemAt(i))) {
                position.setSelectedIndex(i);
                break;
            }
        }

        address = new AddressDetail(employee.getAddress(), false, true, false);

        try {
            sess.getTransaction().commit();
        } catch (HibernateException e) {
            //
        }
    }

}
