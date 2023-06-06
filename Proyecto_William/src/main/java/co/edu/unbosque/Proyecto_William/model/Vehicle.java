package co.edu.unbosque.Proyecto_William.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(nullable = false)
	private Long ownerDocument;
	
	
	@Column(nullable = false, unique = true)
	private String registration;
	
	
	@Column(nullable = false)
	private String mark;
	
	
	@Column(nullable = false)
	private String color;
	
	
	@Column(nullable = false)
	private String codesInfringement;
	
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}


	public Vehicle(Integer id, Long ownerDocument, String registration, String mark, String color,
			String codesInfringement) {
		super();
		this.id = id;
		this.ownerDocument = ownerDocument;
		this.registration = registration;
		this.mark = mark;
		this.color = color;
		this.codesInfringement = codesInfringement;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Long getOwnerDocument() {
		return ownerDocument;
	}


	public void setOwnerDocument(Long owner_document) {
		this.ownerDocument = owner_document;
	}


	public String getRegistration() {
		return registration;
	}


	public void setRegistration(String registration) {
		this.registration = registration;
	}


	public String getMark() {
		return mark;
	}


	public void setMark(String mark) {
		this.mark = mark;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getCodesInfringement() {
		return codesInfringement;
	}


	public void setCodesInfringement(String codes_infringement) {
		this.codesInfringement = codes_infringement;
	}


	@Override
	public String toString() {
		return "Owner document = " + ownerDocument + "\nRegistration =" + registration + "\nMark = "
				+ mark + "\nColor = " + color + "\nCodes infringement = " + codesInfringement + "\n";
	}
	
	
	
}
