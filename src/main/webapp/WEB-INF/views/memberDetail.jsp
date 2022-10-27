<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberDetail</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.rtl.min.css">
</head>
<body>
<div class="container">
    <table class="table">
<%--    상세조회결과--%>
<%--    MemberDTO 객체: ${result}--%>
    <tr>
        <th>번호</th>
        <td>${result.id}</td>
    </tr>
        <tr>
            <th>이메일</th>
            <td>${result.memberEmail}</td>
        </tr>
        <tr>
            <th>비밀번호</th>
            <td>${result.memberPassword}</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${result.memberName}</td>
        </tr>
        <tr>
            <th>나이</th>>
            <td>${result.memberAge}</td>
        </tr>
        <tr>
            <th>연락처</th>
            <td>${result.memberMobile}</td>
        </tr>

    <a href="/">홈으로 이동</a>
    </table>
</div>
</body>
</html>
