function buscarAdmin(e) {
	e.preventDefault();
	const body = {
		'name': $('#username').val(),
		'password': $('#password').val(),
	}
	$.ajax({
		method: "GET",
		url: "http://localhost:8081/AdminController/getAdmin/" + $('#username').val(),
		data: body,
		success: function(data) {
			if (data === true) { // Reemplaza "data === true" con tu condición real para verificar si el resultado es "true"
				Swal.fire({
					icon: 'success',
					title: 'Bienvenido',
				}).then(() => {
					window.location.href = "http://localhost:8080/Front/HTML/Admins.html";
				});
			} else {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: 'Usuario o clave incorrectos',
				});
			}
		},
		error: function(xhr, status, error) {
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}

$('#login-search').submit(buscarAdmin);