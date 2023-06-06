package co.edu.unbosque.Proyecto_William.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Proyecto_William.model.Admin;
import co.edu.unbosque.Proyecto_William.model.Infringement;
import co.edu.unbosque.Proyecto_William.repository.AdminDAO;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/AdminController")
@Transactional
public class AdminController {

	@Autowired
	AdminDAO adDAO;
	
	@PostMapping(path = "/addAdmin")
	public ResponseEntity<String> addAdmin(@RequestParam String name, @RequestParam String password) {
		Admin add = new Admin(null, name, password);	
		adDAO.save(add);

		return ResponseEntity.status(HttpStatus.CREATED).body("Creado");
	}
	
	@GetMapping("/getAdmin/{name}")
	public Boolean getAdmin(@RequestParam String password, @PathVariable String name) {
		ArrayList<Admin> lst = adDAO.findAll();
		
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getName().equals(name) && lst.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
}
