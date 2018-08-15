<%@page import="utils.DbHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../libs/css/edit.css">
    <link rel="shortcut icon" href="favicon.ico">
     <link href="../libs/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../libs/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../libs/css/animate.css" rel="stylesheet">
    <link href="../libs/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="../libs/js/jquery.min.js"></script>
<script type="text/javascript">
  function save(){
	 $.post("a",$(".f1").serialize(),function(json){   
		 /*找到f1这个类 form表单 $(selector).post(URL,data,function(data,status,xhr),dataType);
		 *serialize()序列化表单值
		 */
		if(json.status>0){
		 parent.fresh(); 
		 var index=parent.layer.getFrameIndex(window.name);
		 parent.layer.close(index);
	 }else{
		 alert(json.text);
	 }
	 } ,"json");
  }
</script>
</head>
<body>



	<form class="f1" action="a" method="post">
		<c:if test="${requestScope.info!=null}">
	<input type="hidden" name="cmd" value="update">
	<input type="hidden" name="id" value="${requestScope.info.id}">	
</c:if>
<c:if test="${requestScope.info==null}">
	<input type="hidden" name="cmd" value="insert">
</c:if>

<div class="inputview">

		<span class="inputspan"> 
		<label class="inputtext">姓名:</label> 
		 <input class="inputinput" name="name" value="${requestScope.info.name}"> 
		 </span>
		<span class="inputspan"> 	
		<label class="inputtext">性别:</label> 
		 <select name="sex" class="inputselect">
		 <c:forEach items="${requestScope.sexstatus}" var="r" varStatus="v">	 
		 <c:if test="${requestScope.info.sex!=v.index}">
			<option value="${v.index}">${r}</option>
			</c:if>
			 <c:if test="${requestScope.info.sex==v.index}">
			<option selected="selected" value="${v.index}">${r}</option>
			</c:if>
		 </c:forEach>
			
		</select> 
		</span>
		<span>
		<label class="inputtext">班级:</label>     
		<select name="classid">
		 <c:forEach items="${requestScope.classrow}" var="r" varStatus="v">
		  <c:if test="${requestScope.info.classid!=r.id}">
		  <option value="${r.id}">${r.content}</option>
		  </c:if>
		    <c:if test="${requestScope.info.classid==r.id}">
		  <option selected="selected" value="${r.id}">${r.content}</option>
		  </c:if>
		 </c:forEach>
		   </select>
		</span>
		<span>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="button" onclick="save();" >保存</button>
		</div>	
		</span>
	</form>

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
