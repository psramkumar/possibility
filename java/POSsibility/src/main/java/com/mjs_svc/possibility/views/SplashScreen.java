package com.mjs_svc.possibility.views;

import com.mjs_svc.possibility.util.Version;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class SplashScreen extends JFrame {
    private JProgressBar progress;
    private JLabel logo;
    private JLabel text;

    public SplashScreen() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        logo = new JLabel(new ImageIcon(getClass().getResource("/possibility-logo.png")));
        add(logo, BorderLayout.NORTH);

        text = new JLabel("Version: " + Version.getVersion() 
                + " build: " + Version.getBuild()
                + "; By MJS Services http://mjs-svc.com");
        add(text);

        progress = new JProgressBar(0, 4);
        progress.setStringPainted(true);
        add(progress, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(430, 100));
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    public void updateProgress(int step, String message) {
        progress.setValue(step);
        progress.setString(message);
    }
}
