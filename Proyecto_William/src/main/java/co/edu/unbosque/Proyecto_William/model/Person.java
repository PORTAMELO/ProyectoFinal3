package co.edu.unbosque.Proyecto_William.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(nullable = false)
	private String name;
	
	
	@Column(unique = true, nullable = false)
	private Long document;
	
	
	@Column(nullable = false)
	private Integer age;
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	
	public Person(Integer id, String name, Long document, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.age = age;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDocument() {
		return document;
	}

	public void setDocument(Long document) {
		this.document = document;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
