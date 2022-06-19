// 비밀번호 찾기 버튼
$(document).ready(function() {
	var panelOne = $('.form-panel.two').height(), panelTwo = $('.form-panel.two')[0].scrollHeight;
	$('.form-panel.two').not('.form-panel.two.active').on('click',
			function(e) {
				e.preventDefault();
				$('.form-toggle').addClass('visible');
				$('.form-panel.one').addClass('hidden');
				$('.form-panel.two').addClass('active');
				$('.form').animate({'height': panelTwo}, 200);
			});
			$('.form-toggle').on('click', function(e) {
				e.preventDefault();
				$(this).removeClass('visible');
				$('.form-panel.one').removeClass('hidden');
				$('.form-panel.two').removeClass('active');
				$('.form').animate({
					'height': panelOne
				}, 200);
			});
		});

// 비밀번호 찾기
function forgot() {
	var serData = $('#forgot').serialize();

	$.ajax({
		url: '/triple/forgot',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.ok) {
				alert('회원정보를 변경해주세요.');
				location.href = "/triple/edit";
			} else {
				alert('입력된 정보가 옳바르지 않습니다.');
			}
		},
		error: function(xhr, status, err) {
			alert('err: ' + err);
		}
	});
	return false;
}

// 로그인
function login() {
	var serData = $('#loginForm').serialize();

	$.ajax({
		url: '/triple/login',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.ok) {
				alert('로그인 성공!');
				location.href = "/triple/home";
			} else {
				alert('로그인 실패');
			}
		},
		error: function(xhr, status, err) {
			alert('err: ' + err);
		}
	});
	return false;
}