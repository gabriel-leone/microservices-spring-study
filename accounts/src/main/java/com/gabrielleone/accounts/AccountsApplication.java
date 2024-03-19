package com.gabrielleone.accounts;

import com.gabrielleone.accounts.models.DTOs.AccountContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Gabriel Leone",
						email = "gabriel.leone@usp.br",
						url = "https://www.linkedin.com/in/gabriel-leone"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/gabriel-leone"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts microservice GitHub Repository",
				url = "https://github.com/gabriel-leone/microservices-spring-study"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
