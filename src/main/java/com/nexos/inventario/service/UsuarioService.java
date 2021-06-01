package com.nexos.inventario.service;

import java.util.List;

import com.nexos.inventario.model.Usuario;

/**
 * Interfaz que se encarga de enmarcar los metodos que se implementaran
 * @author Anderson
 *
 */
public interface UsuarioService {

	/**
	 * Metodo encargado de obtener la lista de
	 * usuarios
	 * @return Lista de usuarios.
	 */
	public List<Usuario> getUsuarios();
	
	
	/**
	 * Metodo encargado de crear un usuario
	 * @param usuario: usuario
	 * @return Usuario creado
	 */
	public Usuario create(Usuario usuario);
	
	
	/**
	 * Metodo encargado de actualizar un usuario
	 * @param usuario: usuario
	 * @return Usuario actualizado
	 */
	public Usuario update(Usuario usuario);
	
	
	/**
	 * Metodo encargado de eliminar un usuario
	 * @param uuidUsuario: uuidUsuario
	 */
	public void delete(Integer uuidUsuario);

	
	/**
	 * Metodo encargado de retornar un usuario por su uuid
	 * @param uuid: uuid
	 * @return Usuario
	 */
	public Usuario byUuid(String uuid);
}
