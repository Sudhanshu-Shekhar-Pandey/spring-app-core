package sud.learn.dao;

import org.springframework.stereotype.Component;

@Component
public class GetMySQLConnection implements GetDBConnections {

	@Override
	public void getDBConnection() {
		System.out.println("    MY SQL Connection fetched ...");
	}

}
