<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
      
%>

<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.easyui.js"></script>  
<script type="text/javascript" src="<%=basePath%>js/jquery/easyui-lang-zh_CN.js"></script>
</head>`
<body>
<input type="hidden" id="staffName" value="${staffName }">
<p>上传失败,请仔细比对Excel数据格式，及文件格式,数据上传中止在<input type="text" id="areaName" value="${staffName }">这一行,此行之前的上传成功</p>
<p>失败原因，可能没有次居民</p>
</body>
</html>