package net.collabwork.brm.tools.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class SplashScreen extends JDialog {
	private static final Log LOG = LogFactory.getLog(SplashScreen.class);

	private static SplashScreen splashScreen;

	public static synchronized void display(final boolean display) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
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
		});
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

		JXPanel loadingPanel = new JXPanel();
		loadingPanel.setBackground(Color.WHITE);
		DropShadowBorder shadow = new DropShadowBorder();
		shadow.setShadowColor(Color.BLACK);
		shadow.setShowLeftShadow(true);
		shadow.setShowRightShadow(true);
		shadow.setShowBottomShadow(true);
		shadow.setShowTopShadow(true);
		loadingPanel.setBorder(shadow);

		JLabel lblLoading = new JLabel("BRM Tools Loading...");
		lblLoading.setOpaque(false);
		lblLoading.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLoading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		loadingPanel.add(lblLoading);
		getContentPane().add(loadingPanel, BorderLayout.CENTER);

		pack();
	}

}
