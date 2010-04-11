package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.models.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.mjs_svc.possibility.util.*;
import java.awt.GridBagLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeeDetail extends JPanel {
    private JLabel lId, lUsername, lFirstName, lLastName, lPosition;
    private JTextField username, firstName, lastName;
    private JSpinner id;
    private JComboBox position;
    private AddressDetail address;
    private Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
    private Employee employee;
    private int employeeId;
    private ResourceBundle rb = ResourceBundle.getBundle("FieldTitles");

    public static boolean closable = true;
    public static boolean maximizable = false;
    public static boolean resizable = false;
    public static boolean iconifiable = true;


    public EmployeeDetail(int empId) {
        employeeId = empId;
        
        lId = new JLabel(rb.getString("employee.id"));
        lUsername = new JLabel(rb.getString("user.username"));
        lFirstName = new JLabel(rb.getString("user.firstname"));
        lLastName = new JLabel(rb.getString("user.familyname"));
        lPosition = new JLabel(rb.getString("employee.position"));

        username = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();

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
        
        loadData();

        setLayout(new GridBagLayout());
        GridBagConstraints labels = new GridBagConstraints();
        labels.gridx = 0;
        labels.ipadx = 2;
        labels.anchor = GridBagConstraints.LINE_END;
        GridBagConstraints fields = new GridBagConstraints();
        fields.gridx = 1;
        fields.weightx = 1;
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
        add(lPosition, labels);
        fields.gridy = 4;
        add(position, fields);

        labels.gridy = 5;
        labels.gridwidth = 2;
        add(address, labels);

        //labels.gridy = 6;
        //labels.gridwidth = 1;

    }

    private void loadData() {
        sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        employee = (Employee) sess.load(Employee.class, employeeId);

        username.setText(employee.getUser().getUsername());
        firstName.setText(employee.getUser().getFirstName());
        lastName.setText(employee.getUser().getLastName());

        for (int i = 0; i < position.getItemCount(); i++) {
            if (employee.getPosition().getName().equals(position.getItemAt(i))) {
                position.setSelectedIndex(i);
                break;
            }
        }

        address = new AddressDetail(employee.getAddress(), false, true, false);
        
        sess.getTransaction().commit();
    }

}
