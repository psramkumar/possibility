package com.mjs_svc.possibility.views;

import javax.swing.*;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class SplashScreen extends JFrame {
    private JProgressBar progress;

    public SplashScreen() {
        progress = new JProgressBar(0, 4);
        progress.setStringPainted(true);
        add(progress);
        setUndecorated(true);
        setSize(200, 20);
        setLocation(200, 200);
        setVisible(true);
        requestFocus();
    }

    public void updateProgress(int step, String message) {
        progress.setValue(step);
        progress.setString(message);
    }
}
