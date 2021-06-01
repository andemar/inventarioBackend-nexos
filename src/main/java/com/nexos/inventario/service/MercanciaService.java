package com.nexos.inventario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexos.inventario.dto.MercanciaUsuarioDTO;
import com.nexos.inventario.model.Mercancia;

/**
 * Interfaz que se encarga de enmarcar los metodos que se implementaran
 * @author Anderson
 *
 */
public interface MercanciaService {

	/**
	 * Metodo encargado de crear una mercancia
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO 
	 */
	public void create(MercanciaUsuarioDTO mercanciaUsuarioDTO);
	
	
	/**
	 * Metodo encargado de actualizar una mercancia
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO 
	 */
	public void update(MercanciaUsuarioDTO mercanciaUsuarioDTO);


	/**
	 * Metodo encargado de leiminar una mercancia
	 * @param mercanciaUsuarioDTO
	 */
	public void delete(Integer idMercancia);
	
	
	/**
	 * Metodo encargado de retornar la lista de mercancia en formato page
	 * @param pageable: Pageable
	 * @return Page Mercancia
	 */
	public Page<Mercancia> getMercanciaPage(Pageable pageable);

	
	/**
	 * Metodo encargado de retornar una mercancia por su uuid
	 * @param uuid: uuid
	 * @return Mercancia
	 */
	public Mercancia byUuid(String uuid);



	
}
