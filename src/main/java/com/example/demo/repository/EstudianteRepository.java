package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.EstudianteService;

public interface EstudianteRepository {
	

	public void insertar(Estudiante estudiante);
	public void actualizar(Estudiante estudiante); 
	public void eliminar(String cedula);
	public Estudiante buscar(String cedula);
	
	//query
	
	public Estudiante seleccionarPorApellido(String apellido);
	public Estudiante seleccionarPorApellidoTyped(String apellido);

	public List<Estudiante>seleccionarListaPorApellido(String apellido);
	public Estudiante seleccionarPorApellidoyNombre(String apellido,String nombre);
	
	//name

	public Estudiante seleccionarPorApellidoNamed(String apellido);
	public Estudiante seleccionarPorApellidoNamedQuery(String apellido);

	//native
	public Estudiante selecionarPorApellidoNativedQuery(String apellido);
	public Estudiante selecionarPorApellidoNativedQueryNamed(String apellido);

	
	///
	public Estudiante seleccionarPorNombreNamedQuery(String apellido);
	public Estudiante selecionarPorNombreNativeQueryNamed(String nombre);
	///
	
	public Estudiante selecionarPorApellidoCriterioAPIQuery(String apellido);

///
	public Estudiante seleccionarDinamico(String nombre,String apellido,Double peso);
	
	public int eliminarPorNombre(String nombre);
	public int actualizarPorApellido(String nombre, String apellido);
	
	
	
	
}
