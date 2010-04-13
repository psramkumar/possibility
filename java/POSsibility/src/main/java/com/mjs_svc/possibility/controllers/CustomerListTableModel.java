package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.Customer;
import javax.swing.table.AbstractTableModel;
import java.util.*;
import com.mjs_svc.possibility.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class CustomerListTableModel extends AbstractTableModel {

    private ResourceBundle titlesRB = ResourceBundle.getBundle(
            "FieldTitles",
            Locale.getDefault());
    private String[] columnNames = {
        titlesRB.getString("customer.id"),
        titlesRB.getString("user.username"),
        titlesRB.getString("user.firstname"),
        titlesRB.getString("user.familyname")
    };

    Object[][] data;

    public CustomerListTableModel(String hql) {
        super();
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        Vector<Object[]> customers = new Vector<Object[]>();
        for (Object cus : sess.createQuery("from Customer" + hql).list()) {
            Customer c = (Customer) cus;
            customers.add(new Object[] {
                c.getId(),
                c.getUser().getUsername(),
                c.getUser().getFirstName(),
                c.getUser().getLastName()
            });
        }
        sess.getTransaction().commit();
        data = new Object[customers.size()][getColumnCount()];
        for (int i = 0; i < customers.size(); i++) {
            data[i] = customers.get(i);
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

}
