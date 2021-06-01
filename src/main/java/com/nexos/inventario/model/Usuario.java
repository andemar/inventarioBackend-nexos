package com.nexos.inventario.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nexos.inventario.util.UuidUtil;

import lombok.Getter;
import lombok.Setter;


/**
 * Esta clase describe los atributos del modelo Usuario
 * @author Anderson
 *
 */
@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

	//-----------------------------------------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * Atributo de identificador privado del usuario
	 */
	@Id
	@JsonIgnore()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	
	/**
	 * Atributo de identificador publico del usuario
	 */
	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid = UuidUtil.generateUuid();
	
	
	/**
	 * Atributo nombre del usuario
	 */
	@NotNull(message = "Debe haber un nombre")
	@NotEmpty(message = "Debe haber un nombre")
	@Column(name = "nombre", nullable = false, length = 255, unique = true)
	private String nombre;
	
	
	/**
	 * Atributo edad del usuario
	 */
	@NotNull(message = "Debe haber una edad")
	@Min(value = 1, message = "La edad debe ser mayor a 0")
	@Max(value = 150, message = "La edad debe ser menor a 150")
	@Column(name = "edad", nullable = false, length = 3)
	private Integer edad;
	
	
	/**
	 * Atributo de fecha de ingreso del usuario
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDateTime fechaIngreso;
	
	
	/**
	 * Atributo cargo del usuario
	 */
	@NotNull(message = "Debe haber un cargo")
	@ManyToOne
	@JoinColumn(name = "id_cargo", nullable = false, foreignKey = @ForeignKey(name="FK_usuario_cargo"))
	private Cargo cargo;
	
	
}
