package es.maquina.discord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Clase encargada de albergar todos los beans de spring
 * 
 * @author MaQuiNa1995
 *
 */
@Configuration
//FIXME acotar mas la ruta
@ComponentScan(basePackages = "es.maquina.discord.bot")
public class Configuracion {

	/**
	 * Ruta del properties
	 */
	private static final String RUTA_PROPERTIES = "properties.cfg";

	/**
	 * Bean encargado de inyectar los valores de un archivo properties
	 * 
	 * @return PropertySourcesPlaceholderConfigurer
	 */
	@Bean("propertyHolder")
	public static PropertySourcesPlaceholderConfigurer propertyHolder() {

		PropertySourcesPlaceholderConfigurer propertyHolder = new PropertySourcesPlaceholderConfigurer();
		propertyHolder.setIgnoreResourceNotFound(Boolean.FALSE);
		propertyHolder.setLocation(new ClassPathResource(RUTA_PROPERTIES));

		return propertyHolder;
	}

}
