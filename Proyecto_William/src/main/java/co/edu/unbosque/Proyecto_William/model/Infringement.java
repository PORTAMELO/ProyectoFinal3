package co.edu.unbosque.Proyecto_William.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Infringement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(unique = true, nullable = false)
	private String code;
	
	
	@Column(nullable = false)
	private Integer value;
	
	
	@Column(nullable = false)
	private boolean immobilized;
	
	
	@Column(nullable = false)
	private String description;
	
	public Infringement() {
		// TODO Auto-generated constructor stub
	}

	public Infringement(Integer id, String code, Integer value, boolean immobilized, String description) {
		super();
		this.id = id;
		this.code = code;
		this.value = value;
		this.immobilized = immobilized;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean isImmobilized() {
		return immobilized;
	}

	public void setImmobilized(boolean immobilized) {
		this.immobilized = immobilized;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
