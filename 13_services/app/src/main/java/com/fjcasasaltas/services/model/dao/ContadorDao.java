package com.fjcasasaltas.services.model.dao;

import com.fjcasasaltas.services.model.Contador;

public interface ContadorDao {

	/**
	 * Buscar el contador
	 * @return contador
	 */
	public Contador buscar();
	
	/**
	 * Modificar un contador
	 * @param elemento a modificar
	 */
	public void modificar(Contador contador);

}
