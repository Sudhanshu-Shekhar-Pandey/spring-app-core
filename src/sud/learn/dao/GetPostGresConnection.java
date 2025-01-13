package sud.learn.dao;

import org.springframework.stereotype.Component;

@Component
public class GetPostGresConnection implements GetDBConnections {

	@Override
	public void getDBConnection() {
		System.out.println("    PostGres Connection fetched ...");
	}

}