<%@ page import="cn.news.entity.CurrentAnswerProgress" %>
<%@ page import="cn.news.entity.English" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/22
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>英语练习结果</title>
</head>
<body>
<%
    CurrentAnswerProgress currentAnswerProgress = (CurrentAnswerProgress) request.getAttribute("cap");
%>
<h1 align="center">本次练习结果</h1>
<div align="center">
    <table width="200" border="1">
        <tr>
            <td bgcolor="#66CC66">英语&nbsp;</td>
            <td bgcolor="#66CC66">错误次数&nbsp;</td>
        </tr>
        <%
            for (English english : currentAnswerProgress.geteAlreadyList()) {
                if (english.getErrorNumber() > 0) {
        %>
        <tr>
            <td><%=english.getEnglish()%>
            </td>
            <td><%=english.getErrorNumber()%>&nbsp;</td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
<p align="center">分数:<%=((currentAnswerProgress.getTopicNumber() - currentAnswerProgress.getErrorNumber())
        / currentAnswerProgress.getTopicNumber() * 100.0)%>
</p>
</body>
</html>
