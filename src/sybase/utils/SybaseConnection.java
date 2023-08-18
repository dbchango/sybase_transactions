package sybase.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SybaseConnection {
	
	
	private static Tool util_tool = new Tool();
	
	private static String resourceName = "myconf.properties";
	
	private static Connection connection = null;
	
	private static Properties props;;
	
	private static String connection_string = "";
	
	
	static {
		
		try {
			props = util_tool.readConfigFile(resourceName);
			connection_string = "jdbc:" + props.get("database.type")
			+ ":user=" + props.get("database.user")
			+ ";password=" + props.get("database.password")
			+ ";Server=" + props.get("database.server")
			+ ";Port=" + props.get("database.port");
			
			Class.forName("cdata.jdbc.sybase.SybaseDriver");
			
			connection = DriverManager.getConnection(connection_string);
			
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
