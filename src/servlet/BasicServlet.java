package servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DbHelper;
public class BasicServlet extends GenericServlet {
	protected String limit;
	public static int max = 5;
	
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		req.setCharacterEncoding("utf-8");
	
		//属性注入
		Enumeration<String> names=req.getParameterNames();
		Object o=this;
		//直接在serlvet内定义每个变量  还是 通过entity属性出路
		try {
			Field f=getClass().getDeclaredField("entity");
			o=f.getType().newInstance();
			f.setAccessible(true);
			f.set(this, o);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		while(names.hasMoreElements()) {
			String name=names.nextElement();
			setValue(name, req, o);
			if(!o.equals(this))
			setValue(name, req, this);
		}
		Integer pageno=1;
		String p =req.getParameter("pageno");
		if(p !=null) {
			pageno = Integer.valueOf(p);			
		}
		if(pageno==null||pageno<1)pageno=1;
		int prev = 1;
		int next = 2;
		limit=" limit "+(pageno-1)*max+","+max;
		   
		    prev = pageno>1?pageno-1:1;
		    next = pageno + 1;
		    req.setAttribute("prev", prev);
		    req.setAttribute("next", next);
		//方法转接
		String cmd=req.getParameter("cmd");
		if(cmd==null||cmd.length()==0) {
			//默认执行某方法
			def(req, resp);
		}else {
			//执行cmd内容名方法
			try {
				Method m=this.getClass().getDeclaredMethod(cmd,HttpServletRequest.class, HttpServletResponse.class);
				m.setAccessible(true);
				m.invoke(this, req,resp);
			} catch (Exception e) {
				//e.printStackTrace();
				def(req, resp);
			}
		}
		
	}

	
	public void setValue(String name,HttpServletRequest req,Object target) {
		try {
			Field f=target.getClass().getDeclaredField(name);
			f.setAccessible(true);
			if(f.getType().equals(int.class)||f.getType().equals(Integer.class)) {
				f.set(target, Integer.valueOf(req.getParameter(name)));
			}else if(f.getType().equals(Double.class)||f.getType().equals(double.class)) {
				f.set(target, Double.valueOf(req.getParameter(name)));
			}else {
				f.set(target,req.getParameter(name));
			}
		} catch (Exception e) {				
//			e.printStackTrace();
		}
	}
}
