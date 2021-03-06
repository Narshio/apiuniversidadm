package com.ibm.academia.restapi.universidad.excepciones.handler;

import java.util.HashMap;
import java.util.Map;

import com.ibm.academia.restapi.universidad.excepciones.BadRequestExeption;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiUniversidadMException {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> noExisteException(NotFoundException exception) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = BadRequestExeption.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestExeption exception) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}
}