package net.collabwork.brm.tools;

import net.collabwork.brm.tools.config.AppConfig;

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

	public void launch() {
		System.out.println(appService.sayHello());
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				AppConfig.class);

		App app = ctx.getBean(App.class);
		app.launch();
	}
}
