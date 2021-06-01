package com.nexos.inventario.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------

	@Autowired
	private UsuarioService usuarioService;
	
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------

	/**
	 * Servicio que crea un usuario
	 * @param usuario
	 * @return Usuario
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
		
		usuarioService.create(usuario);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	
	/**
	 * Servicio que retorna la lista de usuarios
	 * @return List Usuario
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getUsuarios() {
		
		List<Usuario> usuarios = usuarioService.getUsuarios();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

}
