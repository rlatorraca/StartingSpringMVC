package com.rlsp.faturaSpring;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class FaturaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaturaSpringApplication.class, args);
	}
	
	/**
	 * Diz que pretende-se usar as definicoes do BRASIL (datas, numeracao, etc)
	 */

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
