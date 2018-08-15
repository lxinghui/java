package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Class;
import utils.DbHelper;

public class ClassServlet extends BasicServlet{

	Class entity;
	@Override
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", DbHelper.executeQuery("select * from class",Class.class));
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   DbHelper.executeUpdate("insert into class(content) values(?)", entity.getContent());
	   resp.sendRedirect("index.jsp");
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHelper.executeUpdate("update class set content=? where id=?", entity.getContent(),entity.getId());
		resp.sendRedirect("index.jsp");
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("classrow", DbHelper.executeQuery("select * from class"));
		req.getRequestDispatcher("/Class/classedit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info",DbHelper.executeQuery("select * from class where id = ?", Integer.valueOf(entity.getId())).get(0) );
		add(req,resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHelper.executeUpdate("delete * from class where id=?", Integer.valueOf(entity.getId()));
	}
}
