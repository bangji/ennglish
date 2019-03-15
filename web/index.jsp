<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/17
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>获取你英语文件的路径</title>
  </head>
  <body>
  <form action="EnglishServlet?action=fileIO" method="post" enctype="multipart/form-data">
    <label> 路径: </label>
    <input type="file" name="englishFolder">
    <input type="submit" value="确认"/>
  </form>
  </body>
</html>
