package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	 //定义全局变量
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;
    public static Connection conn = null;
    public Statement stmt = null; // 声明Statement对象的实例
	public ResultSet rs = null; // 声明ResultSet对象的实例
//   //读取配置文件内容，放在静态代码块中就行，因为只需要加载一次就可以了
//    static{
//        try{
//            Properties props = new Properties();
//            //使用类路径加载的方式读取配置文件
//            //读取的文件路径要以“/”开头，因为如果使用“.”的话，当部署到服务器上之后就找不到文件了，使用“/”开头会直接定位到工程的src路径下
//            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
//            //加载配置文件
//            props.load(in);
//            //读取配置文件信息
//            url = props.getProperty("url");
//            user = props.getProperty("user");
//            password = props.getProperty("password");
//            driverClass = props.getProperty("driverClass");
//            //注册驱动程序
//            Class.forName(driverClass);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("驱动程序注册失败！！！");
//        }
//    }
//    
//    //获取连接对象Connection
//    public static Connection getConnection(){
//        try{
//            return DriverManager.getConnection(url,user,password);
//        }catch(SQLException e){
//            e.printStackTrace();
//            //跑出运行时异常
//            throw new RuntimeException();
//        }
//    }
    
    public static Connection getConnection() {
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//加驱动
    	return conn;
    }
	/*
	 * 功能：执行查询语句
	 */
	public ResultSet executeQuery(String sql) {
		try { // 捕捉异常
			conn = getConnection(); // 调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // 输出异常信息
		}
		return rs; // 返回结果集对象
	}

	/*
	 * 功能:执行更新操作
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();					//调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);		//执行更新操作
		} catch (SQLException ex) {
			result = 0;
		}
		return result;
	}
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}