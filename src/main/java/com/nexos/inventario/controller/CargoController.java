package com.nexos.inventario.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.service.CargoService;

@RestController
@RequestMapping("/cargos")
@EnableWebMvc
public class CargoController {

	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------

	@Autowired
	private CargoService cargoService;
	
	
	public CargoController(CargoService cargoService) {
		this.cargoService = cargoService;
	}
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Servicio que crea un cargo
	 * @param titulo: Titlo del cargo a crear
	 * @return Cargo
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cargo> create(@Valid @RequestBody Cargo cargo) {
		
		cargoService.create(cargo);
		
		return new ResponseEntity<Cargo>(cargo, HttpStatus.CREATED);
	}
	
	
	/**
	 * Servicio que retorna la lista de cargos
	 * @return List Cargo
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cargo>> getCargos() {
		
		List<Cargo> cargos = cargoService.getCargos();
		
		return new ResponseEntity<List<Cargo>>(cargos, HttpStatus.OK);
	}
}
