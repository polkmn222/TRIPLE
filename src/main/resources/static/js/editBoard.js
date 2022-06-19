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

// 게시글 변경
function update() {

	if (!confirm('정말로 저장하시겠습니까?')) {
		alert('정상적으로 취소했습니다.');
		return false;
	}


	var formData = new FormData($("#updateForm")[0]);
	$.ajax({
		url: '/triple/bupdate',
		method: 'post',
		enctype: 'multipart/form-data',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		dataType: 'json',
		success: function(res) {
			if (res.updated) {
				alert('저장 성공!');
			} else {
				alert('저장 실패');
			}
			location.href = "/triple/list";
		},
		error: function(xhr, status, err) {
			alert('err' + err);
		}
	});
	return false;
}

// 사진 미리보기
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