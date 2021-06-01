package com.nexos.inventario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexos.inventario.util.UuidUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase describe los atributos del modelo Cargo
 * @author Anderson
 *
 */
@Entity
@Getter
@Setter
@Table(name = "cargo")
public class Cargo {
	
	//-----------------------------------------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * Atributo de identificador privado del cargo
	 */
	@Id
	@JsonIgnore()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCargo;
	
	
	/**
	 * Atributo de identificador publico del cargo
	 */
	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid = UuidUtil.generateUuid();
	
	
	/**
	 * Atributo nombre del usuario
	 */
	@NotNull(message = "Debe haber un titulo")
	@NotEmpty(message = "Debe haber un titulo")
	@Column(name = "titulo", nullable = false, length = 255, unique = true)
	private String titulo;

	
}
