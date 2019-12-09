<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

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
<h1>message:${message}</h1>
<h1>this is my spring mvc</h1>

<table>
    <tr>
        <td><a href="hello/two" type="submit">当前是Hellword.jsp页面，点击跳转two页面</a></td>
    </tr>
</table>
</body>
</html> 