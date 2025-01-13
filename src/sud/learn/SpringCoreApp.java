package sud.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sud.learn.config.SpringConfig;
import sud.learn.dao.GetFruitDetailsDao;

/**
 * Another class with main for annotation implementation.
 */
public class SpringCoreApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		GetFruitDetailsDao getFruitDetailsDao = context.getBean("fruitDetailsDao", GetFruitDetailsDao.class);
		getFruitDetailsDao.getFruitDetails();
		
		
		

		context.close();

	}

}
