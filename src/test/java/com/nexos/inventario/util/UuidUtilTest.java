package com.nexos.inventario.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UuidUtilTest {
	
	/**
	 * Prueba que verifica que se genera el uuid
	 */
	@Test
	void testGenerateUuid() {
		
		String uuid = UuidUtil.generateUuid();
		
		assertTrue(!uuid.isEmpty());
	}

}
