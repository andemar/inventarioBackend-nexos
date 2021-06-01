package com.nexos.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nexos.inventario.dao.CargoDao;
import com.nexos.inventario.model.Cargo;
import com.nexos.inventario.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

	//--------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------

	@Autowired
	private CargoDao cargoDao;
	
	//--------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------

	/**
	 * Implementacion para la creacion de un cargo
	 */
	@Override
	public Cargo create(Cargo cargo) {
		
		if(cargoDao.findByTitulo(cargo.getTitulo()) != null)
			throw new DataIntegrityViolationException("El cargo ya existe");
		
		return cargoDao.save(cargo);
	}
	
	
	/**
	 * Implementacion para la obtencion de los cargos
	 */
	@Override
	public List<Cargo> getCargos() {
		return (List<Cargo>) cargoDao.findAll();
	}


}
