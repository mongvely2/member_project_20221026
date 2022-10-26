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
</head>
<body>
    <h2>상세조회결과</h2>
    <h4>MemberDTO 객체: ${result}</h4>
    <h5>번호: ${result.id}</h5>
    <h5>이메일: ${result.memberEmail}</h5>
    <h5>비밀번호: ${result.memberPassword}</h5>
    <h5>이름: ${result.memberName}</h5>
    <h5>나이: ${result.memberAge}</h5>
    <h5>연락처: ${result.memberMobile}</h5>

    <a href="/">홈으로 이동</a>
</body>
</html>
