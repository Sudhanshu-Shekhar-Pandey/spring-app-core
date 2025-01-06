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

	**1. Constructor Injection -** 
    	<bean id="beanNameObjRef" class="class.fully-qualified.name">
    		<constructor-arg name="id" value="1001" type="int"/> 
    		<constructor-arg name="anotherObj" ref="classRefBeanObj"/>
    	</bean>
    	<bean id="classRefBeanObj" class="examples.AnotherBean"/>

    	# You can also use constructor injection in config.xml using [value], [type + value], [name + value], [index + value], [name + value + type] to resolving any ambiguity if exist while initializing constructor, eg- two var with same data type. 
    	# similarly for reference objs, we can use <constructor-arg ref="anotherBean"/> inside <bean> or direct initialize it as <bean id="anotherBean" class="examples.AnotherBean"/> .


	2. Setter Injection - 
		<bean id="beanNameObjRef" class="class.fully-qualified.name">
    		<property name="" value=""/>
    		<property name="scienceObj" ref="classRefBeanObj" />
    	</bean>
    

 - We can mix constructor-based and setter-based DIs. Rule of thumb is to use constructors for mandatory dependencies and setter methods or configuration methods for optional dependencies.
 - Dependency injection also helps us ensures that required dependencies are not null, can be assigned reasonable default values. Otherwise, not-null checks must be performed everywhere.
 - Large number of constructor arguments is a bad code implying the class has too many responsibilities and must be refactored. 
 - Circular dependency - Class A requires an instance of class B through constructor injection, and class B requires an instance of class A through constructor injection. This forces one of the beans to be injected into the other prior to being fully initialized itself (a classic chicken-and-egg scenario). IoC container detects this at runtime, and throws a `BeanCurrentlyInCreationException`
 *Not recommended solution* - one of the class should be configured by setters injection. Or use setter injection in both classes.


	
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
	5. Run the Application.
	6. **Constructor Injection -** 
	7. Add private dependencies in Orange.class and add public constructor to initialize them. 
	8. Define constructor dependencies in config file - 
		*<!-- for constructor injection -->
		<bean id="fruitBean" class="sud.learn.beans.Orange">
			<constructor-arg name="fruitName" value="RED-ORANGE" type="String"/>
			<constructor-arg name="fruitId" value="1001" type="int"/>
			<constructor-arg name="withSeeds" value="TRUE" type="boolean"/>
    </bean>*
	9. Instantiate Orange class and print all the properties. 
    	*FruitInterface fruit = context.getBean("fruitBean", FruitInterface.class);
		  fruit.printFruitDetails();*
	10. Setter Injection - 
	    Add a new Apple.class with properties and add setters for them. 
	11. Define setter dependencies in config file -
	  <!-- for setter injection -->
    <bean id="appleBean" class="sud.learn.beans.Apple">
    	<property name="fruitName" value="Green-Apple"></property>
    	<property name="fruitId" value="1002"></property>
    	<property name="withSeeds" value="TRUE"></property>
    </bean>
	12. Instantiate Orange class and print all its properties.
	13. **Run the Application.**
