<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-26
  Time: 오전 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberSave</title>
</head>
<body>
    <h2>회원가입 정보</h2>
    <form action="/save" method="post">
        <input type="text" name="memberEmail" placeholder="이메일입력"> <br>
        <input type="text" name="memberPassword" placeholder="비밀번호입력"><br>
        <input type="text" name="memberName" placeholder="이름입력"><br>
        <input type="text" name="memberAge" placeholder="나이입력"><br>
        <input type="text" name="memberMobile" placeholder="전화번호입력"><br>
        <input type="submit" value="저장">
    </form>

    <a href="/">홈으로 이동</a>
</body>
</html>
