package utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	/*
	 * 转json串
	 */
	public static String toString(Object o) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			return "";
		}
	}
	/*
	 * 转对象
	 */
    public static <T> T toObject(String str,Class<T> cla) {
        ObjectMapper mapper = new ObjectMapper();
        try {
                return mapper.readValue(str, cla);
              
          } catch (Exception e) {
                return null;              
          }
    }
    
    public static String returnvalues(int status,String text) {
    	return toString(new jsonInfo(status,text)); //变成jsoninfo 再变成字符串
    }
    
    public static class jsonInfo{
    	int status;
    	String text;
    	
		public jsonInfo(int status, String text) {
			super();
			this.status = status;
			this.text = text;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
    	
    }
}
