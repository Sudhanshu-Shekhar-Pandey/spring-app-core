# Spring Core (Framework) -

Generally in java, Objs can be created using 'new' keyword with Class or Interface reference. 
> Eg: List<String> list = new ArrayList<>(); 
	
Spring can help us create obj for us called spring-beans and help us manage those beans. It will do that inside Spring IOC container. 
Our goal is to achieve loose coupling, and make our code change minimum. Any new change should be handled by configurable using external config file like xml with minor changes.  

**Types of Spring IOC container ?** 

 - *Bean factory (Interface)* - For Basic small apps. 
 - *Application Context (Interface)* - For big enterprise app. Its a superset of Bean Factory. The implementation class for this interface is ClassPathXmlApplicatioContext.class  or AnnotationConfigApplicationContext.class; 

**How to tell Spring which class objects needs to be created ?**
We can tell spring in form of configuration using - **1.** xml file, **2.** Annotations. 
	
***

# Source Code - 
## Standalone Spring App with no maven (using .jar)  - 
**Requirements-** 

	IDE (Eclipse or STS), 
	Java 17 (to work with Spring 6),
	Spring 6 jars,

**References** - https://docs.spring.io/spring-framework/reference/

**Steps -** 

	1. Create a Java Project - 
	     project name = spring-app-core 
	2. Create a library for Spring Jars - 
	     BuildPath > Add Lib > User Lib > New > LibName > Add External Jar > Select Jars > Apply & Close > Finish. 
	     This will be added to Project Class path. 
	3. Create spring-config.xml file in root folder or classpath of application (recommended). Creating anywhere else is also possible but that will create coupling with that location.
	4. Create beans, and a class with main method. Configure xml for the beans. 
	5. Run the Application. > Written with [StackEdit](https://stackedit.io/).
