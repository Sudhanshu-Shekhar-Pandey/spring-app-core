package sud.learn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

//@Component
public class GetMySQLConnection implements GetDBConnections {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/SchemaName";
	private String userName = "root";
	private String password = "admin";
	
	

	@Override
	public void getDBConnection() {
		System.out.println("    MY SQL Connection fetched ...");
		// skipping below code as jars are missing currently-
		if(true) {
	        return;
	    }
		
		try {
			
			//Load Driver 
			Class.forName(driver);
			
			//Get Connection 
			Connection connection = DriverManager.getConnection(url, userName, password);
			
			// Execute Query
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(" SELECT * FROM SchemaName.TABLE_NAME; ");
			
			while (resultSet.next()) {	
				int fruitId = resultSet.getInt(1);
				double fruitPrice = resultSet.getDouble(2);
				String fruitName = resultSet.getString(3);
				
				System.out.println(fruitId + " - " + fruitName + " : " + fruitPrice);
			}
			
			// close connection 
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
