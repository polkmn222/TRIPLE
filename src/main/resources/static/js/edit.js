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

// 회원정보 수정
function updateUser() {
	var serData = $('#updateForm').serialize();
	$.ajax({
		url: '/triple/update',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.updated) {
				alert('수정 성공!');
			} else {
				alert('수정 실패');
			}
			location.href = "/triple/home";
		},
		error: function(xhr, status, err) {
			alert('에러:' + err);
		}
	});
	return false;
}

// 회원정보 삭제
function del_user(uid) {

	if (!confirm('정말 탈퇴 하시겠습니까?')) {
		return;
	}
	var obj = {};
	obj.uid = uid;
	$.ajax({
		url: '/triple/delete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.deleted ? '탈퇴 성공' : '탈죄 실패');
			location.href = '/triple/logout';
		},
		error: function(xhr, status, err) {
			alert('에러 : ' + err);
		}
	});
}