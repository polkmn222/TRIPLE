// 미 작성시 빨간 글씨
window.addEventListener('load', () => {
	const forms = document.getElementsByClassName('validation-form');

	Array.prototype.filter.call(forms, (form) => {
		form.addEventListener('submit', function(event) {
			if (form.checkValidity() === false) {
				event.preventDefault(); event.stopPropagation();
			}

			form.classList.add('was-validated');
		}, false);
	});
},
	false);


// 회원가입
var idcheck = false;
function addUser() {


	if (!confirm('가입 하시겠습니까?')) {
		alert('정상적으로 취소했습니다.');
		return false;
	}
	if (idcheck == false) {
		alert('아이디 중복확인을 해주세요');
		return false;
	}


	var serData = $('#validation-form').serialize();

	$.ajax({
		url: '/triple/add',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.add) {
				alert('가입 되었습니다!');
			} else {
				alert('가입에 실패했습니다.');
			}
			location.href = "/triple/login";
		},
		error: function(xhr, status, err) {
			alert('err ' + err);
		}
	});
	return false;
}

// 아이디 중복 체크
function idchecked() {
	var serData = $('#uid').serialize();
	var uid_val = $("#uid").val();

	if (uid_val.length > 13 || uid_val.length < 4) {
		alert('양식에 맞춰주세요.');
		return false;
	}

	if (!idTest(uid_val)) {
		alert('양식에 맞춰주세요.');
		return false;
	}


	$.ajax({
		url: '/triple/idcheck',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.check) {
				alert('사용 가능한 아이디입니다.');
				idcheck = true;
			} else {
				alert('중복된 아이디입니다.');
			}
		},

		error: function(xhr, status, err) {

			alert(err);
		}
	});


}

// 유효성검사		
function idTest(uid) {

	var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
	if (!idRegExp.test(uid)) {

		return false;
	}
	return true;
}

function pwdTest(pwd) {

	var password1RegExp = /^[a-zA-z0-9]{4,12}$/;
	if (!password1RegExp.test(pwd)) {

		return false;
	}

}

function emailTest(email) {

	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (!emailRegExp.test(email)) {

		return false;
	}
	return true;
}

function nameTest(name) {

	var nameRegExp = /^[가-힣]{2,5}$/;
	if (!nameRegExp.test(name)) {

		return false;
	}
	return true;
}