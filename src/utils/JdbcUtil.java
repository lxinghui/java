package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	 //����ȫ�ֱ���
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;
    public static Connection conn = null;
    public Statement stmt = null; // ����Statement�����ʵ��
	public ResultSet rs = null; // ����ResultSet�����ʵ��
//   //��ȡ�����ļ����ݣ����ھ�̬������о��У���Ϊֻ��Ҫ����һ�ξͿ�����
//    static{
//        try{
//            Properties props = new Properties();
//            //ʹ����·�����صķ�ʽ��ȡ�����ļ�
//            //��ȡ���ļ�·��Ҫ�ԡ�/����ͷ����Ϊ���ʹ�á�.���Ļ��������𵽷�������֮����Ҳ����ļ��ˣ�ʹ�á�/����ͷ��ֱ�Ӷ�λ�����̵�src·����
//            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
//            //���������ļ�
//            props.load(in);
//            //��ȡ�����ļ���Ϣ
//            url = props.getProperty("url");
//            user = props.getProperty("user");
//            password = props.getProperty("password");
//            driverClass = props.getProperty("driverClass");
//            //ע����������
//            Class.forName(driverClass);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("��������ע��ʧ�ܣ�����");
//        }
//    }
//    
//    //��ȡ���Ӷ���Connection
//    public static Connection getConnection(){
//        try{
//            return DriverManager.getConnection(url,user,password);
//        }catch(SQLException e){
//            e.printStackTrace();
//            //�ܳ�����ʱ�쳣
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
		}//������
    	return conn;
    }
	/*
	 * ���ܣ�ִ�в�ѯ���
	 */
	public ResultSet executeQuery(String sql) {
		try { // ��׽�쳣
			conn = getConnection(); // ����getConnection()��������Connection�����һ��ʵ��conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // ����쳣��Ϣ
		}
		return rs; // ���ؽ��������
	}

	/*
	 * ����:ִ�и��²���
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();					//����getConnection()��������Connection�����һ��ʵ��conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);		//ִ�и��²���
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