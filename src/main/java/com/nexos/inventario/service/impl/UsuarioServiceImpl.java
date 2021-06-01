package com.nexos.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.inventario.dao.CargoDao;
import com.nexos.inventario.dao.UsuarioDao;
import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.UsuarioService;

/**
 * Implementacion de los servicios de usuario
 * @author Anderson
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private CargoDao cargoDao;
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------

	
	@Override
	public List<Usuario> getUsuarios() {
		
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario create(Usuario usuario) {
		
		// Obtengo el cargo
		Cargo cargo = cargoDao.findByUuid(usuario.getCargo().getUuid());
		usuario.setCargo(cargo);
		
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer uuidUsuario) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo encargado de retornar un usuario por su uuid
	 */
	@Override
	public Usuario byUuid(String uuid) {
		return usuarioDao.findByUuid(uuid);
	}
	
}
