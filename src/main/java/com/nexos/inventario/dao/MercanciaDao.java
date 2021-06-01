package com.nexos.inventario.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.nexos.inventario.model.Mercancia;

/**
 * Interfaz que hereda los metodos de CrudRepository y contiene los
 * query personalizados.
 * @author Anderson
 *
 */
public interface MercanciaDao extends CrudRepository<Mercancia, Integer>{

	/**
	 * Metodo encargado de retornar la lista de mercancia en formato page
	 * @param pageable: pageable
	 * @return Page Mercancia
	 */
	Page<Mercancia> findAll(Pageable pageable);

	
	/**
	 * Metodo encargado de retornar una mercancia por su nombre
	 * @param nombreProducto: nombreProducto
	 * @return Mercancia
	 */
	Mercancia findByNombreProducto(String nombreProducto);


	/**
	 * Metodo encargado de retornar una mercancia por su uuid
	 * @param uuid: uuid
	 * @return Mercancia
	 */
	Mercancia findByUuid(String uuid);

}
