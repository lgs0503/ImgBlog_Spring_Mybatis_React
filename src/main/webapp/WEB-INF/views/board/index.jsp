
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .search-form {
        margin : 20px 20px;
    }
    .search-form div{
        display: inline-block;
        margin: 0px 20px;
    }
    .btn-form {
        float: right;
    }
    table{
        background-color: #efefef;
    }
    .board-form{
        padding : 0px 20px;
    }
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script>
    function boardSearch(){
        document.getElementById('boardForm').submit();
    }
    function boardAdd(){
        location.href = "/addBoardView";
    }
</script>
<body>
    <form id="boardForm" name="boardForm" action="/boardView" method="get">
        <div class="board">
            <div class="search-form">
                <div>
                    <label>제목</label>
                    <input type="text" id="title" name="title" value="${title}"/>
                </div>
                <div>
                    <label>작성자</label>
                    <input type="text" id="createUser" name="userName" value="${userName}"/>
                </div>
                <div class="btn-form">
                    <input class="btn btn-primary" type="button" value="조회" onclick="boardSearch();"/>
                    <input class="btn btn-primary" type="button" value="글쓰기" onclick="boardAdd();"/>
                </div>
            </div>
            <div class="board-form">
                <table class="table">
                    <thead class="table-dark">
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty boardList}">
                            <tr>
                                <td colspan="4">조회된 데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:if test="${!empty boardList}">
                            <c:forEach var="board" items="${boardList}" varStatus="varStatus">
                                <tr>
                                    <td>${varStatus.index + 1}</td>
                                    <td>${board.POST_TITLE}</td>
                                    <td>${board.CREATE_USER}</td>
                                    <td>${board.CREATE_DATE}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <div class="page-form">

            </div>
        </div>
    </form>
</body>
</html>
