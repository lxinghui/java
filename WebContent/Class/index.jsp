<%@page import="utils.DbHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../model/list_header.jsp"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
    <link rel="shortcut icon" href="favicon.ico">
     <link href="../libs/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../libs/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../libs/css/animate.css" rel="stylesheet">
    <link href="../libs/css/style.css?v=4.1.0" rel="stylesheet">
<title>Insert title here</title>
<script type="text/javascript">
	function del(id,sym) {	
		   if(sym!=0){
			   alert("班里有人");
		   }else{
			   c(id);
		   }
		    
		    	   
	}
	function c(id){
		location.href = "b?cmd=delete&id=" + id;	
	}
</script>
</head>
<body>
 <div class="demoTable">


		<form action="index.jsp" method="post">
			<input type="text" name="txt"><input type="submit" value="查询">
			<div class="layui-laypage-btn">
							<a href="b?cmd=add">新增</a>
						</div>
		</form>
	</div>
	<div class="layui-form layui-border-box layui-table-view">
		<div class="layui-table-box">
			<div class="layui-table-body layui-table-main">
				<table class="layui-table">
				  <thead>
						<tr>				
							<th>班级</th>
							<th>功能</th>
						</tr>
					</thead>
					<tbody>
					<%
					request.setCharacterEncoding("utf-8");
					String where = "";
					if (request.getParameter("txt") != null && request.getParameter("txt").length() > 0) {
						where = "where c.content like '%" + request.getParameter("txt") + "%'";
					}
					int max = 5;
					String limit = " limit 0," + max;
					int prev = 1;
					int next = 1;
					if (request.getParameter("pageno") != null && request.getParameter("pageno").length() > 0) {

						int pageno = Integer.valueOf(request.getParameter("pageno"));
						prev = pageno > 1 ? pageno - 1 : 1;
						next = pageno + 1;
						limit = " limit " + (pageno - 1) * max + "," + max;
					}
					ArrayList<HashMap<String, Object>> rs = DbHelper.executeQuery(
							"select * from  class c  " + where
									+ " " + limit);
					for (int i = 0; i < rs.size(); i++) {
					%>
					<tr>
					<td><%=rs.get(i).get("content")%></td>
					<%
					ArrayList<HashMap<String, Object>> rr = DbHelper.executeQuery("select * from student where classid =?",rs.get(i).get("id"));
					int sym = rr.size();
					%>
					<td><a href="javascript:del(<%=rs.get(i).get("id")%>,<%=sym%>);">删除</a>
							<a href="b?cmd=edit&id=<%=rs.get(i).get("id")%>">修改</a></td>
							</tr>
							<%
								}
							%>
					</tbody>
					</table>
			</div>
		</div>
		<div class="layui-table-page">
			<div id="layui-table-page1">
				<div class="layui-box layui-laypage layui-laypage-default">
					<span class="layui-laypage-skip">
						<div class="layui-laypage-btn">
							<a href="index.jsp?pageno=<%=prev%>">上一页</a>
						</div>
					</span>
					<span>
					<div class="layui-laypage-btn">
							<a href="index.jsp?pageno=<%=next%>">下一页</a>
						</div>
					</span>
				</div>
			</div>
		</div>
	</div>
	 <!-- 全局js -->
    <script src="../libs/js/jquery.min.js?v=2.1.4"></script>
    <script src="../libs/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="../libs/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../libs/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="../libs/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="../libs/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="../libs/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="../libs/js/plugins/pace/pace.min.js"></script>
</body>
</html>