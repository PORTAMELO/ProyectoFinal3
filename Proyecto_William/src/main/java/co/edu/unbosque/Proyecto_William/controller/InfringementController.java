package co.edu.unbosque.Proyecto_William.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto_William.model.Infringement;
import co.edu.unbosque.Proyecto_William.repository.InfringementDAO;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/InfringementController")
@Transactional
public class InfringementController {

	@Autowired
	private InfringementDAO infriDAO;

	@PostMapping(path = "/addinfringement")
	public ResponseEntity<String> AddInfringement(@RequestParam String code, @RequestParam Integer value,
			@RequestParam boolean immobilized, @RequestParam String description) {
		Infringement add = new Infringement();
		add.setCode(code);
		add.setValue(value);
		add.setImmobilized(immobilized);
		add.setDescription(description);

		infriDAO.save(add);

		return ResponseEntity.status(HttpStatus.CREATED).body("Creado");
	}
	
	@GetMapping(path = "/getallinfringement")
	public ResponseEntity<ArrayList<Infringement>> getInfringement() {
		ArrayList<Infringement> lst = infriDAO.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lst);
	}
}
