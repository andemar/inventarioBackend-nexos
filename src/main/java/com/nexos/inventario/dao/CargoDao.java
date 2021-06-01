package com.nexos.inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.nexos.inventario.model.Cargo;

/**
 * Interfaz que hereda los metodos de CrudRepository y contiene los
 * query personalizados.
 * @author Anderson
 *
 */
public interface CargoDao extends CrudRepository<Cargo, Integer>{
	
	/**
	 * Servicio que retorna un cargo por su titulo
	 * @param titulo: titulo
	 * @return Cargo
	 */
	Cargo findByTitulo(String titulo);
	
	
	/**
	 * Servicio que retorna un cargo por su uuid
	 * @param uuid: uuid
	 * @return Cargo
	 */
	Cargo findByUuid(String uuid);

}
