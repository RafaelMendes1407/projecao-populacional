package br.com.tt.projecaopopulacional.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
public class ProjecaoPopulacionalConfiguration {

	@Value("${tt.api.projecao}")
	private String apiUrl;

	@Value("${tt.api.log.path}")
	private String logPath;
}
