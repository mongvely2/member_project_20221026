<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-10-27
  Time: 오후 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberUpdate</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.rtl.min.css">
</head>
<body>
<div class="container" id="update-form">
    <form action="/update" method="post" name="updateForm">
<%--        readonly : 읽기만 가능한 태그--%>
        id: <input type="text" name="id" value="${member.id}" class="form-control" readonly>
        email: <input type="text" name="memberEmail" value="${member.memberEmail}" class="form-control" readonly>
        password: <input type="text" name="memberPassword" id="memberPassword" class="form-control">
        name: <input type="text" name="memberName" value="${member.memberName}" class="form-control">
        mobile: <input type="text" name="memberMobile" value="${member.memberMobile}" class="form-control">
        age: <input type="text" name="memberAge" value="${member.memberAge}" class="form-control">
        <input type="button" value="수정" class="btn btn-warning" onclick="update()">
    </form>
    <a class="btn btn-dark" href="/">홈으로 이동</a>
</div>

</body>
<script>
    const update = () => {
        const passwordDB = '${member.memberPassword}'; //DB에서 가져온 비밀번호
        const memberPasswordCheck = document.getElementById("memberPassword").value;
        if (memberPasswordCheck == passwordDB) {
            document.updateForm.submit();
            // location.href = "/update";  ->  단순히 주소만 요청하는 태그로 잘못된 태그임(form을 실행하여 제출해야 함)
        } else {
            alert("비밀번호가 일치하지 않습니다")
        }
    /*
    * 사용자가 input 태그에 입력한 비밀번호와 DB에서 가져온 비밀번호가 일치하면 수정 요청을 보내고
    * 일치하지 않으면 alert으로 비밀번호가 일치하지 않습니다. 출력
    */
    }
</script>
</html>
