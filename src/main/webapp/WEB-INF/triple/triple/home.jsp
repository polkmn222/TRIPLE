<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
	//로그인된 아이디가 있는지 읽어와보기
	String uid =(String)session.getAttribute("uid");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/kotton-css@1.0.0/dist/kotton-style.min.css">
<meta name ="viewport" content = "width=device-width, initial-scale = 1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" /> 
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/home.css">
<title>home</title>
</head>
<body class="kotton-style">
	<div class="wrapper">
     <div class="head">
       <div class="slide-open" id="slide-open">
          <span class="burgur" id="burgur">
          <span class="top-line"></span>
          <span class="bot-line"></span>
       </span>
       </div>
      
       <div class="logo">TRIPLE</div>
     </div>
     <div class="body">
         <img src="/img/home.png" width="100%" height="100%"  alt="" class="thumb" />
       <div class="content">
       </div>
       <div class="slide" id="slide">
         <div class="slide-title">
           메 뉴 
         </div>
         
         <ul>
           <%if(uid==null){%>
           <li><strong><button type="button" 
					onclick="location.href='/triple/login';">로그인</button></strong></li>
           <li><strong><button type="button" 
					onclick="location.href='/triple/add';">회원가입</button></strong></li>
          	<%}else{ %>
          
           <li><strong><button type="button" 
					onclick="location.href='/triple/logout';">로그아웃</button></strong></li>
           <li><strong><button type="button" 
					onclick="location.href='/triple/edit';">회원정보 수정</button></strong></li>
          	<%} %>
          
           
           <li><strong><button type="button" 
					onclick="location.href='/triple/list';">리뷰</button></strong></li>
          		
         </ul>
       </div>
     </div>
	<footer class="my-3 text-center text-small"> <p class="mb-1">&copy; 2022 PSY</p> </footer>
  </div> 
	<script src="/js/home.js"></script>
</body>
</html>