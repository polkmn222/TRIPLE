<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
<meta name="viewport" content="width=device-width, initial-scale = 1">
<link rel="stylesheet" type="text/css" href="/css/list.css">
</head>
<body> 
	<div class="container managergrounp" id="container" name="container">
		<h3>게시판</h3>
		<hr>
		<a class="btn" href="/triple/home">홈 <span class="btn_txt_small">▶</span></a>
		

		<div class="right">
			<select id="cntPerPage" class="select_btn" name="sel" onchange="selChange()">
				<option value="5"
					<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
				<option value="10"
					<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
				<option value="15"
					<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
				<option value="20"
					<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
			</select>

		</div>
		<table class="bbsList" summary="">
			<thead class="head">
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody class="body">
				<tr>
					<c:forEach var="b" items="${viewAll }">
						<tr>
							<th>${b.num }.</th>
							<th><a href="/triple/detail?num=${b.num}">${b.title }</a></th>
							<th>${b.author }</th>
							<th>${b.bdate }</th>
						</tr>
					</c:forEach>
				</tr>
			</tbody>

			<tfoot class="foot">
				<tr>
					<td colspan="9">
						<c:if test="${paging.startPage != 1 }">
							<a href="/triple/list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
						</c:if> <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage }">
									<b>${p }</b>
								</c:when>
								<c:when test="${p != paging.nowPage }">
									<a href="/triple/list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
								</c:when>
							</c:choose>
						</c:forEach> 
							<c:if test="${paging.endPage != paging.lastPage}">
								<a href="/triple/list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
							</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<div class="btn_wrap">
		<a class="btn_org" href="/triple/badd"><span class="txt_white">리뷰작성</span></a>
	</div>
 <script>
//페이지 갯수 변경
 function selChange() {
 	var sel = document.getElementById('cntPerPage').value;
 	location.href = "list?nowPage=${paging.nowPage}&cntPerPage=" + sel;
 }
</script>
</body>
</html>