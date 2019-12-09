<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HelloSpringMVC</title>
    <base href="<%=basePath%>"/>
</head>
<body>
<h1>this is two two</h1>
<h1>${message}</h1>

<table>
    <tr>
        <td><a href="hello/mvc" type="submit">当前是two页面，点击跳转Helloword页面</a></td>
    </tr>
</table>
</body>
</html> 