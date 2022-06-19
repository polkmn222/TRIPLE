<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head> <meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>회원가입 화면</title> 
<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/add.css">
</head> 
<body>
<div class="container">
<div class="input-form-backgroud row">
<div class="input-form col-md-12 mx-auto">
<h4 class="mb-3">회원가입</h4>
<form class="validation-form" id = "validation-form"  onsubmit = "return addUser();" novalidate>
 <div class="row"> 
<div class="col-md-10 mb-3">
	
	<label for="uid">아이디</label>
	<input type="text" class="form-control" id="uid" name = "uid"  placeholder="4~12자의 영문 대소문자와 숫자로만 입력해주세요."  required>
	<div class="invalid-feedback"> 4~12자의 영문 대소문자와 숫자로만 입력해주세요. </div> 
	</div>
		<button type ="button" class="button button1"  onclick = "idchecked()">중복확인</button>
	</div>
		
		
		<div class="mb-3">
	    <label for="pwd">비밀번호</label>
	    <input type="password" class="form-control" id="pwd" name = "pwd"  placeholder="4~12자의 영문 대소문자와 숫자로만 입력해주세요." required>
	    <div class="invalid-feedback"> 4~12자의 영문 대소문자와 숫자로만 입력해주세요. </div> </div> <!-- </div>  -->
		
		
		<div class="mb-3">
	    <label for="name">이름</label>
	    <input type="text" class="form-control" id="name" name = "name"  placeholder="이름은 두 글자 이상입니다." required>
	    <div class="invalid-feedback"> 이름을 입력해주세요. </div> </div> <!-- </div>  -->
	
		
	    	
	    	<div class="mb-3"> 
	        <label for="phone">전화번호</label>
	        <input type="tel" class="form-control" id="phone" name = "phone" placeholder="010-1234-5678"  required>
	        <div class="invalid-feedback"> 전화번호 입력해주세요. </div> </div> 
	    	
	    	<div class="mb-3"> 
	        <label for="email">이메일</label>
	        <input type="email" class="form-control" id="email" name = "email" placeholder="abc@abc.com"  required>
	        <div class="invalid-feedback"> 이메일을 입력해주세요. </div> </div> 
	        
	        	<div class="mb-3">
	        	<label for="address">주소</label>
	            <input type="text" class="form-control" id="address" name = "address" placeholder="서울특별시 강남구" required>
	            <div class="invalid-feedback"> 주소를 입력해주세요. </div> </div>
	           
	            	
	            	<div class="row"> <div class="col-md-8 mb-3"> <label for="root">가입 경로</label> <select class="custom-select d-block w-100" id="root" name = "root">
	                <option value=""></option> <option>검색</option> <option>지인추천</option> <option>SNS</option> </select> <div class="invalid-feedback"> 가입 경로를 선택해주세요. </div> </div>
	                </div>
	                         <hr class="mb-4"> <div class="custom-control custom-checkbox">
	                         <input type="checkbox" class="custom-control-input" id="aggrement" required>
	                         <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label> </div>
	                         	<div class="mb-4"></div> <button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button>
	                            
</form></div></div> 
	<footer class="my-3 text-center text-small"> <p class="mb-1">&copy; 2022 PSY</p> </footer></div>
	<script src="/js/add.js"></script>
</body>
</html>