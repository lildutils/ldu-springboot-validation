package com.lildutils.springboot.validation.controller.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Spliterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lildutils.springboot.validation.ex.LDuValidationException;

@ControllerAdvice
public class LDuValidationsControllerAdvice extends ResponseEntityExceptionHandler
{
	@Override
	protected @ResponseBody ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus s, WebRequest request )
	{
		final Map<String, String> validationErrors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach( error -> {
			final String fieldName = ((FieldError) error).getField();
			final String errorMessage = error.getDefaultMessage();
			validationErrors.put( fieldName, errorMessage );
		} );
		return super.handleExceptionInternal( ex, validationErrors, null, HttpStatus.BAD_REQUEST, null );
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	ResponseEntity<?> handleConstraintViolationException( ConstraintViolationException e )
	{
		final Map<String, String> validationErrors = new HashMap<>();
		for( ConstraintViolation<?> violation : e.getConstraintViolations() )
		{
			final Spliterator<Node> spliterator = violation.getPropertyPath().spliterator().trySplit();
			final List<String> propertyParts = new ArrayList<>();
			spliterator.forEachRemaining( ( n ) -> propertyParts.add( n.getName() ) );
			validationErrors.put( propertyParts.get( propertyParts.size() - 1 ), violation.getMessage() );
		}
		return super.handleExceptionInternal( e, validationErrors, null, HttpStatus.BAD_REQUEST, null );
	}

	@ExceptionHandler(LDuValidationException.class)
	@ResponseBody
	ResponseEntity<?> handleLDuValidationException( LDuValidationException e )
	{
		final Map<String, String> validationErrors = new HashMap<>();
		for( Entry<String, String> entry : e.getValidationErrors().entrySet() )
		{
			validationErrors.put( entry.getKey(), entry.getValue() );
		}
		return super.handleExceptionInternal( e, validationErrors, null, HttpStatus.BAD_REQUEST, null );
	}

}
