package com.nexos.inventario.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.service.CargoService;

class CargoControllerTest {
	
	private CargoService cargoServiceMock = Mockito.mock(CargoService.class);
	
	@Autowired
	CargoController cargoController = new CargoController(cargoServiceMock);
	
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
		
		List<Cargo> mockCargos = new ArrayList<Cargo>();
		mockCargos.add(cargo1);
		mockCargos.add(cargo2);
		
		Mockito.when(cargoServiceMock.getCargos()).thenReturn(mockCargos);
		
		Mockito.when(cargoServiceMock.create(cargo1)).thenReturn(cargo1);
		
	}

	/**
	 * Test de creacion de cargo
	 */
	@Test
	void testCreate() {
		
		Cargo create = new Cargo();
		create.setIdCargo(1);
		create.setUuid("00000000-0000-0000-0000-000000000001");
		create.setTitulo("cargo 1");
		
		Cargo cargo = cargoController.create(create).getBody();
		
		assertNotNull(cargo);
	}

	/**
	 * Test de obtencion de cargos
	 */
	@Test
	void testGetCargos() {

		List<Cargo> cargos = cargoController.getCargos().getBody();
		
		assertEquals(2, cargos.size());
	}

}
