<%@ page import="java.util.List" %>
<%@ page import="cn.news.entity.English" %>
<%@ page import="cn.news.entity.CurrentAnswerProgress" %>
<%@ page import="cn.news.entity.CurrentAnswerProgressChoiceQuestion" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/17
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>英语练习</title>
    <script src="js/jquery-1.12.4.min.js" type="text/javascript"></script>
    <script>
        $(function () {
            $('#answer').focus();
        });
    </script>
</head>
<body>
<%
    response.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    CurrentAnswerProgressChoiceQuestion currentAnswerProgress = (CurrentAnswerProgressChoiceQuestion) request.getSession().getAttribute("cap");
    request.getSession().setAttribute("cap", currentAnswerProgress);
%>
<h3 align="center"><%=currentAnswerProgress.geteList().size() > 1 ?
        ("答完这题还有" + (currentAnswerProgress.geteList().size() - 1) + "题") : "最后一题啦"%>
</h3>
<h1 align="center"><%=currentAnswerProgress.geteList().get(currentAnswerProgress.getNowAProblemIndex())
        .getChinese().get(currentAnswerProgress.getNowAProblemChineseIndex())%>的英语是什么?</h1>
<h3>A.&nbsp;<%=currentAnswerProgress.getNowChoiceQuestion().get(0)%></h3>
<h3>B.&nbsp;<%=currentAnswerProgress.getNowChoiceQuestion().get(1)%></h3>
<h3>C.&nbsp;<%=currentAnswerProgress.getNowChoiceQuestion().get(2)%></h3>
<h3>D.&nbsp;<%=currentAnswerProgress.getNowChoiceQuestion().get(3)%></h3>
<form name="form1" method="post" action="EnglishServlet?action=englishSwitch">
    <label for="answer"></label>
    <div align="center">
        <p>
            <input type="text" name="answer" id="answer" autocomplete="off">
            <input type="submit" name="affirm" id="affirm" value="提交">
        </p>
    </div>
</form>
<div>
    <%if (currentAnswerProgress.getOnAProblemIndex() >= 0) {%>
    <p align="center"><%=currentAnswerProgress.isOnAProblemResult() ? "上一道题回答正确" : ("上一道题回答错误 答案:" +
            currentAnswerProgress.geteList().get(currentAnswerProgress.getOnAProblemIndex()).getEnglish() + "会重答喔")%>
    </p><%}%>
</div>
</body>
</html>
