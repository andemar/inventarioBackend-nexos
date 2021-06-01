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
 * Esta clase describe los atributos del modelo Mercancia
 * @author Anderson
 *
 */
@Entity
@Getter
@Setter
@Table(name = "mercancia")
public class Mercancia {

	//-----------------------------------------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * Atributo de identificador privado de la mercancia
	 */
	@Id
	@JsonIgnore()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMercancia;
	
	
	/**
	 * Atributo de identificador publico de la mercancia
	 */
	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid = UuidUtil.generateUuid();
	
	
	/**
	 * Atributo nombreProducto de la mercancia
	 */
	@NotNull(message = "Debe haber un nombre producto")
	@NotEmpty(message = "Debe haber un nombre producto")
	@Column(name = "nombre_producto", nullable = false, length = 255, unique = true)
	private String nombreProducto;
	
	
	/**
	 * Atributo cantidad de la mercancia
	 */
	@NotNull(message = "Debe existir una cantidad")
	@Min(value = 0)
	@Column(name = "cantidad", nullable = false, length = 7)
	private Integer cantidad;
	
	
	/**
	 * Atributo de fecha de ingreso de la mercancia
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDateTime fechaIngreso;
	
	
	/**
	 * Atributo usuario quien creo la mercancia
	 */
	@NotNull(message = "Debe haber un usuario")
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name="FK_mercanciar_usuario"))
	private Usuario usuario;
	
	
	/**
	 * Atributo usuario quien modifico por ultima vez la mercancia
	 */
	@ManyToOne
	@JoinColumn(name = "id_usuario_modificacion", nullable = true, foreignKey = @ForeignKey(name="FK_mercanciar_usuario_modificacion"))
	private Usuario usuarioModificacion;
	
	
	/**
	 * Atributo de fecha de modificacion de la mercancia
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;
	
	
}
