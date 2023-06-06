package co.edu.unbosque.Proyecto_William.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto_William.model.Admin;

public interface AdminDAO extends CrudRepository<Admin, Integer> {
	
	public ArrayList<Admin> findAll();
	
}
