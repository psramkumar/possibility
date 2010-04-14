package com.mjs_svc.possibility.views;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.mjs_svc.possibility.controllers.EmployeeListTableModel;
import com.mjs_svc.possibility.util.*;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeeList extends JPanel  implements PingSubscriber {

    private ResourceBundle rb = ResourceBundle.getBundle(
            "FieldTitles",
            Locale.getDefault());
    public static String title;
    public static final boolean resizable = true;
    public static final boolean closable = true;
    public static final boolean maximizable = true;
    public static final boolean iconifiable = true;
    private JTable employeeTable;
    private JScrollPane scrollPane;
    private String query;

    public EmployeeList(String subTitle, String hql) {
        super();
        query = hql;
        title = rb.getString("employee.plural") + " - " + subTitle + query;
        setLayout(new BorderLayout());
        PingPublisher.add(this);

        employeeTable = new JTable(new EmployeeListTableModel(query));
        employeeTable.setAutoCreateRowSorter(true);
        employeeTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable t = (JTable) e.getSource();
                JInternalFrame empDetail = new JInternalFrame(
                        rb.getString("employee") + ": "
                            + t.getValueAt(t.getSelectedRow(), 1),
                        EmployeeDetail.resizable,
                        EmployeeDetail.closable,
                        EmployeeDetail.maximizable,
                        EmployeeDetail.iconifiable);
                empDetail.setContentPane(new EmployeeDetail((Integer) t.getValueAt(t.getSelectedRow(), 0)));
                empDetail.pack();
                empDetail.setVisible(true);
                t.getParent()
                        .getParent()
                        .getParent()
                        .getParent()
                        .getParent()
                        .getParent()
                        .getParent().add(empDetail);
                try {
                    empDetail.setSelected(true);
                } catch (PropertyVetoException exc) {
                    // ignore it for now.
                }
            }
        });
        scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void onPing() {
        employeeTable.setModel(new EmployeeListTableModel(query));
    }
}
