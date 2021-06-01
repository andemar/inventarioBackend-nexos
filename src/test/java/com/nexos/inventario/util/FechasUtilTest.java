package com.nexos.inventario.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FechasUtilTest {

	/**
	 * Prueba que verifica si una fecha es anterior a la actual en maquina
	 */
	@Test
	void testFechaIgualOAntes() {
		
		// La prueba se ha de hacer en una maquina con una fecha posterior a esta
		LocalDateTime before = LocalDateTime.of(2020, 01, 01, 00, 00);
		
		boolean igualOAntes = FechasUtil.fechaIgualOAntes(before);
		
		assertTrue(igualOAntes);
	}

}
