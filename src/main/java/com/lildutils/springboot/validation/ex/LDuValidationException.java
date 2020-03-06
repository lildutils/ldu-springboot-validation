package com.lildutils.springboot.validation.ex;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LDuValidationException extends RuntimeException
{
	private Map<String, String> validationErrors = new HashMap<>();

	public LDuValidationException()
	{
		super( "VM_ENTITY_INVALID" );
	}

	public LDuValidationException( Map<String, String> validationErrors )
	{
		super( "VM_ENTITY_INVALID" );
		this.validationErrors = validationErrors;
	}

	public LDuValidationException( String message, Map<String, String> validationErrors )
	{
		super( message );
		this.validationErrors = validationErrors;
	}

	public LDuValidationException( String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace )
	{
		super( message, cause, enableSuppression, writableStackTrace );
	}

	public LDuValidationException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public LDuValidationException( String message )
	{
		super( message );
	}

	public LDuValidationException( Throwable cause )
	{
		super( cause );
	}

	public Map<String, String> getValidationErrors()
	{
		return validationErrors;
	}

}
