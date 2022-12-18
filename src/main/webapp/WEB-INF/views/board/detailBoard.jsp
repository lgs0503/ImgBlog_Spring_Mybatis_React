<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script>
    function update(){
        alert("수정")
    }
    function del(){
        if (confirm("정말 삭제하시겠습니까?")) {
            var postId = document.getElementById("postId").value;
            location.href = "/deleteBoard?postId=" + postId;
        }
    }
    function back(){
        history.back();
    }
</script>
<body>
    <div>
        <input type="hidden" id="postId" name="postId" value="${board.POST_ID}"/>
        <div>
            <label>글제목</label>
            <span>${board.POST_TITLE}</span>
        </div>
        <div>
            <label>글내용</label>
            <span>${board.POST_CONTENT}</span>
        </div>
        <div>
            <label>작성자</label>
            <span>${board.CREATE_USER}</span>
        </div>
        <div>
            <label>작성일</label>
            <span>${board.CREATE_DATE}</span>
        </div>
    </div>
    <div class="btn-form">
        <input type="button" value="수정" onclick="update();"/>
        <input type="button" value="삭제" onclick="del();"/>
        <input type="button" value="뒤로가기" onclick="back()"/>
    </div>
</body>
</html>
