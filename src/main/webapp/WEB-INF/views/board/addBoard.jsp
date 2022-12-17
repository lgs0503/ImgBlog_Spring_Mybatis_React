
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
function save(){
    if (confirm('저장하시겠습니까?')){
        var title = document.getElementById('title').value;
        var content = document.getElementById('content').value;
        var userName = document.getElementById('userName').value;

        if(!nullCheck(title)){
            alert('title을 입력해주세요')
            return;
        }

        if(!nullCheck(content)){
            alert('content을 입력해주세요')
            return;
        }

        if(!nullCheck(userName)){
            alert('userName을 입력해주세요')
            return;
        }

        document.getElementById('boardForm').submit();
    }
}
function nullCheck(value){
    var result = false;

    if (value) {
        result = true;
    }

    return result;
}

function back(){
    history.back();
}
</script>
<body>
    <form id="boardForm" name="boardForm" action="/addBoard" method="post">
        <div>
            <div>
                <label>글제목</label>
                <input type="text" id="title" name="title"/>
            </div>
            <div>
                <label>글내용</label>
                <input type="text" id="content" name="content"/>
            </div>
            <div>
                <label>작성자</label>
                <input type="text" id="userName" name="userName"/>
            </div>
        </div>
        <div class="btn-form">
            <input type="button" value="저장" onclick="save()"/>
            <input type="button" value="뒤로가기" onclick="back()"/>
        </div>
    </form>
</body>
</html>
