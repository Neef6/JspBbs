package cn.edu.nju.utils;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCUtil {
	private static Properties p = new Properties();
	private static String url;
	private static String userName;
	private static String password;
	
	static{
		try{
			URL fileurl = JDBCUtil.class.getClassLoader().getResource("/db.properties");
			String path = URLDecoder.decode(fileurl.getPath(), "utf-8");
			FileInputStream fis = new FileInputStream(path);
			
			p.load(fis);
			fis.close();
			String driver = p.getProperty("driver");
			url = p.getProperty("url");
			userName = p.getProperty("userName");
			password = p.getProperty("password");
			
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeAll(Connection con, Statement statement, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
