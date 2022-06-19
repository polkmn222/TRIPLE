// 수정
 
function go(num) {
	var url = "/triple/bedit?num=" + num;
	location.href = url;
}

// 게시글 삭제
function del_board(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}

	var obj = {};
	obj.num = num;

	$.ajax({
		url: '/triple/bdelete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.deleted ? '삭제 성공' : '삭제 실패!');
			location.href = '/triple/list';
		},
		error: function(xhr, status, err) {
			alert('에러 : ' + err);
		}
	});
}

// 파일 삭제
function delete_file(num) {

	if (!confirm('정말로 삭제하시겠어요?')) {
		return;
	}
	var obj = {};
	obj.num = num;
	$.ajax({
		url: '/triple/file/delete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.deleted ? '삭제 성공' : '삭제 실패');
			location.reload();
		},
		error: function(xhr, status, err) {
			alert('에러:' + err);
		}
	});
}

// 이미지 미리보기
function setThumbnail(event) {
	for (var image of event.target.files) {
		var reader = new FileReader();

		reader.onload = function(event) {
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute("style", "width:32%;height:width;");
			document.querySelector("div#image_container").appendChild(img);

		};

		console.log(image);
		reader.readAsDataURL(image);
	}
}

// 댓글
function reply() {

	if (!confirm('글을 작성하시겠습니까?')) {
		alert('정상적으로 취소했습니다.');
		return false;
	}

	var serData = $('#commentAdd').serialize();
	$.ajax({
		url: '/triple/cadd',
		method: 'post',
		cache: false,
		data: serData,
		dataType: 'json',
		success: function(res) {
			if (res.cadd) {
				alert('저장 성공!');
				location.reload();
			} else {
				alert('저장실패.');
				location.reload();
				return false;
			}
		},
		error: function(xhr, status, err) {
			alert('err' + err);
		}
	});
	return false;
}

// 댓글 삭제
function cDelete(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}
	var obj = {};
	obj.num = num;
	$.ajax({
		url: '/triple/cDeleted',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.cDeleted ? '삭제 성공' : '삭제 실패!');
			location.reload();
		},
		error: function(xhr, status, err) {
			alert('에러 : ' + err);
		}
	});
}