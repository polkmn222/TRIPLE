<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'>
<meta name="viewport" content="width=device-width, initial-scale = 1">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>로그인</title>
</head>
<body>
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1>Account Login</h1>
			</div>
			<div class="form-content">
				<form id="loginForm">
					<div class="form-group">
						<label for="uid">아이디</label> <input type="text" id="uid" name="uid" required="required" />
					</div>
					<div class="form-group">
						<label for="pwd">비밀번호</label> <input type="password" id="pwd" name="pwd" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> </label><a class="form-recovery">Forgot Password? -></a>
					</div>
					<div class="form-group">
						<button type="button" onclick="login()">로그인</button>
					</div>
				</form>
			</div>
		</div>

		<div class="form-panel two">
			<div class="form-header">
				<h1>Forgot Account</h1>
			</div>
			<div class="form-content">
				<form id="forgot">
					<div class="form-group">
						<label for="username">아이디</label> <input type="text" id="uid" name="uid" required="required" />
					</div>
					<div class="form-group">
						<label for="password">이름</label> <input type="text" id="name" name="name" required="required" />
					</div>
					<div class="form-group">
						<label for="cpassword">이메일</label> <input type="email" id="email" name="email" required="required" />
					</div>
					<div class="form-group">
						<label for="">정보를 입력해주세요.</label> <input type="hidden" id="" name="" />
					</div>
					<div class="form-group">
						<label for=""></label> <input type="hidden" />
					</div>
					<div class="form-group">
						<button type="submit" onclick="forgot()">찾기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="pen-footer">
		<a href="/triple/home" target="_self"><i class="material-icons">arrow_backward</i>뒤로가기</a>
		<a href="/triple/add" target="_self">회원가입<i class="material-icons">arrow_forward</i></a>
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>
	<script src="/js/login.js"></script>
</body>
</html>