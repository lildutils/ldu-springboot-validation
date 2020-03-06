package com.lildutils.springboot.validation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.lildutils.springboot.validation.controller.advice.LDuValidationsControllerAdvice;
import com.lildutils.springboot.validation.validator.LDuCustomValidator;

@Configuration
@ComponentScan(basePackageClasses = LDuValidationsControllerAdvice.class)
public class LDuValidationConfigurer
{
	@Bean("validator")
	public LDuCustomValidator getDTOValidator()
	{
		return new LDuCustomValidator();
	}

}
