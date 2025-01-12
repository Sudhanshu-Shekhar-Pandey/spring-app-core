package sud.learn.dao;

public class GetPostGresConnection implements GetDBConnections {

	@Override
	public void getDBConnection() {
		System.out.println("    PostGres Connection fetched ...");
	}

}