package com.mjs_svc.possibility.views;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.mjs_svc.possibility.controllers.EmployeesListTableModel;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class EmployeesList extends JPanel {
    private ResourceBundle panelRB = ResourceBundle.getBundle(
            "PanelTexts",
            Locale.getDefault());
    public static String title;
    public static final boolean resizable = true;
    public static final boolean closable = true;
    public static final boolean maximizable = true;
    public static final boolean iconifiable = true;

    private JTable employeeTable;
    private JScrollPane scrollPane;

    public EmployeesList(String subTitle, String hql) {
        super();
        title = panelRB.getString("employeeslist") + " - " + subTitle + hql;
        setLayout(new BorderLayout());

        employeeTable = new JTable(new EmployeesListTableModel(hql));
        scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
