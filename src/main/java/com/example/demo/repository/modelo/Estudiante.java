package com.example.demo.repository.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name="estudiante")
@Entity
@NamedQuery(name = "Estudiante.buscaPorApellido", query = "SELECT e FROM Estudiante e WHERE e.apellido= :datoApellido")
public class Estudiante {
	//para hacer secuencia
	@GeneratedValue(generator = "seq_estudinate", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_estudiante", sequenceName = "seq_estudiante", allocationSize = 1)
	@Id
	@Column(name="estu_id")
	private Integer id;
	
	@Column(name= "estu_cedula")
	
	private String cedula;
	
	@Column(name="estu_nombre")
	private String nombre;
	
	@Column(name="estu_apellido")
	private String apellido;

	
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Estudiante [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	

}
