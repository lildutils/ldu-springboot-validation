package com.lildutils.springboot.validation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.lildutils.springboot.validation.controller.advice.LDuValidationsControllerAdvice;
import com.lildutils.springboot.validation.validator.LDuCustomValidator;

@Configuration
@ComponentScan(basePackageClasses =
{ LDuValidationsControllerAdvice.class, LDuCustomValidator.class })
public class LDuValidationConfigurer
{

}
