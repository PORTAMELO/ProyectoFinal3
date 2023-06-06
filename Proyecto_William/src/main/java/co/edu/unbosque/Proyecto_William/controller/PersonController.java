package co.edu.unbosque.Proyecto_William.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto_William.model.Infringement;
import co.edu.unbosque.Proyecto_William.model.Person;
import co.edu.unbosque.Proyecto_William.model.Vehicle;
import co.edu.unbosque.Proyecto_William.repository.PersonDAO;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/PersonController")
@Transactional
public class PersonController {

	@Autowired
	PersonDAO perDAO;
	
	@PostMapping(path = "/AddPerson")
	public ResponseEntity<String> AddPerson(@RequestParam String name, @RequestParam Long document,
			@RequestParam Integer age) {
		
		if (perDAO.findByDocument(document) != null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("El documento introducido ya está registrado.");
		} else {
			Person add = new Person(null, name, document, age);
			perDAO.save(add);
			return ResponseEntity.status(HttpStatus.CREATED).body("Creado");
		}
		
	}
	
	@DeleteMapping("/DeletePerson/{document}")
	public ResponseEntity<String> deleteRegistration(@PathVariable Long document) {

		Person pt = perDAO.findByDocument(document);
		if (pt == null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body("La cedula de la persona no coincide con ninguna registrada.");
		} else {
			perDAO.deleteByDocument(document);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("La persona se ha eliminado correctamente de la base de datos.");
		}

	}
	
	@GetMapping(path = "/GetAllPerson")
	public ResponseEntity<ArrayList<Person>> getAllPerson() {
		ArrayList<Person> lst = perDAO.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lst);
	}
	
}
