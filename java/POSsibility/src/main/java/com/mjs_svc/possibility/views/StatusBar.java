package com.mjs_svc.possibility.views;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * A status bar to stay at the bottom of the window and keep the user up to date
 * on what's going on
 * @author Matthew Scott
 * @version $Id$
 */
public class StatusBar extends JPanel {
    private JLabel status;
    private JProgressBar progress;

    /**
     * Construct a new status bar with some text and a progress bar
     */
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

    /**
     * Change the status text
     * @param newStatus The new text
     */
    public void setStatus(String newStatus) {
        status.setText(newStatus);
        status.setVisible(true);
    }
}
