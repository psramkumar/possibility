package com.mjs_svc.possibility.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author mscott
 */
public class EmployeeFilter extends JPanel {

    private JTextField hql;
    private JScrollPane filterScroller;
    private JButton addFilter, runFilters;
    private JPanel filterPanel;
    private EmployeeList result;
    Vector<FilterSegment> filters;

    public static String title;
    public static boolean resizable = true;
    public static boolean closable = true;
    public static boolean maximizable = true;
    public static boolean iconifiable = true;

    private class FilterSegment extends JPanel {
        private JComboBox field, operator, postCombinator;
        private JTextField filter;
        JButton close;
        private String objName = "employee";

        public FilterSegment() {
            field = new JComboBox(new String[] {
                "user.username",
                "user.firstname",
                "user.lastname",
                "id",
                "position.name"
            });
            operator = new JComboBox(new String[] {
                "=",
                ">=",
                "<=",
                "!=",
                ">",
                "<",
                "like"
            });
            postCombinator = new JComboBox(new String[] {
                "",
                "and",
                "or",
                "and not",
                "or not"
            });
            filter = new JTextField();
            close = new JButton("XXXRemove this filter");
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filters.remove((FilterSegment)((JButton) e.getSource()).getParent());
                    drawFilters();
                }
            });

            add(field);
            add(operator);
            add(filter);
            add(postCombinator);
            add(close);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        @Override
        public String toString() {
            return (String) field.getSelectedItem() + " "
                    + (String) operator.getSelectedItem() + " "
                    + (String) postCombinator.getSelectedItem();
        }
    }

    public EmployeeFilter() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        filters = new Vector<FilterSegment>();

        filters.add(new FilterSegment());

        filterScroller = new JScrollPane();
        filterScroller.setAlignmentX(CENTER_ALIGNMENT);
        drawFilters();
        filterScroller.add(filterPanel);
        add(filterScroller);

        runFilters = new JButton("XXXRun filters");
        runFilters.setAlignmentX(RIGHT_ALIGNMENT);
        runFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer query = new StringBuffer();
                for (FilterSegment f : filters) {
                    query.append(f.toString() + " ");
                }
                hql.setText(query.toString());
                result.setQuery(query.toString());
                result.onPing();
            }
        });
        add(runFilters);

        addFilter = new JButton("XXXAdd new filter");
        addFilter.setAlignmentX(RIGHT_ALIGNMENT);
        add(addFilter);

        hql = new JTextField();
        hql.setAlignmentX(CENTER_ALIGNMENT);
        add(hql);

        result = new EmployeeList("XXXFilter", "");
        result.setAlignmentX(CENTER_ALIGNMENT);
        add(result);
    }

    private void drawFilters() {
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setAlignmentX(CENTER_ALIGNMENT);
        for (FilterSegment f : filters) {
            f.setAlignmentX(CENTER_ALIGNMENT);
            filterPanel.add(f);
        }
    }
}
