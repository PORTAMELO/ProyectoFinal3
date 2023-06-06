function iniciar() {
	$.ajax({
		method: "GET",
		url: "http://localhost:8081/InfringementController/getallinfringement",
		success: function(infringements) {
			for (const infringement of infringements) {
				$('#infringement').append('<option value="' + infringement.code + '">' + infringement.description + '</option>');
			}
		}
	});
	
	$.ajax({
		method: "GET",
		url: "http://localhost:8081/InfringementController/getallinfringement",
		success: function(infringements) {
			for (const infringement of infringements) {
				$('#infringement2').append('<option value="' + infringement.code + '">' + infringement.description + '</option>');
			}
		}
	});
}

function iniciar2() {
	$.ajax({
		method: "GET",
		url: "http://localhost:8081/VehicleController/AllVehicle",
		success: function(vehicles) {
			for (const current of vehicles) {
				$('#vehicle-table-body').append(`
				 <tr>
				    <th>${current.id}</th>
				    <th>${current.ownerDocument}</th>
				    <th>${current.registration}</th>
				    <th>${current.mark}</th>
				    <th>${current.color}</th>
				    <th>${current.codesInfringement}</th>
				  </tr>
				`);
			}
		}
	});
	
	$.ajax({
		method: "GET",
		url: "http://localhost:8081/PersonController/GetAllPerson",
		success: function(person) {
			for (const current of person) {
				$('#person-table').append(`
				 <tr>
				    <th>${current.id}</th>
				    <th>${current.name}</th>
				    <th>${current.document}</th>
				    <th>${current.age}</th>
				  </tr>
				`);
			}
		}
	});
}

iniciar();
iniciar2();




function agregarVehiculo(e) {
	e.preventDefault();
	const body = {
		'owner_document': $('#ownerDocument').val(),
		'registration': $('#registro').val(),
		'mark': $('#marca').val(),
		'color': $('#color').val(),
		'codes_infringement': $('#infringement').val(),
	}
	$.ajax({
		method: "POST",
		url: "http://localhost:8081/VehicleController/AddVehicle",
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Agregado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 412) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}

$('#add-vehicle-form').submit(agregarVehiculo);




function eliminarVehiculo(e) {
	e.preventDefault();
	const body = {
		'registration': $('#eliminarMatricula').val(),
	}
	$.ajax({
		method: "DELETE",
		url: "http://localhost:8081/VehicleController/DeleteVehicle/" + $('#eliminarMatricula').val(),
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Eliminado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 404) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}

$('#delete-vehicle-form').submit(eliminarVehiculo);




function agregarPersona(e) {
	e.preventDefault();
	const body = {
		'name': $('#nombre-persona').val(),
		'document': $('#documento-persona').val(),
		'age': $('#edad-persona').val(),
	}
	$.ajax({
		method: "POST",
		url: "http://localhost:8081/PersonController/AddPerson",
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Agregado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 412) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}

$('#add-person-form').submit(agregarPersona);


function agregarInfraccion(e) {
	e.preventDefault();
	const body = {
		'registration': $('#matricula-penalty').val(),
		'infringement': $('#infringement2').val(),
	}
	$.ajax({
		method: "PUT",
		url: "http://localhost:8081/VehicleController/AddInfringement/" + $('#matricula-penalty').val(),
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Agregado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 412) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}

$('#add-penalty-form').submit(agregarInfraccion);


function eliminarInfraccion(e) {
	e.preventDefault();
	const body = {
		'registration': $('#matricula-penalty-remove').val(),
		'infringement': $('#codigo-infraccion-remove').val(),
	}
	$.ajax({
		method: "PUT",
		url: "http://localhost:8081/VehicleController/DeleteInfringement/" + $('#matricula-penalty-remove').val(),
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Eliminado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 412) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}


$('#remove-penalty-form').submit(eliminarInfraccion);




function eliminarPersona(e) {
	e.preventDefault();
	const body = {
		'document': $('#eliminarPersona').val(),
	}
	$.ajax({
		method: "DELETE",
		url: "http://localhost:8081/PersonController/DeletePerson/" + $('#eliminarPersona').val(),
		data: body,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'Eliminado',
			}).then(() => location.reload());
		},
		error: function(xhr, status, error) {
			if (+xhr.status === 412) {
				Swal.fire({
					icon: 'warning',
					title: 'Warning',
					text: xhr.responseText,
				});
			}
			console.log("[ERROR]");
			console.log(xhr);
		}
	});
}



$('#delete-person-form').submit(eliminarPersona);