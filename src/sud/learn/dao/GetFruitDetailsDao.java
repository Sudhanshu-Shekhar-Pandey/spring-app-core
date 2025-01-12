package sud.learn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GetFruitDetailsDao {

	@Autowired
	@Qualifier("postgresConnection")
	GetDBConnections getDBConnections;

	public void getFruitDetails() {
		System.out.println("Fetching Food Details . . .");

		System.out.println("Fetching DB Connection . . . ");
		getDBConnections.getDBConnection();

	}

}
