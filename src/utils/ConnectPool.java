package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Vector;

public class ConnectPool {
	
	
	private Vector<Connect> list  = new Vector<>();
	
	public ConnectPool(int count) {
		for(int i = 0;i<count;i++) {
			list.add(new Connect());
		}
	}
	
	public Connect getFreeConnection() {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).status == 1) continue;
			if(list.get(i).conn == null) {
				try {
					list.get(i).conn = getConnection();
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
			list.get(i).status =1;
			return list.get(i);
		}
		return null;
	}
	
	public void closeAll(){
		try{
		for(int i=0;i<list.size();i++){
			list.get(i).conn.close();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String url;
	private static String name;
	private static String pass;
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(sqlstr, "root", "123");  //获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}  
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("db.properties"));
		} catch (Exception e) {	
			e.printStackTrace();
		}
		url = p.getProperty("url", "jdbc:mysql://localhost:3306/hotel");
		name = p.getProperty("name","root");
	    pass = p.getProperty("pass", "123");
	}
	private static Connection getConnection()throws Exception{
		return DriverManager.getConnection(url,name,pass);
	}
	
}
