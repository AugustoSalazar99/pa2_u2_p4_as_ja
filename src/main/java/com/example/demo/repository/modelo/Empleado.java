package com.example.demo.repository.modelo;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
//cuando no pongo el nombre de la table pone por defecto el nombre de la clases en minuscula
@Table(name = "empleado")

public class Empleado {
	@GeneratedValue(generator = "seq_empleado", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_empleado", sequenceName = "seq_empleado", allocationSize = 1)

	@Id
	@Column(name = "empl_id")
	private Integer id;
	@Column(name = "empl_sueldo")
	private BigDecimal sueldo;
	@Column(name = "empl_cargo")
	private String cargo;
	// one-to-one
	@OneToOne(cascade = CascadeType.ALL)//siempre all,si pide que afecta el cuidadano que se actualiza merch
	//como baja la pk a m segundad tabla y se converta en fk
	@JoinColumn(name="empl_ciudadano_id")//empezar por empl por que pertenece a empleado
	private Ciudadano ciudadano;

	// setyget

	public Ciudadano getCuidadano() {
		return ciudadano;
	}

	public void setCuidadano(Ciudadano cuidadano) {
		this.ciudadano = cuidadano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", sueldo=" + sueldo + ", cargo=" + cargo + "]";
	}

}