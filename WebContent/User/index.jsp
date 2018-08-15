<%@page import="utils.DbHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../model/list_header.jsp"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <link rel="shortcut icon" href="favicon.ico">
     <link href="../libs/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../libs/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../libs/css/animate.css" rel="stylesheet">
    <link href="../libs/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript">

    var select=${requestScope.select};
    var txt=${requestScope.txt};
	function del(id) {
		location.href = "a?cmd=delete&id=" + id;

	}
	function fresh(){
		location.href="a";
	}
	function openwin(){
		show("a?cmd=add",300,220);
	}
	function openedit(id){
		show("a?cmd=edit&id="+id,300,220);
	}
	$(function(){
		$(".selectall").on("click",function(){
			if(event.target.checked){   //全选
			$(".chk").prop("checked","checked");  //checked = "checked"
			}
			else{
			$(".chk").prop("checked", "");
			}
		});
		
		$(".sselect").on("change",function(){   //下拉框的change事件
			$(".sinput").css("display","none").prop("disabled","disabled");  //disabled 不能更改  全部消失单独更改特定的
			$($(".sinput")[$(".sselect").val()]).css("display","inline").removeAttr("disabled");  //sinput[] 数组  用sselect.val确定哪个sinput
		});
		
		
		$(".sselect").val(select);    //servlet回来的select
		$(".sinput").css("display","none").prop("disabled","disabled");
		$($(".sinput")[select]).css("display","inline").removeAttr("disabled").val(txt);
	});
	 function delall(){
		 if(confirm("确认删除")){
			 var ids=[];
			 $(".chk").each(function(){
				 if($(this).prop("checked")){			
					 ids.push($(this).attr("myid"));   //把myid放到ids里
				 }
			 });
			 if(ids.length==0){
				 alert("请选择行");
				 return;
			 }
			 location.href="a?cmd=deleteall&ids="+ids;
		 }
	 }
</script>
<style type="text/css">
[type='checkbox']{
display:block !important;
}
</style>
</head>
<body>
	<div class="demoTable">


		<form action="a" method="post">
		
		    <select class="sselect" name="select">           <!-- $(".sselect").val() -->
		    <option value="0">姓名查询</option>
		    <option value="1">班级查询</option>
		    <option value="2">性别查询</option>
		    </select>
		    
		    <input name="txt" class="sinput" >		    
		    <select name="txt" class="inputselect sinput" style="height:30px;display: none;">
		        <c:forEach items="${requestScope.classrow}" var="r" varStatus="v">         
		          <option value="${r.id}">${r.content}</option>		         		          
		        </c:forEach>
		    </select>
		    
		    <select name="txt" class="inputselect sinput" style="height:30px;display: none;"> 
		       <c:forEach items="${requestScope.sexstatus}" var="r" varStatus="v">		       
		            <option value="${v.index}">${r}</option>		       
		       </c:forEach>
		    </select>
		    
			<button  class="layui-btn" type="submit">查询</button>
			
			<div class="layui-laypage-btn">
			<button type="button" class="layui-btn" onclick="openwin();">新增</button>
			<button  class="layui-btn" type="button" onclick="delall();">批量删除</button>	
			</div>
		</form>


	</div>


	<div class="layui-form layui-border-box layui-table-view">
		<div class="layui-table-box">
			<div class="layui-table-body layui-table-main">
				<table class="layui-table">
					<thead>
						<tr>
						    <th><input type="checkbox" class="selectall"></th>
							<th>姓名</th>
							<th>性别</th>
							<th>班级</th>
							<th>功能</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list}" var="r" > 
						<tr>
					<td><input type="checkbox" class="chk" myid="${r.id}"></td>
							<td>${r.name }</td>
							<td>${r.sex_Name}</td>
							<td>${r.class_name}</td>
							<td><a href="javascript:del(${r.id});">删除</a>
							<a href="javascript:openedit(${r.id});">修改</a></td>
						</tr>
					</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

		<div class="layui-table-page">
			<div id="layui-table-page1">
				<div class="layui-box layui-laypage layui-laypage-default">
					<span class="layui-laypage-skip">
						<button type="button" class="layui-btn"
							onclick="location.href='a?pageno=${requestScope.prev}'">上一页</button>
						<button type="button" class="layui-btn"
							onclick="location.href='a?pageno=${requestScope.next}'">下一页</button>
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
