package com.lildutils.springboot.validation.messages;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface LDuValidationMessages
{
	String	VM_NOT_NULL				= "VM_NOT_NULL";
	String	VM_NOT_BLANK			= "VM_NOT_BLANK";
	String	VM_NOT_EMPTY			= "VM_NOT_EMPTY";
	String	VM_EMAIL				= "VM_INVALID_PATTERN";
	String	VM_INVALID_LENGTH_MIN	= "VM_INVALID_LENGTH_MIN";
	String	VM_INVALID_LENGTH_MAX	= "VM_INVALID_LENGTH_MAX";
	String	VM_INVALID_SIZE			= "VM_INVALID_SIZE";

	static void printAll()
	{
		Arrays.asList( LDuValidationMessages.class.getDeclaredFields() ).forEach( f -> {
			try
			{
				System.out.println( LDuValidationMessages.class.getField( f.getName() ).get( f.getName() ) );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		} );
	}

	static void printAllAsJSON()
	{
		System.out.println( "{" );
		Field[] fields = LDuValidationMessages.class.getDeclaredFields();
		int l = fields.length;
		for( int i = 0; i < l; i++ )
		{
			try
			{
				final String field = LDuValidationMessages.class.getField( fields[i].getName() )
						.get( fields[i].getName() ).toString();
				if( i < (l - 1) )
				{
					System.out.println( "\"" + field + "\": \"" + field + "\"," );
				}
				else
				{
					System.out.println( "\"" + field + "\": \"" + field + "\"" );
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		System.out.println( "}" );
	}

	static void main( String[] args )
	{
//		LDuValidationMessages.printAll();
		LDuValidationMessages.printAllAsJSON();
	}
}
