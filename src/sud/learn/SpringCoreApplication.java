package sud.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sud.learn.beans.Apple;
import sud.learn.beans.Banana;
import sud.learn.beans.FruitInterface;
import sud.learn.dao.GetFruitDetailsDao;
import sud.learn.service.FruitServiceImpl;

public class SpringCoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml", "spring-service.xml", "spring-dao.xml");

		// Calling Class Method
		Banana banana = context.getBean("bananaBean", Banana.class);
		System.out.println(" BANANA Fruit Color  : " + banana.getFruitColor());

		/* Calling Method via Interface - helps calling methods of any implementation class with 
		 * no change in java class but only changing name of Class in spring-config.xml */
		
		// constructor injection - with obj reference call
		FruitInterface fruit = context.getBean("fruitBean", FruitInterface.class);
		fruit.printFruitDetails();
		fruit.getFruitPrice();

		// setter injection - with obj reference call 
		Apple apple = context.getBean("appleBean", Apple.class);
		apple.printFruitDetails();
		apple.getFruitPrice();
		
		// -- 
		FruitServiceImpl serviceImpl = context.getBean("fruitService", FruitServiceImpl.class);
		serviceImpl.test();
		
		
		System.out.println(" \n --------------------- ENABLING ANNOTATION ------------------------------ \n ");
		GetFruitDetailsDao fruitDetails  =  context.getBean("fruitDAO", GetFruitDetailsDao.class);
		fruitDetails.getFruitDetails();
		

	}

}
