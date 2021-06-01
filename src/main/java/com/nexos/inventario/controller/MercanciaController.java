package com.nexos.inventario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.inventario.dto.MercanciaUsuarioDTO;
import com.nexos.inventario.model.Mercancia;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.MercanciaService;
import com.nexos.inventario.service.UsuarioService;

@RestController
@RequestMapping("/mercancias")
public class MercanciaController {
	
	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------
	
	@Autowired
	private MercanciaService mercanciaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public MercanciaController(MercanciaService mercanciaService, UsuarioService usuarioService) {
		this.mercanciaService = mercanciaService;
		this.usuarioService = usuarioService;
		
	}
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Servicio que crea una mercancia
	 * @param mercancia: mercancia
	 * @return ResponseEntity Mercancia
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mercancia> create(@Valid @RequestBody MercanciaUsuarioDTO mercanciaUsuarioDTO) {
		
		mercanciaService.create(mercanciaUsuarioDTO);
		
		return new ResponseEntity<Mercancia>(mercanciaUsuarioDTO.getMercancia(), HttpStatus.CREATED);
	}
	
	
	/**
	 * Serivico que actualiza una mercancia
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO 
	 * @return ResponseEntity Mercancia
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mercancia> update(@Valid @RequestBody MercanciaUsuarioDTO mercanciaUsuarioDTO) {
		
		verificarUpdate(mercanciaUsuarioDTO);
		
		mercanciaService.update(mercanciaUsuarioDTO);
		
		return new ResponseEntity<Mercancia>(mercanciaUsuarioDTO.getMercancia(), HttpStatus.OK);
	}
	
	
	/**
	 * Serivico que elimina una mercancia
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO 
	 * @return ResponseEntity Mercancia
	 */
	@DeleteMapping(value = "/{uuidMercancia}/{uuidUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable String uuidMercancia, @PathVariable String uuidUsuario) {
		
		Integer idMercancia = verificarDelete(uuidMercancia, uuidUsuario);
		
		mercanciaService.delete(idMercancia);
	}


	/**
	 * Servicio que retorna la lista de mercancias en modalidad pageable
	 * @param pageable: pageable
	 * @return Page Mercancia
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Mercancia>> getMercanciaPage(Pageable pageable) {
		
		Page<Mercancia> lista;
		
		lista = mercanciaService.getMercanciaPage(pageable);
		
		return new ResponseEntity<Page<Mercancia>>(lista, HttpStatus.OK);
		
	}
	
	
	//--------------------------------------------------------------------------------------------
	//Metodos internos
	//--------------------------------------------------------------------------------------------
	
	
	/**
	 * Verifica que el usuario que creo la mercancia, corresponda al que hizo la peticion.
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO
	 */
	private void verificarUpdate(MercanciaUsuarioDTO mercanciaUsuarioDTO) {
		
		Mercancia mercanciaActual = mercanciaUsuarioDTO.getMercancia();
		mercanciaActual = mercanciaService.byUuid(mercanciaActual.getUuid());
		
		if(mercanciaActual == null)
			throw new DataIntegrityViolationException("La mercancia no existe");
				
	}
	
	
	/**
	 * Verifica que el usuario que creo la mercancia, corresponda al que hizo la peticion.
	 * @param mercanciaUsuarioDTO: mercanciaUsuarioDTO
	 */
	private Integer verificarDelete(String uuidMercancia, String uuidUsuario) {
		
		Mercancia mercanciaActual = mercanciaService.byUuid(uuidMercancia);
		
		if(mercanciaActual == null)
			throw new DataIntegrityViolationException("La mercancia no existe");
		
		Usuario usuarioActual = mercanciaActual.getUsuario();
		
		Usuario usuarioPeticion = usuarioService.byUuid(uuidUsuario);
		
		if(usuarioActual.getIdUsuario() != usuarioPeticion.getIdUsuario())
			throw new DataIntegrityViolationException("Usuario sin permisos");
		
		return mercanciaActual.getIdMercancia();
	}

}
