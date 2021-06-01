package com.nexos.inventario.dto;

import com.nexos.inventario.model.Mercancia;
import com.nexos.inventario.model.Usuario;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que servira principalmente para la creacion, edicion y eliminacion
 * de mercancias.
 * @author Anderson
 *
 */
@Getter
@Setter
public class MercanciaUsuarioDTO {
	
	private Mercancia mercancia;
	
	private Usuario usuario;

}
