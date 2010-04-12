package com.mjs_svc.possibility.views;

import java.awt.*;
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

    public AddressDetail(String title, Address a, boolean enableCreate, boolean enableUpdate, boolean enableDelete) {
        new AddressDetail(a, enableCreate, enableUpdate, enableDelete);
        setBorder(BorderFactory.createTitledBorder(rb.getString("address")
                + " - " + address.getId() + ": " + title));
    }

    public AddressDetail(Address a, boolean enableCreate, boolean enableUpdate, boolean enableDelete) {
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

        address1 = new JTextField(address.getAddress1());
        address2 = new JTextField(address.getAddress2());
        name = new JTextField(address.getName());
        organization = new JTextField(address.getOrganization());
        phone = new JTextField(address.getPhone());
        city = new JTextField(address.getCity());
        state = new JTextField(address.getState());
        zipCode = new JTextField(address.getZipCode());
        country = new JTextField(address.getCountry());

        create = new JButton("XXXcreate");
        create.setEnabled(enableCreate);
        update = new JButton("XXXupdate");
        update.setEnabled(enableUpdate);
        delete = new JButton("XXXdelete");
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
        add(lAddress1, labels);
        fields.gridy = 2;
        add(address1, fields);

        labels.gridy = 3;
        add(lAddress2, labels);
        fields.gridy = 3;
        add(address2, fields);

        labels.gridy = 4;
        add(lCity, labels);
        fields.gridy = 4;
        add(city, fields);

        labels.gridy = 5;
        add(lState, labels);
        fields.gridy = 5;
        add(state, fields);

        labels.gridy = 6;
        add(lZipCode, labels);
        fields.gridy = 6;
        add(zipCode, fields);

        labels.gridy = 7;
        add(lCountry, labels);
        fields.gridy = 7;
        add(country, fields);

        labels.anchor = GridBagConstraints.LINE_START;
        labels.gridy = 8;
        add(create, labels);
        fields.gridwidth = 1;
        fields.gridy = 8;
        add(update, fields);
        fields.gridx = 2;
        add(delete, fields);
    }
}
