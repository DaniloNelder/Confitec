package br.com.confitec.teste;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * 
 * @author Danilo Nelder
 *
 */

@EnableAutoConfiguration
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class TesteApplication {

	private static Logger logger = LoggerFactory.getLogger(TesteApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a API");
		SpringApplication.run(TesteApplication.class, args);
		logger.info("API iniciada!");
	}

}
