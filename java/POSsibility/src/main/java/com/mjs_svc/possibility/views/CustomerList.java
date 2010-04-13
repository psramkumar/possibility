package com.mjs_svc.possibility.views;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.mjs_svc.possibility.controllers.CustomerListTableModel;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;

/**
 *
 * @author Matthew Scott
 * @version $Id: EmployeesList.java 23 2010-04-11 20:07:09Z matthew.joseph.scott $
 */
public class CustomerList extends JPanel {

    private ResourceBundle rb = ResourceBundle.getBundle(
            "FieldTitles",
            Locale.getDefault());
    public static String title;
    public static final boolean resizable = true;
    public static final boolean closable = true;
    public static final boolean maximizable = true;
    public static final boolean iconifiable = true;
    private JTable customerTable;
    private JScrollPane scrollPane;

    public CustomerList(String subTitle, String hql) {
        super();
        title = rb.getString("customer.plural") + " - " + subTitle + hql;
        setLayout(new BorderLayout());

        customerTable = new JTable(new CustomerListTableModel(hql));
        customerTable.setAutoCreateRowSorter(true);
        customerTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable t = (JTable) e.getSource();
                JInternalFrame empDetail = new JInternalFrame(
                        rb.getString("customer") + ": "
                            + t.getValueAt(t.getSelectedRow(), 1),
                        CustomerDetail.resizable,
                        CustomerDetail.closable,
                        CustomerDetail.maximizable,
                        CustomerDetail.iconifiable);
                empDetail.setContentPane(new CustomerDetail((Integer) t.getValueAt(t.getSelectedRow(), 0)));
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
        scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
