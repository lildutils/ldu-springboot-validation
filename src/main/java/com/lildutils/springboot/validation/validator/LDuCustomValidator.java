package com.lildutils.springboot.validation.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.lildutils.springboot.validation.ex.LDuValidationException;

@Component
public class LDuCustomValidator
{
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private <T> Map<String, String> doValidation( T dto, Class<?> groups )
	{
		final Map<String, String> results = new HashMap<>();
		Set<ConstraintViolation<T>> violations;
		if( groups != null )
		{
			violations = validator.validate( dto, groups );
		}
		else
		{
			violations = validator.validate( dto );
		}
		for( ConstraintViolation<?> violation : violations )
		{
			final Spliterator<Node> spliterator = violation.getPropertyPath().spliterator().trySplit();
			final List<String> propertyParts = new ArrayList<>();
			spliterator.forEachRemaining( ( n ) -> propertyParts.add( n.getName() ) );
			results.put( propertyParts.get( propertyParts.size() - 1 ), violation.getMessage() );
		}
		return results;
	}

	public <T> void validate( T dto )
	{
		final Map<String, String> validationErrors = doValidation( dto, null );
		if( !validationErrors.isEmpty() )
		{
			throw new LDuValidationException( validationErrors );
		}
	}

	public <T> void validate( T dto, Class<?> groups )
	{
		final Map<String, String> validationErrors = doValidation( dto, groups );
		if( !validationErrors.isEmpty() )
		{
			throw new LDuValidationException( validationErrors );
		}
	}

}
