package net.collabwork.brm.tools;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import net.collabwork.brm.tools.config.AppConfig;
import net.collabwork.brm.tools.presenters.MainPresenter;
import net.collabwork.brm.tools.ui.SplashScreen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 * 
 */
@Component
public class App {
	private static final Log LOG = LogFactory.getLog(App.class);

	@Autowired
	private AppService appService;

	@Autowired
	private PunchService punchService;

	@Autowired
	private MainPresenter presenter;

	public void launch() {
		try {
			LOG.debug("launching app...");

			presenter.display(true);

			SplashScreen.display(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (Exception e) {
					LOG.error("No Windows L&F", e);
				}

				SplashScreen.display(true);
				Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

					@Override
					public void uncaughtException(Thread t, Throwable e) {
						JOptionPane.showMessageDialog(null, "Uncaugh error: " + e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
						LOG.error("uncaught exception", e);
					}
				});

				ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

				App app = ctx.getBean(App.class);
				app.launch();
			}
		});

	}
}
