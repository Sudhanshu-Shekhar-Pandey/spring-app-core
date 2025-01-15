package sud.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import sud.learn.dao.GetFruitDetailsDao;
import sud.learn.dao.GetMySQLConnection;
import sud.learn.dao.GetPostGresConnection;

@Configuration
//@ComponentScan(basePackages = "sud.learn.dao")
public class SpringConfig {
	
	@Bean
	public GetFruitDetailsDao getFruitDetailsDao() {
		return new GetFruitDetailsDao();
	}
	
	@Bean
	@Primary
	public GetMySQLConnection getMySQLConnection() {
		return new GetMySQLConnection();
	}
	
	@Bean
	public GetPostGresConnection getPostGresConnection() {
		return new GetPostGresConnection();
	}
	
	//For constructor injection - 
	/*
	 @Bean public AnotherBean anotherBean() { return new AnotherBean(refBean()); }
	 @Bean public RefBean refBean() { return new RefBean(); }
	*/
	
	
	
	
	
	
	
	

}
