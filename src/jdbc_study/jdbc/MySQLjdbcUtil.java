package jdbc_study.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLjdbcUtil {
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try(InputStream is = ClassLoader.getSystemResourceAsStream("db.properties")){
			Properties properties = new Properties();
			properties.load(is);

			conn = DriverManager.getConnection(properties.getProperty("url"), properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return conn;
	}

}
