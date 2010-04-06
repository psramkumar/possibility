package com.mjs_svc.possibility.views;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class StatusBar extends JPanel {
    private JLabel status;
    private JProgressBar progress;

    public StatusBar() {
        status = new JLabel();
        progress = new JProgressBar();

        status.setVisible(false);
        progress.setVisible(false);

        setLayout(new BorderLayout());
        add(status, BorderLayout.LINE_START);
        add(progress, BorderLayout.LINE_END);

        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }

    public void setStatus(String newStatus) {
        status.setText(newStatus);
        status.setVisible(true);
    }
}
