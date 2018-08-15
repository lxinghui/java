<%@page import="utils.DbHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
	<form class="f1" action="b" method="post">
	<c:if test="${requestScope.info!=null}">
	<input type="hidden" name="cmd" value="update">
	<input type="hidden" name="id" value="${requestScope.info.id}">	
</c:if>
<c:if test="${requestScope.info==null}">
	<input type="hidden" name="cmd" value="insert">
</c:if>
		<div class="inputview">
        <label class="inputtext">班级:</label> <input
				class="inputinput" name="content"
				value="${requestScope.info.content}">
			</span>
			<div class="inputbtpanel ">
				<button class="seachbt1" type="submit">保存</button>
			</div>
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