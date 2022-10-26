<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오후 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberLogin</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="/login" method="post">
        <input type="text" name="memberEmail" placeholder="이메일입력">
        <input type="text" name="memberPassword" placeholder="비밀번호입력">
        <input type="submit" value="로그인">
    </form>

    <a href="/">홈으로 이동</a>
</body>
</html>
