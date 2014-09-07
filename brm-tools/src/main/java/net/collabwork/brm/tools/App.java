package net.collabwork.brm.tools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import net.collabwork.brm.tools.config.AppConfig;
import net.collabwork.brm.tools.ui.SplashScreen;
import net.collabwork.brm.tools.ui.TestDialog;

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

    @Autowired
    private AppService appService;

    @Autowired
    private PunchService punchService;

    public void launch() {
        try {
            TestDialog dialog = new TestDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            SplashScreen.display(false);

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {

                SplashScreen.display(true);

                ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

                App app = ctx.getBean(App.class);
                app.launch();
            }
        });

    }
}
