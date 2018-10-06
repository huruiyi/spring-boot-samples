<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <span class="panel-title">学生信息</span></div>
                <div class="panel-body">
                    编号： <span th:text="#{student.id}">2</span><br/>
                    姓名：<span th:text="#{student.name}">萌萌哒</span><br/>
                    性别：<span th:text="#{student.gender}">男</span><br/>
                    年龄：<span th:text="#{student.age}">20</span><br/>
                    电话：<span th:text="#{student.telephone}">15890905678</span>
                    <br/>
                </div>
                <div class="panel-footer text-right">
                    <span class="panel-title">酒城工作室@2107</span></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


