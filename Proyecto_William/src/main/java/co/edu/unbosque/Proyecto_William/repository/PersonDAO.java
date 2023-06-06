package co.edu.unbosque.Proyecto_William.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto_William.model.Person;

import java.util.ArrayList;
import java.util.List;


public interface PersonDAO extends CrudRepository<Person, Integer>{

	public Person findByDocument(Long document);
	
	public void deleteByDocument(Long document);
	
	public ArrayList<Person> findAll();
	
}
