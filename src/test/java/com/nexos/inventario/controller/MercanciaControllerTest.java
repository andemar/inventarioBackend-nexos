package com.nexos.inventario.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.model.Mercancia;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.service.MercanciaService;
import com.nexos.inventario.service.UsuarioService;

class MercanciaControllerTest {
	
	private MercanciaService mercanciaServiceMock = Mockito.mock(MercanciaService.class);
	private UsuarioService usuarioServiceMock = Mockito.mock(UsuarioService.class);
	
	@Autowired
	MercanciaController mercanciaController = new MercanciaController(mercanciaServiceMock, usuarioServiceMock);
	
	/**
	 * Datos iniciales
	 */
	@BeforeEach
	void setUp() {
		
		Cargo cargo1 = new Cargo();
		cargo1.setIdCargo(1);
		cargo1.setUuid("00000000-0000-0000-0000-000000000001");
		cargo1.setTitulo("cargo 1");
		
		LocalDateTime fechaIngreso = LocalDateTime.of(2020, 01, 01, 00, 00);
		
		Usuario usuario1 = new Usuario();
		usuario1.setIdUsuario(1);
		usuario1.setNombre("usuario 1");
		usuario1.setUuid("00000000-0000-0000-0001-000000000001");
		usuario1.setCargo(cargo1);
		usuario1.setEdad(30);
		usuario1.setFechaIngreso(fechaIngreso);
		
		Mercancia mercancia1 = new Mercancia();
		mercancia1.setCantidad(1);
		mercancia1.setFechaIngreso(fechaIngreso);
		mercancia1.setIdMercancia(1);
		mercancia1.setNombreProducto("mercancia 1");
		mercancia1.setUsuario(usuario1);
		mercancia1.setUuid("00000000-0000-0000-0002-000000000001");
		
		Mercancia mercancia2 = new Mercancia();
		mercancia2.setCantidad(2);
		mercancia2.setFechaIngreso(fechaIngreso);
		mercancia2.setIdMercancia(2);
		mercancia2.setNombreProducto("mercancia 2");
		mercancia2.setUsuario(usuario1);
		mercancia2.setUuid("00000000-0000-0000-0002-000000000002");
		
		List<Mercancia> mockMercancias = new ArrayList<Mercancia>();
		mockMercancias.add(mercancia1);
		mockMercancias.add(mercancia2);
		
		Mockito.when(usuarioServiceMock.byUuid("00000000-0000-0000-0001-000000000001")).thenReturn(usuario1);
		
		Pageable pageable = PageRequest.of(0, 20);
		final Page<Mercancia> page = new PageImpl<Mercancia>(mockMercancias);
		
		Mockito.when(mercanciaServiceMock.getMercanciaPage(pageable)).thenReturn(page);
	}
	
	/**
	 * Test que de obtencion de mercancias
	 */
	@Test
	void testGetMercanciaPage() {
		
		Pageable pageable = PageRequest.of(0, 20);
		
		Page<Mercancia> mercancias = mercanciaController.getMercanciaPage(pageable).getBody();
				
		assertEquals(2, mercancias.getTotalElements());
	}

}
