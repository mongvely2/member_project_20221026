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
    <link rel="stylesheet" href="/resources/css/bootstrap.rtl.min.css">
    <script src="/resources/js/jquery.js"></script>
    <style>
        #save-form {
            width: 1000px;
        }
    </style>
</head>
<body>
<div class="container" id="save-form">
    <h2>회원가입 정보</h2>
    <form action="/save" method="post" name="saveForm">
        <input type="text" name="memberEmail" id="memberEmail" onblur="emailDuplicateCheck()" placeholder="이메일입력" class="form-control"> <br>
        <span id="email-dup-check"></span>
        <span id="email-input-check"></span>
        <input type="text" name="memberPassword" placeholder="비밀번호입력" class="form-control"><br>
        <input type="text" name="memberName" placeholder="이름입력" class="form-control"><br>
        <input type="text" name="memberAge" placeholder="나이입력" class="form-control"><br>
        <input type="text" name="memberMobile" placeholder="전화번호입력" class="form-control"><br>
        <input type="button" value="회원가입" onclick="save()" class="btn btn-primary">
<%--        form 태그에는 button 태그 함부로 사용하지 말 것, 페이지가 넘어감, input 타입으로 쓸 것--%>
<%--        <button onclick="btn1Fn()">버튼가입</button>--%>
    </form>
    <a class="btn btn-dark" href="/">홈으로 이동</a>
</div>


</body>
<script>
    const save = () => {
      console.log("save 함수호출");
      if (document.saveForm.memberEmail.value == "") {
          // alert("이메일을 입력해주세요");
          // 이메일 입력하지 않았을 때 span에 빨간색으로 출력
          const emailCheck = document.getElementById("email-input-check");
          emailCheck.innerHTML = "이메일을 입력해주세요";
          emailCheck.style.color = "rde";
          return false;
      } else if(document.saveForm.memberPassword.value == "") {
          alert("비밀번호를 입력해주세요");
          return false;
      } else if(document.saveForm.memberName.value == "") {
          alert("이름을 입력해주세요");
          return false;
      } else if(document.saveForm.memberAge.value == "") {
          alert("나이를 입력해주세요");
          return false;
      } else if(document.saveForm.memberMobile.value == "") {
          alert("전화번호를 입력해주세요");
          return false;
      }
          document.saveForm.submit();
    //  name = saveForm 단을 실행하겠다 라는 문법 _form에 name이 saveForm

    }

    const emailDuplicateCheck = () => {
        const insertEmail = document.getElementById("memberEmail").value;
        const checkResult = document.getElementById("email-dup-check");

        console.log("입력한 이메일", insertEmail);

        $.ajax({
            type: "post",
            url: "/duplicate-check",
            dataTypes: "text",
            data: {
                inputEmail: insertEmail
            },

            success: function (result) {
                console.log("checkResult:" + result )
                if(result == "ok") {
                    checkResult.innerHTML = "사용할 수 있는 이메일 입니다";
                    checkResult.style.color = "green";
                } else {
                    checkResult.innerHTML = "이미 사용중인 이메일 입니다";
                    checkResult.style.color = "red";
                }
            },
            error: function () {
                console.log("실패")

            }

        });
    }

</script>
</html>
