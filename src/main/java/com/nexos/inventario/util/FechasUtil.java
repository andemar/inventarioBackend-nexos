package com.nexos.inventario.util;

import java.time.LocalDateTime;

public class FechasUtil {

	/**
	 * Verifica que la fecha dada, es anterior o igual a la actual
	 * @param fecha: Fecha
	 * @return boolean
	 */
	public static boolean fechaIgualOAntes(LocalDateTime fecha) {
		
		LocalDateTime now = LocalDateTime.now();
		
		return (fecha.isBefore(now) || fecha.isEqual(now));
		
	}
}
