package sud.learn.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class GetPostGresConnection implements GetDBConnections {

	@Override
	public void getDBConnection() {
		System.out.println("    PostGres Connection fetched ...");
	}

}