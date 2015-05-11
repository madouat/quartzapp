package bf.quartzapp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings({ "unused", "resource" })
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		// Thread.sleep(30000);
		// context.close();
	}

}
