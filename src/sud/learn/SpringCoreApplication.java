package sud.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sud.learn.beans.Banana;
import sud.learn.beans.FruitInterface;

public class SpringCoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

		// Calling Class Method
		Banana banana = context.getBean("bananaBean", Banana.class);
		System.out.println("Fruit Color  : " + banana.getFruitColor());

		// Calling Method via Interface - 
		// This will help creating objects and calling methods of any implementation class with
		// no change in java class but only changing name of Class in spring-config.xml
		FruitInterface fruit = context.getBean("fruitBean", FruitInterface.class);
		System.out.println("Fruit Name : " + fruit.getFruitName());
		
		

	}

}
