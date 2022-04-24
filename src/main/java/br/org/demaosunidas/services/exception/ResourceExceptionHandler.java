package br.org.demaosunidas.services.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
//Tipo um interceptor
public class ResourceExceptionHandler {
	
	//Intercepta os erros objectNotFound.
	@ExceptionHandler(ObjectNotFoudException.class)
	public ResponseEntity<String> objectNotFound(ObjectNotFoudException e, HttpServletRequest request) {
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{not_found}");
	}
	
	
}