package com.example.demo.banco.repository;

import com.example.demo.banco.repository.modelo.Transferencia;

public interface TransferenciaRepository {
	public void insertar(Transferencia transferencia);

	public void actualizar(Transferencia transferencia);

	public void eliminar(String numero);

	public Transferencia seleccionarPorNumero(String numero);

}
