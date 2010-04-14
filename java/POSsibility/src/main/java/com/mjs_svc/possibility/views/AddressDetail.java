package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.controllers.AddressDetailController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.mjs_svc.possibility.models.Address;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class AddressDetail extends JPanel {

    private Address address;
    private JLabel lAddress1, lAddress2, lName, lOrganization, lPhone, lCity,
            lState, lZipCode, lCountry;
    private JTextField address1, address2, name, organization, phone, city,
            state, zipCode, country;
    private JButton create, update, delete;
    private ResourceBundle rb = ResourceBundle.getBundle(
            "FieldTitles", Locale.getDefault());

    public AddressDetail(String title, Address a, boolean enableCreate,
            boolean enableUpdate, boolean enableDelete) {
        new AddressDetail(a, enableCreate, enableUpdate, enableDelete);
        setBorder(BorderFactory.createTitledBorder(rb.getString("address")
                + " - " + address.getId() + ": " + title));
    }

    public AddressDetail(Address a, boolean enableCreate, boolean enableUpdate,
            boolean enableDelete) {
        address = a;

        lAddress1 = new JLabel(rb.getString("address.address1"));
        lAddress2 = new JLabel(rb.getString("address.address2"));
        lName = new JLabel(rb.getString("address.name"));
        lOrganization = new JLabel(rb.getString("address.organization"));
        lPhone = new JLabel(rb.getString("address.phone"));
        lCity = new JLabel(rb.getString("address.city"));
        lState = new JLabel(rb.getString("address.state"));
        lZipCode = new JLabel(rb.getString("address.zipcode"));
        lCountry = new JLabel(rb.getString("address.country"));

        address1 = new JTextField();
        address2 = new JTextField();
        name = new JTextField();
        organization = new JTextField();
        phone = new JTextField();
        city = new JTextField();
        state = new JTextField();
        zipCode = new JTextField();
        country = new JTextField();

        loadData();

        create = new JButton(rb.getString("create"));
        create.setEnabled(enableCreate);
        update = new JButton(rb.getString("update"));
        update.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                address = AddressDetailController.updateAddress(
                        address.getId(),
                        name.getText(),
                        organization.getText(),
                        phone.getText(),
                        address1.getText(),
                        address2.getText(),
                        city.getText(),
                        state.getText(),
                        zipCode.getText(),
                        country.getText());
                loadData();
            }
        });
        update.setEnabled(enableUpdate);
        delete = new JButton(rb.getString("delete"));
        delete.setEnabled(enableDelete);

        setBorder(BorderFactory.createTitledBorder(rb.getString("address")
                + " - " + address.getId()));

        setLayout(new GridBagLayout());
        GridBagConstraints labels = new GridBagConstraints();
        GridBagConstraints fields = new GridBagConstraints();
        labels.weightx = 0.5;
        labels.gridx = 0;
        labels.ipadx = 2;
        labels.gridwidth = 1;
        labels.anchor = GridBagConstraints.LINE_END;
        fields.weightx = 1;
        fields.gridx = 1;
        fields.gridwidth = 2;
        fields.fill = GridBagConstraints.HORIZONTAL;

        labels.gridy = 0;
        add(lName, labels);
        fields.gridy = 0;
        add(name, fields);

        labels.gridy = 1;
        add(lOrganization, labels);
        fields.gridy = 1;
        add(organization, fields);

        labels.gridy = 2;
        add(lPhone, labels);
        fields.gridy = 2;
        add(phone, fields);

        labels.gridy = 3;
        add(lAddress1, labels);
        fields.gridy = 3;
        add(address1, fields);

        labels.gridy = 4;
        add(lAddress2, labels);
        fields.gridy = 4;
        add(address2, fields);

        labels.gridy = 5;
        add(lCity, labels);
        fields.gridy = 5;
        add(city, fields);

        labels.gridy = 6;
        add(lState, labels);
        fields.gridy = 6;
        add(state, fields);

        labels.gridy = 7;
        add(lZipCode, labels);
        fields.gridy = 7;
        add(zipCode, fields);

        labels.gridy = 8;
        add(lCountry, labels);
        fields.gridy = 8;
        add(country, fields);

        labels.anchor = GridBagConstraints.LINE_START;
        labels.gridy = 9;
        add(create, labels);
        fields.gridwidth = 1;
        fields.gridy = 9;
        add(update, fields);
        fields.gridx = 2;
        add(delete, fields);
    }

    private void loadData() {
        address1.setText(address.getAddress1());
        address2.setText(address.getAddress2());
        name.setText(address.getName());
        organization.setText(address.getOrganization());
        phone.setText(address.getPhone());
        city.setText(address.getCity());
        state.setText(address.getState());
        zipCode.setText(address.getZipCode());
        country.setText(address.getCountry());
    }
}
