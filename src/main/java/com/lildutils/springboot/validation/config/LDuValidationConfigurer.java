package com.lildutils.springboot.validation.config;

import javax.validation.Validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lildutils.springboot.validation.validator.LDuCustomValidator;

@Configuration
public class LDuValidationConfigurer
{
	@Bean("lduCustomValidator")
	public LDuCustomValidator getLDuCustomValidator()
	{
		return new LDuCustomValidator( Validation.buildDefaultValidatorFactory().getValidator() );
	}

}
