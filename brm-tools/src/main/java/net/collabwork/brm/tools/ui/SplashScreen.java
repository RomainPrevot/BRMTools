package net.collabwork.brm.tools.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class SplashScreen extends JDialog {

    private static SplashScreen splashScreen;

    public static void display(final boolean display) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                if (splashScreen == null) {
                    splashScreen = new SplashScreen();
                }
                splashScreen.setLocationRelativeTo(null);
                System.out.println("settign splashScreen visible: " + display);
                splashScreen.setVisible(display);
            }
        });

    }

    /**
     * Create the dialog.
     */
    public SplashScreen() {
        setAlwaysOnTop(true);
        setModal(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setResizable(false);
        setBounds(100, 100, 200, 120);

        JLabel lblLoading = new JLabel("Loading...");
        getContentPane().add(lblLoading, BorderLayout.CENTER);
    }

}
