package co.edu.unbosque.Proyecto_William.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import co.edu.unbosque.Proyecto_William.model.Infringement;
import co.edu.unbosque.Proyecto_William.model.Person;
import co.edu.unbosque.Proyecto_William.model.Vehicle;
import co.edu.unbosque.Proyecto_William.repository.InfringementDAO;
import co.edu.unbosque.Proyecto_William.repository.PersonDAO;
import co.edu.unbosque.Proyecto_William.repository.VehicleDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pdf")
public class PdfController {

	@Autowired
	PersonDAO perDAO;

	@Autowired
	VehicleDAO vehDAO;

	@Autowired
	InfringementDAO infDAO;

	@GetMapping("/generate")
	public void generatePdf(HttpServletResponse response) {
		try {
			ArrayList<Vehicle> lst = vehDAO.findAll();

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
			Color colorTitulo = new Color(52, 152, 219);
			Color colorCeldas = new Color(236, 240, 241);
			Color colorTituloTexto = new Color(0, 169, 92);
			
			document.open();

			Paragraph titulo = new Paragraph("Todos los partes", new Font(Font.HELVETICA, 16, Font.BOLD, colorTituloTexto));
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        titulo.setSpacingAfter(10f);
	        document.add(titulo);
			
			PdfPTable tabla = new PdfPTable(5);
			tabla.setWidthPercentage(100);
			tabla.setSpacingBefore(10f);
			tabla.setSpacingAfter(10f);

			String[] titulos = { "ID", "Cedula del dueño", "Matricula del vehiculo", "Codigo de infraccion",
					"Valor de multas" };

			for (int i = 0; i < titulos.length; i++) {
				PdfPCell celda = new PdfPCell();
				celda.setBackgroundColor(colorTitulo);
				celda.setPadding(5);
				celda.setPhrase(new Phrase(titulos[i], headerFont));
				tabla.addCell(celda);

			}

			int total = 0;

			for (int i = 0; i < lst.size(); i++) {
				tabla.addCell(crearCelda(lst.get(i).getId() + "", cellFont, colorCeldas));
				tabla.addCell(crearCelda(lst.get(i).getOwnerDocument() + "", cellFont, colorCeldas));
				tabla.addCell(crearCelda(lst.get(i).getRegistration(), cellFont, colorCeldas));
				tabla.addCell(crearCelda(lst.get(i).getCodesInfringement(), cellFont, colorCeldas));
				tabla.addCell(crearCelda(buscarInfraccion(lst.get(i)) + "", cellFont, colorCeldas));
				total += buscarInfraccion(lst.get(i));
			}

			PdfPTable tabla2 = new PdfPTable(2);
			tabla2.setWidthPercentage(100);
			tabla2.setSpacingBefore(10f);
			tabla2.setSpacingAfter(10f);

			PdfPCell celda = new PdfPCell();
			celda.setBackgroundColor(colorTitulo);
			celda.setPadding(5);
			celda.setPhrase(new Phrase("Valor total", headerFont));
			tabla2.addCell(celda);

			tabla2.addCell(crearCelda(total + "", cellFont, colorCeldas));

			document.add(tabla);
			document.add(tabla2);

			document.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=Todos los partes.pdf");

			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/generate2")
	public void generatePdf2(HttpServletResponse response) {
		try {
			ArrayList<Person> lst = perDAO.findAll();

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
			Color colorTitulo = new Color(52, 152, 219);
			Color colorCeldas = new Color(236, 240, 241);
			Color colorTituloTexto = new Color(0, 169, 92);

			document.open();
			
			Paragraph titulo = new Paragraph("Todos los vehiculos por persona", new Font(Font.HELVETICA, 16, Font.BOLD, colorTituloTexto));
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        titulo.setSpacingAfter(10f);
	        document.add(titulo);

			PdfPTable tabla = new PdfPTable(5);
			tabla.setWidthPercentage(100);
			tabla.setSpacingBefore(10f);
			tabla.setSpacingAfter(10f);

			String[] titulos = { "ID", "Cedula del dueño", "Nombre del dueño", "Matricula del vehiculos", "Codigo de infraccion"};

			for (int i = 0; i < titulos.length; i++) {
				PdfPCell celda = new PdfPCell();
				celda.setBackgroundColor(colorTitulo);
				celda.setPadding(5);
				celda.setPhrase(new Phrase(titulos[i], headerFont));
				tabla.addCell(celda);

			}

			for (int i = 0; i < lst.size(); i++) {
				tabla.addCell(crearCelda(lst.get(i).getId() + "", cellFont, colorCeldas));
				tabla.addCell(crearCelda(lst.get(i).getDocument() + "", cellFont, colorCeldas));
				tabla.addCell(crearCelda(lst.get(i).getName(), cellFont, colorCeldas));
				String temp [] = buscarVehiculos(lst.get(i).getDocument());
				tabla.addCell(crearCelda(temp[0], cellFont, colorCeldas));
				tabla.addCell(crearCelda(temp[1], cellFont, colorCeldas));
			}

			document.add(tabla);

			document.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=Todos los vehiculos por persona.pdf");

			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PdfPCell crearCelda(String texto, Font tipo_fuente, Color colorCelda) {
		PdfPCell cell = new PdfPCell(new Phrase(texto, tipo_fuente));
		cell.setBackgroundColor(colorCelda);
		cell.setPadding(5);
		return cell;
	}

	private String[] buscarVehiculos(Long document) {
		
		ArrayList<Vehicle> lst = vehDAO.findByOwnerDocument(document);
		String salida [] = new String[2];
		
		String ptmd = "";
		
		for (int i = 0; i < lst.size(); i++) {
			if (i == lst.size()-1) {
				ptmd += lst.get(i).getRegistration();
			} else {
				ptmd += lst.get(i).getRegistration() + " - ";
			}
		}
		salida[0] = ptmd;
		String temp = "";
		for (int i = 0; i < lst.size(); i++) {
			String aux [] = lst.get(i).getCodesInfringement().split("/");
			for (int j = 0; j < aux.length; j++) {
				if (aux[j] != null) {
					if (j == aux.length-1) {
						temp += aux[j];
					} else {
						temp += aux[j] + " - ";
					}
				}
			}
		}
		salida[1] = temp;
		return salida;
	}
	private int buscarInfraccion(Vehicle pt) {

		String[] ptmd = pt.getCodesInfringement().split("/");
		int salida = 0;
		for (int i = 0; i < ptmd.length; i++) {
			Infringement fill = infDAO.findByCode(ptmd[i]);
			if (fill != null) {
				salida += (fill.getValue()) * 1160000;
			}
		}

		return salida;
	}
}
