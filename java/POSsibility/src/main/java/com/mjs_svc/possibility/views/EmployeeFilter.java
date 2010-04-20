package com.mjs_svc.possibility.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author mscott
 */
public class EmployeeFilter extends JPanel {
    private class FilterSegment extends JPanel {
        private JComboBox field, operator, postCombinator;
        private JTextField filter;
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
        }

        @Override
        public String toString() {
            return (String) field.getSelectedItem() + " "
                    + (String) operator.getSelectedItem() + " "
                    + (String) postCombinator.getSelectedItem();
        }
    }

    private JTextField hql;
    private JScrollPane scroller;
    private EmployeeList result;

    public EmployeeFilter() {
    }
}
