# Spring Core (Framework) -

Generally in java, Objs can be created using 'new' keyword with Class or Interface reference. 
> Eg: List<String> list = new ArrayList<>(); 
	
Spring can help us create obj for us called spring-beans and help us manage those beans. It will do that inside Spring IOC container. 
Our goal is to achieve loose coupling, and make our code change minimum. Any new change should be handled by configurable using external config file like xml with minor changes.  

**Types of Spring IOC container ?** 

 - *Bean factory (Interface)* - For Basic small apps. 
 - *Application Context (Interface)* - For big enterprise app. Its a superset of Bean Factory. The implementation class for this interface is `ClassPathXmlApplicatioContext.class`  or `AnnotationConfigApplicationContext.class`. 

**How to tell Spring which class objects needs to be created ?**
We can tell spring in form of configuration using - **1.** xml file, **2.** Annotations. 

**IoC : Inversion of Control -** A principle - Instead of user having control of Object creation, give it to Spring. Hence, control is inverted for user.  
**DI : Dependency Injection -** Implementation of IoC - To achieve loose coupling, Spring container (which lays inside JVM) help manage object lifecycle (create to destroy) inside container and inject them in the class wherever needed.

**Types of Dependency Injection :**  
		**1. Constructor Injection -** using constructor to set variable values (properties). Class should have a constructor. [Recommended].  
		**2. Setter Injection -** Using getter setters to set variable values. Class should have setters. [only for optional dependencies].  
		**3. Field Injection -** Using Interface Reference [Not Recommended].  

<br>
In Java class main() method - 
			
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
		
   	ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

   	// Creating ref of interface, to methods of any implementation class which implements that interface.
   	Interface int = context.getBean("beanNameObjRef", Interface.class);
    	
   	// Creating obj of a simple class to call its methods. 
   	ClassName obj = context.getBean("beanNameObjRef", ClassName.class);


<br>
In Spring-config.xml file -

	1. Constructor Injection ---  
    	<bean id="beanNameObjRef" class="class.fully-qualified.name">
    		<constructor-arg name="id" value="1001" type="int"/> 
    		<constructor-arg name="anotherObj" ref="classRefBeanObj"/>
    	</bean>
    	<bean id="classRefBeanObj" class="examples.AnotherBean"/>
    	
    	<!-- Other ways for reference objects/dependencies --> 
    	<bean id="exampleBean" class="examples.ExampleBean">
			<!-- constructor injection using the nested ref element -->
			<constructor-arg>
				<ref bean="anotherExampleBean"/>
			</constructor-arg>
		
			<!-- constructor injection using the neater ref attribute -->
			<constructor-arg ref="yetAnotherBean"/>
			<constructor-arg type="int" value="1"/>
		</bean>
		<bean id="anotherExampleBean" class="examples.AnotherBean"/>
		<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
		
    # You can also use constructor injection in config.xml using [value], [type + value], [name + value], [index + value],  
      [name + value + type] to resolving any ambiguity if exist while initializing constructor, eg- two var with same data type.  
    # similarly for reference objs, we can use <constructor-arg ref="anotherBean"/> inside <bean> or direct initialize it   
      as <bean id="anotherBean" class="examples.AnotherBean"/> .


	2. Setter Injection ---   
		<bean id="beanNameObjRef" class="class.fully-qualified.name">
    		<property name="" value=""/> 
    		<property name="scienceObj" ref="classRefBeanObj" />
    	</bean>
    	<bean id="classRefBeanObj" class="examples.RefBeanObjClass"/>

    	<!-- Other ways for reference objects/dependencies --> 
    	<bean id="exampleBean" class="examples.ExampleBean">
			<!-- method 1 -->
			<property name="justBean">
				<bean class=examplee.justBeanExample"/>
			</property>

			<!-- method 2 -->
			<property name="beanOne">
				<ref bean="anotherExampleBean"/>
			</property>
		
			<!-- setter injection using a neater way -->
			<property name="beanTwo" ref="yetAnotherBean"/>
			<property name="integerProperty" value="1"/>
		</bean>
		<bean id="anotherExampleBean" class="examples.AnotherBean"/>
		<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
    

 - We can mix constructor-based and setter-based DIs. Rule of thumb is to use constructors for mandatory dependencies and setter methods or configuration methods for optional dependencies.
 - Dependency injection also helps us ensures that required dependencies are not null, can be assigned reasonable default values. Otherwise, not-null checks must be performed everywhere.
 - Large number of constructor arguments is a bad code implying the class has too many responsibilities and must be refactored. 
 - Circular dependency - Class A requires an instance of class B through constructor injection, and class B requires an instance of class A through constructor injection. This forces one of the beans to be injected into the other prior to being fully initialized itself (a classic chicken-and-egg scenario). IoC container detects this at runtime, and throws a `BeanCurrentlyInCreationException`
 *Not recommended solution* - one of the class should be configured by setters injection. Or use setter injection in both classes.


**Other ways of Setter/Constructor Injection (for small projects with less Classes)** - 

	a. Dependency Injection using `depends-on` tag - 
		<bean id="beanOne" class="ExampleBean" depends-on="manager,accountDao">
			<property name="manager" ref="manager" />
		</bean>
		<bean id="manager" class="ManagerBean" />
		<bean id="accountDao" class="x.y.jdbc.JdbcAccountDao" />


	b. Dependency Injection using `autowire` tag - 
		In XML - 
		<bean id="refObjA" class="class.fully-qualified.nameA">
		<bean id="someBean" class="class.fully-qualified.SomeBean" autowire="byName">
		or
		<bean id="someObj" class="class.fully-qualified.nameA">
		<bean id="refObjB" class="class.fully-qualified.nameB" autowire="byType">
		or 
		<bean id="someObj" class="class.fully-qualified.nameA">
		<bean id="refObjB" class="class.fully-qualified.nameB" autowire="constructor">


	# Limitations and Disadvantages of autowiring:
	- Dependencies added explicitely as property and constructor-arg settings will always override autowiring. 
	- You cannot autowire simple properties such as primitives, Strings, and arrays of such simple properties. 
	- If there are multiple bean defined (within the container) and those match the type specified by the setter method or constructor argument to be autowired. This may leads to exception.
	  To excluding a Bean from Autowiring set the autowire-candidate attribute of the <bean/> element to false. 



## Dependency Injection using `Annotations` (removing autowire from XML) - 
 To enable annotations, we need to add context schema xsd in config.xml file and add tag to enable annotation configuration.  
     
     <?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
		   	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		    xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
		        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> 
			 	<!-- bean definitions here -->

			 	<!--  enable annotation configuration  -->
			 	<context:annonation-config/>
			 	. . . 
		</beans>

		
Priority order to resolved dependency for @Autowire annotation is `byType` and then `byName`. 
	
1. We can remove `autowire="byType"` (or others) from xml and replace it with `@Autowire` on parameterized constructor in class (applicable for constructor injection).  
2. Similarly, same can be used on setter for setter injection. You can even use it on the property and get rid of setters.  
3. In case of  ***(a)*** multiple ref Objects of same type with different name; or    ***(b)*** having interface reference with multiple implementation class.  Here, `byName` will fail and `byType` will not able to choose which ref Obj to instantiate (with exception - `expected single matching bean but found 2`).    
To resolve this, use @Qualifier("refObjIDInXML") over ref variable.



## Dependency Injection using `Annotations` (removing bean definition from XML) -
To remove bean tag from XML we need to replace it with `@Components` annotation over bean class. Dependencies reference can be injected using `@Autowired` on setter method, or directly on property in that case setter method is not required.

	XML equivalent -
		<bean id="beanA" class="class.fully-qualified.BeanA">

	Annotation equivalent- 
		@Components
		public class BeanA {...}

		where id=class name in camelCase. Or you can explicitely provide id as @Components("beanA")

	We also have to tell Spring where to scan for beans (here components), ie the packages to scan (currently spring is still using XML) -  
		<context:component-scan base-package="sud.learn.spring.bean", "sud.learn.spring.services" />  
		
	The use of <context:component-scan> implicitly enables the functionality of <context:annotation-config>, and not to be included. 



## Annotation based configuration (removing xml config file) - 
Spring can get required configuration using Java annotation and thus we can removed xml configuration file.
 
A configuration class in spring should be annotated with `@Configuration`. For package scanning `@ComponentScan(basePackages = "abc")` should be used.

XML equivalent -

        <context:component-scan base-package="org.example", "org.services" />

Annotation equivalent-  

	@Configuration
	@ComponentScan(basePackages = "org.example", "org.services")
	public class AppConfig  {
		// ...
	}

To create context object for beans, in main method use - 

	ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

All beans must be annotated with `@Component` annotation. By default, bean id will be class name in camel-Case but can also be specified explicitly - 

	@Component("myNewBeanName")

For injecting dependency, use `@Autowired` annotation. By default, your bean id will be class name in camel-Case. 

If there are multiple bean of same type, or using interface reference with more than one implementation classes; Spring will get confused about which bean object to be instantiated. 
To specify which bean to instantiate, use 	`@Qualifier("refBeanCamelCase")`.



***

# Source Code - 
## Standalone Spring App with no maven (using .jar)  - 
**Requirements-** 

	Knowledge of Java Core,
	IDE (Eclipse or STS), 
	Java 17 (to work with Spring 6),
	Spring 6 jars.

**References** - https://docs.spring.io/spring-framework/reference/

**Steps -** 

	1. Create a Java Project - 
	     project name = spring-app-core 
	2. Create a library for Spring Jars -
	    Jars can be downloaded from Maven Central repo.  
	    BuildPath > Add Lib > User Lib > New > LibName > Add External Jar > Select Jars > Apply & Close > Finish. 
	    This will be added to Project Class path. 
	3. Create spring-config.xml file in root folder or classpath of application (recommended). Creating anywhere else is also possible but that will create coupling with that location.
	4. Create beans, and a class with main method. Configure xml for the beans. 
	5. ## Run the Application.
	6. Constructor Injection -------
		Add private dependencies in Orange.class and add public constructor to initialize them. 
	7. Define constructor dependencies in config file - 
		<bean id="fruitBean" class="sud.learn.beans.Orange">
			<constructor-arg name="fruitName" value="RED-ORANGE" type="String"/>
			<constructor-arg name="fruitId" value="1001" type="int"/>
			<constructor-arg name="withSeeds" value="TRUE" type="boolean"/>
		</bean> 
	8. Instantiate Orange class and print all the properties. 
    	FruitInterface fruit = context.getBean("fruitBean", FruitInterface.class);
    	fruit.printFruitDetails();
	9. Setter Injection ----------- 
	    Add a new Apple.class with properties and add setters for them. 
	10. Define setter dependencies in config file -
		<bean id="appleBean" class="sud.learn.beans.Apple">
			<property name="fruitName" value="Green-Apple"></property>
			<property name="fruitId" value="1002"></property>
			<property name="withSeeds" value="TRUE"></property>
		</bean>
	11. Instantiate Orange class and print all its properties.
	12. ## Run the Application.
	13. ## Dependency Injection with reference -->>
		Add FruitService Interface with impl class in a new package 'service'.
	14. Create a new 'spring-service.xml' config file for service class. Register bean of FruitService in that new xml config file. 
	15. Create ref of FruitService in Apple.class with setter and in Orange.class with constructor; and call service methods. 
	16. Add a property with ref for FruitService in Apple bean tag of 'spring-bean.xml' config. 
    	Also add a constructor-arg tag with ref for FruitService in Orange bean tag in same file. 
    	# Note that bean-ref and actual-bean is defined in two different xml config files. This can be achieved.
	17. Add both the config to main methods and call bean methods - 
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml", "spring-service.xml" );
	18. ## Run the Application ------->
	19. ## Other ways of Dependency Injection - `depends-on`, `autowire`   -->>
    	Create a new 'PriceService.class' which will provide cost price and selling price of fruit. 
    20. Add its dependency in 'FruitServiceImpl.class' along with setters and constructor (one of them active at a time).
    21. Call 'priceService.getCostPrice()'' from inside of any method of 'FruitServiceImpl.class'. Also write a test method. 
    22. Define 'PriceService.class' bean in `spring-service.xml`. 
    23. Add a property for PriceService in  `FruitServiceImpl` bean tag using `depends-on`. 
    24. Test your code - 
    25. Modify your xml to inject dependency 'autowire=byName' , 'autowire=byType' , 'autowire=constructor' . 
    26. Call any method of Service Class in main class. 
    27. ## Run the Application ------->    
    28. Replacing with @autowire annotation -
    	Add context schema in xml - 
    	    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  
    	    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"   
    29. Enable annotations in xml. Add tag to enable annotation configuration -   
    	    <context:annotation-config/>
    30. Create new interface in DAO package - `GetDBConnections.class` with implementation class as `GetMySQLConnection.class`. 
    31. Create a new class `GetFruitDetailsDao.class` with a ref of the interface and setters. 
    32. Define the beans in xml with `id` and `class` with autowire="byType". 
    		<bean id="fruitDAO" class="sud.learn.dao.GetFruitDetailsDao" autowire="byType"></bean>
    	 	<bean id="mysqlConnection" class="sud.learn.dao.GetMySQLConnection"></bean>
    33. Test your code by calling a method of `GetFruitDetailsDao.class` which is then calling a method of `GetMySQLConnection.class`. 
    34. Now, replace xml tag <autowire="byType"> + <setter in java> ---(with)-->  
    	@Autowired (org.springframework.beans.factory.annotation.Autowired) on reference var of `GetFruitDetailsDao.class`.  
    35. Now, add another implementation class `GetPostGresConnection.class` and define bean in xml.  
    	    <bean id="postgresConnection" class="sud.learn.dao.GetPostGresConnection"></bean>
    36. Test your code to get exception - "expected single matching bean but found 2: mysqlConnection,postgresConnection".  
    	Reason - @autowire annotation tries to resolved dependency `byType` if not found then `byName`. IOC will not be able to understand which class to  
    	instantiate if multiple bean with same type found. Hence exception. Note that we are using interface ref to call the method.  
    37. Use @Qualifier("varName") to create required bean instance. Eg - `@Qualifier("postgresConnection")`  
    38. ## Run the Application ------->  
    39. ## Replacing bean definition from XML to annotations --> 
    	Add component scan tag in xml and remove all the bean definitions. 
    	<context:component-scan base-package="sud.learn.spring.services" />
    40. Replace bean tags with equivalent annotations. 
    	XML equivalent -
			<bean id="fruitService" class="sud.learn.service.FruitServiceImpl"></bean>

		Annotation equivalent- 
			@Component
			public class FruitServiceImpl{...}

			where id=class name in camelCase. Or you can explicitely provide id as @Components("beanA")  

    41. For injecting dependency, add @Autowired on property or use constructor. Setter method is not required if used over property. 
			@Autowired
			PriceService priceService;
    42. Reference bean should also be made component using @Component. 
    43. ## Run the Application -------> 


<br>

**Steps to use annotation based configuration -**   
	1. Delete xml config file. Instead we will create java Config class to9 be used by SpringIOC for bean creation.   
	2. Create a configuration `SpringConfig.class` which will have `@Configuration` annotation.  
	3. For component scanning use annotation - `@ComponentScan(basePackages = "sud.learn.dao")`  
	4. Mark all the beans with `@Component` annotation. This is equivalent to creating `<bean>` tag in xml. Instead of default bean name as className in camelCase, we can explicitly define name as `@Component("fruitDetailsDao")`.  
	5. Add `@Autowired` for reference variable to inject dependency. Use this on reference variable.  
	6. Use `@Qualifier("getPostGresConnection")` to specify which bean to instantiate in case of multiple bean of same type found, or using interface reference having multiple implementation class.   
	7. ## Run the Application ------->  
	8. 
 
 
    
