package com.nexos.inventario.service.impl;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nexos.inventario.dao.MercanciaDao;
import com.nexos.inventario.dao.UsuarioDao;
import com.nexos.inventario.dto.MercanciaUsuarioDTO;
import com.nexos.inventario.model.Mercancia;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.MercanciaService;
import com.nexos.inventario.util.FechasUtil;

/**
 * Implementacion de los servicios de Mercancia
 * @author Anderson
 *
 */
@Service
public class MercanciaServiceImpl implements MercanciaService{

	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------
	
	@Autowired
	private MercanciaDao mercanciaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de crear una mercancia
	 */
	@Override
	public void create(@Valid MercanciaUsuarioDTO mercanciaUsuarioDTO) {
		
		Usuario usuario;
		Mercancia mercancia = mercanciaUsuarioDTO.getMercancia();
		
		// Verificaciones del objeto
		verificarCreate(mercancia);
		
		// Se obtiene el usuario
		usuario = usuarioDao.findByUuid(mercanciaUsuarioDTO.getUsuario().getUuid());
		mercancia.setUsuario(usuario);
		
		mercanciaDao.save(mercancia);
		
	}
	
	/**
	 * Metodo encargado de actualizar una mercancia
	 */
	@Override
	public void update(MercanciaUsuarioDTO mercanciaUsuarioDTO) {
		
		Usuario usuario;
		Mercancia mercancia = mercanciaUsuarioDTO.getMercancia();
		Mercancia actual = mercanciaDao.findByUuid(mercancia.getUuid());
		
		// Verificaciones del objeto
		verificarUpdate(mercancia);
		
		// Se obtiene el usuario
		usuario = usuarioDao.findByUuid(mercanciaUsuarioDTO.getUsuario().getUuid());
		mercancia.setUsuario(usuario);
		
		// Valores inmutables
		mercancia.setIdMercancia(actual.getIdMercancia());
		mercancia.setUsuario(actual.getUsuario());
		
		// Fecha/usuario de update
		mercancia.setFechaModificacion(LocalDateTime.now());
		mercancia.setUsuarioModificacion(usuario);
		
		// Guardado
		mercanciaDao.save(mercancia);
		
	}
	
	/**
	 * Metodo encargado de actualizar una mercancia
	 */
	@Override
	public void delete(Integer idMercancia) {
		
		mercanciaDao.deleteById(idMercancia);
	}
	
	
	/**
	 * Metodo encargado de retornar la lista de mercancia en formato page
	 */
	@Override
	public Page<Mercancia> getMercanciaPage(Pageable pageable) {
		return mercanciaDao.findAll(pageable);
	}
	
	
	/**
	 * Metodo encargado de retornar una mercancia por su uuid
	 */
	@Override
	public Mercancia byUuid(String uuid) {
		
		return mercanciaDao.findByUuid(uuid);
	}
	
	
	//--------------------------------------------------------------------------------------------
	//Metodos internos
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Validaciones del objeto de mercancia
	 * @param mercancia: mercancia
	 */
	private void verificarCreate(Mercancia mercancia) {
		
		LocalDateTime fechaMercancia = mercancia.getFechaIngreso();
		if(!FechasUtil.fechaIgualOAntes(fechaMercancia))
			throw new DataIntegrityViolationException("La fecha de ingreso debe ser antes o igual a la actual");
		
		if(mercanciaDao.findByNombreProducto(mercancia.getNombreProducto()) != null)
			throw new DataIntegrityViolationException("La mercancia con nombre " + mercancia.getNombreProducto() + ", ya existe.");
	}
	
	
	private void verificarUpdate(Mercancia mercancia) {
		
		LocalDateTime fechaMercancia = mercancia.getFechaIngreso();
		if(!FechasUtil.fechaIgualOAntes(fechaMercancia))
			throw new DataIntegrityViolationException("La fecha de ingreso debe ser antes o igual a la actual");
	}

}
