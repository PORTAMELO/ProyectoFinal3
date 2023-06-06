package co.edu.unbosque.Proyecto_William.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto_William.model.Infringement;
import co.edu.unbosque.Proyecto_William.model.Vehicle;
import co.edu.unbosque.Proyecto_William.repository.InfringementDAO;
import co.edu.unbosque.Proyecto_William.repository.PersonDAO;
import co.edu.unbosque.Proyecto_William.repository.VehicleDAO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/VehicleController")
@Transactional
public class VehicleController {

	@Autowired
	VehicleDAO vehDAO;

	@Autowired
	PersonDAO perDAO;

	@Autowired
	InfringementDAO infDAO;
	
	@PostMapping(path = "/AddVehicle")
	public ResponseEntity<String> AddVehicle(@RequestParam long owner_document, @RequestParam String registration,
			@RequestParam String mark, @RequestParam String color, @RequestParam String codes_infringement) {

		if (perDAO.findByDocument(owner_document) == null) {

			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("El documento introducido no está registrado.");

		} else if (vehDAO.findByRegistration(registration) != null) {

			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("La matrícula ya está registrada.");

		} else {

			Vehicle add = new Vehicle(null, owner_document, registration, mark, color, codes_infringement);
			vehDAO.save(add);
			return ResponseEntity.status(HttpStatus.CREATED).body("Creado");

		}
	}

	@GetMapping("/AllVehicle")
	public ResponseEntity<ArrayList<Vehicle>> seeVehicles() {
		ArrayList<Vehicle> lista = vehDAO.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/SearchVehiclesDocument/{ownerDocument}")
	public ResponseEntity<String> getDocument(@PathVariable Long ownerDocument) {
		ArrayList<Vehicle> lst = vehDAO.findByOwnerDocument(ownerDocument);
		if (!lst.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(lst.toString());
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("No hay vehículo asociado al documento introducido.");
		}
	}

	@PutMapping("/AddInfringement/{registration}")
	public ResponseEntity<String> updateInfringement(@PathVariable String registration,
			@RequestParam String infringement) {

		Vehicle pt = vehDAO.findByRegistration(registration);

		if (pt == null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("La matrícula del vehículo no coincide con ningún vehículo registrado.");
		} else {
			
			pt.setCodesInfringement(pt.getCodesInfringement() + "/" + infringement);
			vehDAO.save(pt);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("La infraccion se ha añadido correctamente.");
		}
	}

	@PutMapping("/DeleteInfringement/{registration}")
	public ResponseEntity<String> deleteInfringement(@PathVariable String registration,
			@RequestParam String infringement) {
		Vehicle pt = vehDAO.findByRegistration(registration);
		if (pt == null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("La matrícula del vehículo no coincide con ningún vehículo matriculado.");
		} else {
			String arr[] = pt.getCodesInfringement().split("/");
			String aux = "";
			Boolean cond = true;
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].equals(infringement) && cond == true) {
					aux += arr[i] + "/";
					cond = false;
				}
			}
			if (aux.equals("")) {
				vehDAO.deleteByRegistration(registration);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("La infracción se ha eliminado correctamente.");	
			}else {
				pt.setCodesInfringement(aux);
				vehDAO.save(pt);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("La infracción se ha eliminado correctamente.");
			}
		}
	}

	@DeleteMapping("/DeleteVehicle/{registration}")
	public ResponseEntity<String> deleteRegistration(@PathVariable String registration) {

		Vehicle pt = vehDAO.findByRegistration(registration);
		if (pt == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La matrícula del vehículo no coincide con ningún vehículo matriculado.");
		} else {
			vehDAO.deleteByRegistration(registration);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("La infracción se ha eliminado correctamente.");
		}

	}

}
