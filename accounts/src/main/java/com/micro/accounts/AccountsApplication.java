package com.micro.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.micro.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value= {AccountsContactInfoDto.class})
@EnableFeignClients
/*@ComponentScans({ @ComponentScan("com.different.accounts.controller") })
@EnableJpaRepositories("com.differnet.accounts.repository")
@EntityScan("com.diffenrent.accounts.model")*/
@OpenAPIDefinition(
		info = @Info(
					title = "Accounts microservice REST API Documentation",
					description = "EazyBank Accounts microservice REST API Documentation",
					version = "v1",
					contact = @Contact(
							name = "Tanmay Kumar",
							email = "tanmaykumar950@gmail.com",
							url = "https://www.linkedin/com/in/tanmayumar1707"
					),
					license = @License(
							name = "Apache 2.0",
							url = ""
					)
				),
		externalDocs = @ExternalDocumentation(
				description =  "EazyBank Accounts microservice REST API Documentation",
				url = ""
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
