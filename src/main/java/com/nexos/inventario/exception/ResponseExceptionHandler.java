package com.nexos.inventario.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class) //ESTE ERROR ES DE TIPO GENERAL CUAL QUIER ERROR
	public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() + " - All", request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) //ESTE ERROR ES DE TIPO GENERAL CUAL QUIER ERROR
	public final ResponseEntity<Object> ManejarUniqueExcepciones(Exception ex, WebRequest request){
		
		String mensaje = "";
		mensaje = ex.getMessage();
		//mensaje = "Data Integrity Error";
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), mensaje, request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ModeloNotFoundException.class) //ESTE ERROR ES EL PERFONALIZADO CUANDO ALGO NO SE ENCUENTRA
	public final ResponseEntity<Object> manejarModeloExcepciones(ModeloNotFoundException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() + " - Not found", request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override //ESTE ERROR ES DE TIPO DATA CUANDO LA INFO NO ES VALIDA SEGUN LOS CONSTRAINS Q DEFINI EN EL MODELO
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {			
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Datos enviados no validos", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	}

}