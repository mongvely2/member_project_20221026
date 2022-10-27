<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberList</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.rtl.min.css">
<%--    <style>--%>
<%--        table, tr, td, th{--%>
<%--            border: 1px solid black;--%>
<%--            border-collapse: collapse;--%>
<%--            padding: 13px;--%>
<%--        }--%>
<%--    </style>--%>
</head>
<body>
<div class="container">
    <table class="table table-striped table-hover">
        <tr>
            <th>번호</th>
            <th>이메일</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>나이</th>
            <th>핸드폰번호</th>
            <th>상세조회</th>
    <%--        <th>삭제하기</th>--%>
        </tr>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>
                    <a href="member?id=${member.id}"> ${member.memberEmail}</a>
                </td>
                <td>${member.memberPassword}</td>
                <td>${member.memberName}</td>
                <td>${member.memberAge}</td>
                <td>${member.memberMobile}</td>
                <td>
                    <a href="/member?id=${member.id}">상세조회</a>
                </td>
    <%--            <td><a href="/delete">삭제</a></td>--%>
            </tr>
        </c:forEach>
        <a href="/">홈으로 이동</a>
    </table>
</div>

</body>
</html>
