package co.edu.unbosque.Proyecto_William.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto_William.model.Infringement;
import java.util.List;


public interface InfringementDAO extends CrudRepository<Infringement, Integer>{

	public ArrayList<Infringement> findAll();
	
	public Infringement findByDescription(String description);
	
	public Infringement findByCode(String code);
	
}
