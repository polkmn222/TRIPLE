<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
boolean check = (Boolean) request.getAttribute("check");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>글쓰기</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/detail.css">
</head>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<FORM ENCTYPE="multipart/form-data">

					<h4 class="mb-3">게시판</h4>
					<div class="row">
						<div class="col-md-10 mb-3">
							 <input type="hidden" id="num" name="num" value="${bbs.num }"> 
							<label for="author">작성자 : </label><span> ${bbs.author } </span>
						</div>
					</div>
					<div class="mb-3">
						<label for="title">글 제목 : </label><span>${bbs.title }</span>
					</div>
					<%
					if (check) {
					%>
					<c:choose>
						<c:when test="${fn:length(bbs.attach)>0}">
							<c:forEach var="f" items="${bbs.attach}">
								<a href="/uj/file/download/${f.num}"> <img
									src="/images/${f.filename}" width="100px" height="100px" class="thumb" /></a>
								 &nbsp; <a class="link_del" href="javascript:delete_file(${f.num});">삭제</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							첨부파일 없음
						</c:otherwise>
					</c:choose>
					<%
					}
					%>

					<%
					if (check != true) {
					%>
					<c:choose>
						<c:when test="${fn:length(bbs.attach)>0}">
							<c:forEach var="f" items="${bbs.attach}">
								<a href="/uj/file/download/${f.num}"> <img
									src="/images/${f.filename}" width="100px" height="100px" alt=""
									class="thumb" /></a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							첨부파일 없음
						</c:otherwise>
					</c:choose>
					<%
					}
					%>

					<div class="mb-3">
						<label for="contents">내용 : </label> <span>${bbs.contents }</span>
					</div>

					<div>
						<c:if test="${uid==bbs.author}">
							<button class="button1" type="button" onclick="go('${bbs.num }');">수정</button>
							<button class="button1" type="button" onclick="del_board('${bbs.num }');">삭제</button>
						</c:if>
							<button class="button1" type="button" onclick="location.href='/uj/list';">목록</button>
					</div>
				</form>
				 <form id = "commentAdd" name = "commentAdd">
					<input type="hidden" id="num" name="num" value="${bbs.num }"> 
					<input type="hidden" id="comment_writer" name="comment_writer" value="${user.uid}">
					<input type="hidden" id="comment_num" name="comment_num" value="${bbs.num}">
					<label for="comment_writer">작성자 : </label><span> ${user.uid } </span><br>
					<label for="comment">댓글</label> <input type="text" id="comment" name="comment" placeholder="댓글을 입력해주세요." class="line" required>
						
						<button type = "button" onclick="reply();">작성</button>
			
			
			<table class="bbsList" summary="">
			<thead class="head">
				<tr>
					<th>글쓴이</th>
					<th>내용</th>
					<th>날짜</th>
				</tr>
			</thead>
				<tr>
					<c:forEach var="c" items="${cList }">
						<tr>
							<th>${c.comment_writer } </th>
							<th>${c.comment}</th> 
							<th>${c.comment_date }
							<c:if test="${uid==c.comment_writer}">						
							&nbsp;<a class="link_del" href="javascript:cDelete(${c.cnum});">삭제</a>
							</c:if>
							</th>
						</tr>
					</c:forEach>
				</tr>
			</table>
				</form> 
			</div>
		</div>
		<footer class="my-3 text-center text-small">
			<p class="mb-1">&copy; 2022 PSY</p>
		</footer>
	</div>
	<script src="/js/detail.js"></script>
</body>
</html>

