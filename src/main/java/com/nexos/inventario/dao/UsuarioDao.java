package com.nexos.inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.nexos.inventario.model.Usuario;

/**
 * Interfaz que hereda los metodos de CrudRepository y contiene los
 * query personalizados.
 * @author Anderson
 *
 */
public interface UsuarioDao extends CrudRepository<Usuario, Integer>{

	/**
	 * Servicio que retorna un usuario por su uuid
	 * @param uuid: uuid
	 * @return Usuario
	 */
	Usuario findByUuid(String uuid);
	

}
