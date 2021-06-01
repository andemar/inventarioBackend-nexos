package com.nexos.inventario.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.UsuarioService;

class UsuarioControllerTest {
	
	private UsuarioService usuarioServiceMock = Mockito.mock(UsuarioService.class);
	
	@Autowired
	UsuarioController usuarioController = new UsuarioController(usuarioServiceMock);
	
	/**
	 * Datos iniciales
	 */
	@BeforeEach
	void setUp() {
		
		Cargo cargo1 = new Cargo();
		cargo1.setIdCargo(1);
		cargo1.setUuid("00000000-0000-0000-0000-000000000001");
		cargo1.setTitulo("cargo 1");
		
		Cargo cargo2 = new Cargo();
		cargo2.setIdCargo(2);
		cargo2.setUuid("00000000-0000-0000-0000-000000000002");
		cargo2.setTitulo("cargo 2");
		
		LocalDateTime fechaIngreso = LocalDateTime.of(2020, 01, 01, 00, 00);
		
		Usuario usuario1 = new Usuario();
		usuario1.setIdUsuario(1);
		usuario1.setNombre("usuario 1");
		usuario1.setUuid("00000000-0000-0000-0001-000000000001");
		usuario1.setCargo(cargo1);
		usuario1.setEdad(30);
		usuario1.setFechaIngreso(fechaIngreso);
		
		Usuario usuario2 = new Usuario();
		usuario2.setIdUsuario(2);
		usuario2.setNombre("usuario 2");
		usuario2.setUuid("00000000-0000-0000-0001-000000000002");
		usuario2.setCargo(cargo2);
		usuario2.setEdad(60);
		usuario2.setFechaIngreso(LocalDateTime.now());
		
		List<Usuario> mockUsuarios = new ArrayList<Usuario>();
		mockUsuarios.add(usuario1);
		mockUsuarios.add(usuario2);
		
		Mockito.when(usuarioServiceMock.getUsuarios()).thenReturn(mockUsuarios);
		
	}
	
	/**
	 * Test de obtencion de cargos
	 */
	@Test
	void testGetUsuarios() {
		
		List<Usuario> usuarios = usuarioController.getUsuarios().getBody();
		
		assertEquals(2, usuarios.size());
	}

}
