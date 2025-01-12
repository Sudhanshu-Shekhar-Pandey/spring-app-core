package sud.learn.dao;

public class GetMySQLConnection implements GetDBConnections {

	@Override
	public void getDBConnection() {
		System.out.println("    MY SQL Connection fetched ...");
	}

}
