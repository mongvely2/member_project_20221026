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
    <script src="/resources/js/jquery.js"></script>
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
            <th>삭제하기</th>
            <th>조회(ajax)</th>
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
                <td>
                    <button class="btn btn-danger" onclick="deleteMember('${member.id}')">삭제</button>
<%--                    <a href="/delete?id=${member.id}">삭제</a>                        --%>
                </td>
                <td>
                    <button class="btn btn-primary" id="memberId" onclick="findMember('${member.id}')">조회(ajax)</button>
                </td>
            </tr>


        </c:forEach>
        <a class="btn btn-dark" href="/">홈으로 이동</a>
    </table>
    <div id="detail-area">

    </div>
</div>
</body>
<script>
    const deleteMember = (clickedId) => {
        <%--console.log('${memberList}');--%>
        <%--console.log("클릭한 id값: ", clickedId);--%>
        location.href = "/delete?id="+clickedId;
    }

    const findMember = (findId) => {
        const detailArea = document.getElementById("detail-area");
        console.log("findId", findId);

        $.ajax({
            type: "get",
            url: "/detail-ajax",
            dataTypes: "json",
            data: {
                id: findId
            },
            success: function (member) {
                console.log("조회결과", member);
                console.log("조회id:", member.id);
                let result =
                    "        <table class=\"table table-striped\">\n" +
                    "            <tr>\n" +
                    "                <th>id</th>\n" +
                    "                <td>"+ member.id +"</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>email</th>\n" +
                    "                <td>" + member.memberEmail + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>password</th>\n" +
                    "                <td>" + member.memberPassword + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>name</th>\n" +
                    "                <td> " + member.memberName + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th>age</th>\n" +
                    "                <td>" + member.memberAge + "</td>\n" +
                    "            </tr>\n" +
                    "        </table>";
                detailArea.innerHTML = result;
            },
            error: function () {
                console.log("실패")
            }
        });

    }
</script>
</html>
