package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.models.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.mjs_svc.possibility.util.*;
import java.awt.GridBagLayout;
import javax.swing.event.*;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id: EmployeeDetail.java 24 2010-04-11 22:22:28Z matthew.joseph.scott $
 */
public class CustomerDetail extends JPanel {
    private JLabel lId, lUsername, lFirstName, lLastName;
    private JTextField username, firstName, lastName;
    private JSpinner id;
    private AddressDetail[] addresses;
    private JScrollPane addressPane;
    private JButton create, update, delete;

    private Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
    private Customer customer;
    private int customerId;
    private ResourceBundle rb = ResourceBundle.getBundle("FieldTitles");

    public static boolean closable = true;
    public static boolean maximizable = false;
    public static boolean resizable = false;
    public static boolean iconifiable = true;


    public CustomerDetail(int empId) {
        customerId = empId;

        lId = new JLabel(rb.getString("customer.id"));
        lUsername = new JLabel(rb.getString("user.username"));
        lFirstName = new JLabel(rb.getString("user.firstname"));
        lLastName = new JLabel(rb.getString("user.familyname"));

        username = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();

        sess.beginTransaction();
        id = new JSpinner(new SpinnerListModel(sess.createQuery("select id from Customer").list()));
        id.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                customerId = (Integer)
                        ((SpinnerListModel)
                        ((JSpinner) e.getSource()).getModel()).getValue();
                loadData();
            }
        });

        addresses = new AddressDetail[] {new AddressDetail(new Address(), false, true, false)};
        sess.getTransaction().commit();
        addressPane = new JScrollPane(addresses[0]);

        create = new JButton(rb.getString("create"));
        create.setEnabled(UserContainer.getUser().hasPermission("add_customer"));
        update = new JButton(rb.getString("update"));
        update.setEnabled(UserContainer.getUser().hasPermission("change_customer"));
        delete = new JButton(rb.getString("delete"));
        delete.setEnabled(UserContainer.getUser().hasPermission("delete_customer"));

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
        labels.gridwidth = 3;
        add(addressPane, labels);

        labels.gridwidth = 1;
        labels.gridy = 5;
        labels.anchor = GridBagConstraints.LINE_START;
        add(create, labels);
        fields.gridwidth = 1;
        fields.gridy = 5;
        add(update, fields);
        fields.gridx = 2;
        add(delete, fields);
    }

    private void loadData() {
        sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        customer = (Customer) sess.load(Employee.class, customerId);

        username.setText(customer.getUser().getUsername());
        firstName.setText(customer.getUser().getFirstName());
        lastName.setText(customer.getUser().getLastName());

        addresses = new AddressDetail[customer.getShippingAddresses().size() + 1];
        addresses[0] = new AddressDetail(rb.getString("address.billing"), customer.getBillingAddress(), false, true, false);
        int as = 1;
        for (Object a : customer.getShippingAddresses()) {
            addresses[as] = new AddressDetail(rb.getString("address.shipping"), (Address) a, false, true, true);
        }

        addressPane = new JScrollPane();
        for (AddressDetail a : addresses) {
            addressPane.add(a);
        }

        sess.getTransaction().commit();
    }

}
