package sud.learn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("getFruitDetailsDao")
public class GetFruitDetailsDao {

	@Autowired
//	@Qualifier("getMySQLConnection")	
//	@Qualifier("getPostGresConnection")
	GetDBConnections getDBConnections;
	

	public void getFruitDetails() {
		System.out.println("Fetching Food Details . . .");

		System.out.println("Fetching DB Connection . . . ");
		getDBConnections.getDBConnection();

	}

}
