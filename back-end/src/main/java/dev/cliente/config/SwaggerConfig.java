package dev.cliente.config;


import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket MobitApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("dev.cliente"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
				"Desafio Mobit", 
				"API REST para os endpoints de contatos",
				"1.0", 
				"código simples", 
				new Contact("Leonardo Brendo Gomes Nascimento", "https://www.linkedin.com/in/leonardo-gomes777/", "leonardobrendoti@gmail.com"), 
				"Engenheiro de software", 
				"Mestre em Ciência da Computação", new ArrayList<VendorExtension>()
		);
		
		return apiInfo;
	}
	
}