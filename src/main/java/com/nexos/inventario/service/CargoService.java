package com.nexos.inventario.service;

import java.util.List;

import com.nexos.inventario.model.Cargo;

/**
 * Interfaz que se encarga de enmarcar los metodos que se implementaran
 * @author Anderson
 *
 */
public interface CargoService {
	
	/**
	 * Servicio que crea un cargo
	 * @param cargo: cargo
	 * @return Cargo creado
	 */
	public Cargo create(Cargo cargo);

	/**
	 * Servicio que retorna la lista de cargos
	 * @return List Cargo
	 */
	public List<Cargo> getCargos();
}
