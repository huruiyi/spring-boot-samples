<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <base href="<%=basePath%>"/>
</head>
<body>
<h2>Hello World!</h2>
<table>
    <tr>
        <td><a href="hello/mvc" type="submit">当前是index.jsp页面,点击跳Helloword.jsp页</a></td>
    </tr>
</table>
</body>
</html>
