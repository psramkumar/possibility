package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;
import com.mjs_svc.possibility.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeeListTableModel extends AbstractTableModel {

    private ResourceBundle titlesRB = ResourceBundle.getBundle(
            "FieldTitles",
            Locale.getDefault());
    private String[] columnNames = {
        titlesRB.getString("employee.id"),
        titlesRB.getString("user.username"),
        titlesRB.getString("user.firstname"),
        titlesRB.getString("user.familyname"),
        titlesRB.getString("user.email"),
        titlesRB.getString("employee.position"),
        titlesRB.getString("employee.address"),
        titlesRB.getString("employee.isclockedin"),
        titlesRB.getString("employee.troubletickets.count")
    };
    
    Object[][] data;
    
    public EmployeeListTableModel(String hql) {
        super();
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        Vector<Object[]> employees = new Vector<Object[]>();
        for (Object emp : sess.createQuery("from Employee" + hql).list()) {
            Employee e = (Employee) emp;
            employees.add(new Object[] {
                e.getId(),
                e.getUser().getUsername(),
                e.getUser().getFirstName(),
                e.getUser().getLastName(),
                e.getUser().getEmail(),
                e.getPosition().getName(),
                e.getAddress() instanceof Address, //XXX set tooltiptext later
                e.getIsClockedIn(),
                e.getTroubleTickets().size()
            });
        }
        sess.getTransaction().commit();
        data = new Object[employees.size()][getColumnCount()];
        for (int i = 0; i < employees.size(); i++) {
            data[i] = employees.get(i);
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
