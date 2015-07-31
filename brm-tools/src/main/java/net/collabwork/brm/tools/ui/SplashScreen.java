package net.collabwork.brm.tools.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SplashScreen extends JDialog {
	private static final Log LOG = LogFactory.getLog(SplashScreen.class);

	private static SplashScreen splashScreen;

	public static void display(final boolean display) {

		if (splashScreen == null) {
			splashScreen = new SplashScreen();
		}
		splashScreen.setLocationRelativeTo(null);
		LOG.debug("Setting splashscreen visibility to " + display);

		splashScreen.setVisible(display);
		if (!display) {
			splashScreen.dispose();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SplashScreen() {
		setAlwaysOnTop(true);
		setModal(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setForeground(Color.BLACK);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.MODELESS);
		setUndecorated(true);
		setResizable(false);
		setSize(200, 200);

		JLabel lblLoading = new JLabel("Loading...");
		getContentPane().add(lblLoading, BorderLayout.CENTER);

		pack();
	}

}
