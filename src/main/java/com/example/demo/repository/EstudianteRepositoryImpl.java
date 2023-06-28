package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl  implements EstudianteRepository{

	//me permite interactuar con la base de datos
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Estudiante estudiante) {
		this.entityManager.persist(estudiante);
		
	}

	
	@Override
	public void actualizar(Estudiante estudiante) {
		this.entityManager.merge(estudiante);
	}

	@Override
	public void eliminar(String cedula) {
		Estudiante estu = this.buscar(cedula);
		this.entityManager.remove(estu);
	}

	@Override
	public Estudiante buscar(String cedula) {
		
		return 	this.entityManager.find(Estudiante.class, cedula);
	}


	@Override
	public Estudiante seleccionarPorApellido(String apellido) {
		//sql
		//SELECT * FROM estudiante e WHERE 	estu_apellido=
		// JPQL
		//SELECT e FROM Estudiante e WHERE e.apellido=  (nombre del atributo o de la columna de la tabla)
		//:datoApellido es una variable que sera reemplazado por el dato que viene de db
		Query myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.apellido= :datoApellido");
		myQuery.setParameter("datoApellido", apellido);
		return (Estudiante) myQuery.getSingleResult();//lgetsingleResult le esta diciendo que retorne un unico resultado
	}


	@Override
	public List<Estudiante> seleccionarListaPorApellido(String apellido) {
		Query myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.apellido= :datoApellido");
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.getResultList();
	}


	@Override
	public Estudiante seleccionarPorApellidoyNombre(String apellido, String nombre) {
		
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.apellido= :datoApellido AND  e.nombre=:datoNombre");
		myQuery.setParameter("datoApellido", apellido);
		myQuery.setParameter("datoApellido", nombre);
		return (Estudiante) myQuery.getSingleResult();
	}


	@Override
	public Estudiante seleccionarPorApellidoTyped(String apellido) {
		// TODO Auto-generated method stub
		
		TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.apellido= :datoApellido", Estudiante.class);
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.getSingleResult();
	}


	@Override
	public Estudiante seleccionarPorApellidoNamed(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> typedQuery=this.entityManager.createNamedQuery("Estudiante.buscaPorApellido",Estudiante.class);
		typedQuery.setParameter("datoApellido", apellido);
		return typedQuery.getSingleResult();
	}


	@Override
	public Estudiante seleccionarPorApellidoNamedQuery(String apellido) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createNamedQuery("Estudiante.buscaPorApellido");
		myQuery.setParameter("datoApellido", apellido);
		
		return  (Estudiante) myQuery.getSingleResult();
	}


	@Override
	public Estudiante selecionarPorApellidoNativedQuery(String apellido) {
		// TODO Auto-generated method stub
		Query myQuery=this.entityManager.createNativeQuery("SELECT * FROM estudiante WHERE estu_apellido =:datoApellido",Estudiante.class);
		myQuery.setParameter("datoApellido", apellido);
		return (Estudiante) myQuery.getSingleResult();
	}


	@Override
	public Estudiante selecionarPorApellidoNativedQueryNamed(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> typedQuery =this.entityManager.createNamedQuery("Estudiante.buscaPorApellidoNative", Estudiante.class);
		typedQuery.setParameter("datoApellido", apellido);
		return typedQuery.getSingleResult();
	}


	@Override
	public Estudiante seleccionarPorNombreNamedQuery(String nombre) {
		Query myQuery = this.entityManager.createNamedQuery("Estudiante.buscaPorNombre");
		myQuery.setParameter("datoNombre", nombre);
		
		return  (Estudiante) myQuery.getSingleResult();
	}


	@Override
	public Estudiante selecionarPorNombreNativedQueryNamed(String nombre) {
		TypedQuery<Estudiante> typedQuery =this.entityManager.createNamedQuery("Estudiante.buscaPorNombreNative", Estudiante.class);
		typedQuery.setParameter("datoNombre", nombre);
		return typedQuery.getSingleResult();
	}


	@Override
	public Estudiante selecionarPorapellidoCriterioAPIQuery(String apellido) {
		
		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();
		//1.especificarel tipo de retorno que tiene mi query
		CriteriaQuery<Estudiante > myCriteriaQuery = myBuilder.createQuery(Estudiante.class);
		//2. Empezamos a crear el SQL
		//2.1 Definimos el FROM (Root)
		Root<Estudiante> miTablaFrom = myCriteriaQuery.from(Estudiante.class);// FROM estud
		//3. Construir las condiciones de mi SQL
		//las condiciones se las conoce como predicados 
		//condicion predicate
		//el primer atributo es el appelido del estudiante en miTablaFrom y el segundo es con lo que comparo
		//con el argumento del metodo
		Predicate condicionApellido = myBuilder.equal(miTablaFrom.get("apellido"), apellido);
		//4. Armamos mi SQL final 
		myCriteriaQuery.select(miTablaFrom).where(condicionApellido);
		TypedQuery<Estudiante> myQueryFinal = this.entityManager.createQuery(myCriteriaQuery);
		return myQueryFinal.getSingleResult();
	}

}
