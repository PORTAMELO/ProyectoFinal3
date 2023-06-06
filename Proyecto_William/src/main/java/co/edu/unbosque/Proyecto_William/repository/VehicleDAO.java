package co.edu.unbosque.Proyecto_William.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyecto_William.model.Vehicle;
import java.util.List;


public interface VehicleDAO extends CrudRepository<Vehicle, Integer>{

	public ArrayList<Vehicle> findAll();
	
	public ArrayList<Vehicle> findByOwnerDocument(long ownerDocument);
	
	public Vehicle findByRegistration(String registration);
	
	public void deleteByRegistration(String registration);
	
}
