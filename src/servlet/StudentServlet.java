package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

import Entity.Student;
import utils.DbHelper;
import utils.JsonUtil;


public class StudentServlet extends BasicServlet {
	
	String txt="";
	Student entity ;	
	int select=0;
	@Override
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String where = "";
		if(txt!=null&&txt.length()>0) { 
			switch(select) {
			case 1:
				where =" where c.id ="+txt+" ";
				break;
			case 2:
				where = "where s.sex ="+txt+" ";
				break;
			case 0:	
				where = " where s.name like '%" + txt + "%'";
				break;
			}		
		}
		req.setAttribute("select", select);     //当前选择
		req.setAttribute("txt",select==0?"'"+txt+"'":txt);    //输入框的内容
		req.setAttribute("sexstatus", entity.sexstatus);
		req.setAttribute("classrow", DbHelper.executeQuery("select * from class"));
		req.setAttribute("list", DbHelper.executeQuery("select s.* ,c.content class_name from student s inner join class c on s.classid = c.id " + where+" "+limit,Student.class));
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   DbHelper. executeUpdate("insert into student(name,sex,classid) values(?,?,?)", entity.getName(),entity.getSex(),entity.getClassid());
	   resp.getWriter().write(JsonUtil.returnvalues(1, ""));
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHelper.executeUpdate("update student set name=?,sex=?,classid=? where id=?", entity.getName(),entity.getSex(),entity.getClassid(),entity.getId());		
		resp.getWriter().write(JsonUtil.returnvalues(1, ""));
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sexstatus", entity.sexstatus);
		req.setAttribute("classrow", DbHelper.executeQuery("select * from class"));
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info",DbHelper.executeQuery("select * from student where id = ?", Integer.valueOf(entity.getId())).get(0) );
		
		add(req,resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHelper.executeUpdate("delete * from student where id=?", Integer.valueOf(entity.getId()));
		def(req,resp);
	}

	protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ids = req.getParameter("ids");
		DbHelper.executeUpdate("delete from student where id in ("+ids+")");
		def(req,resp);
	}
}
