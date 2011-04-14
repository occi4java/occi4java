package libvirt.occi;
import occi.http.occiApi;
import occi.infrastructure.injection.Injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClass {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"resources/beans.xml");
		BeanFactory factory = context;
		Injection test = (Injection) factory
				.getBean("Injection");
		occiApi occi = new occiApi();
		try {
			occi.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}