package br.com.tt.projecaopopulacional.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import br.com.tt.projecaopopulacional.configuration.ProjecaoPopulacionalConfiguration;
import br.com.tt.projecaopopulacional.domain.model.ProjecaoPopulacional;
import br.com.tt.projecaopopulacional.exception.ProjecaoException;

import reactor.core.publisher.Mono;

@Slf4j
@Service
public class HttpService {

	@Autowired
	private ProjecaoPopulacionalConfiguration configUrl;

	public ProjecaoPopulacional getInfoProjecao() {
		WebClient webClient = WebClient.create();
		Mono<ProjecaoPopulacional> projecao = webClient.get() //
				.uri(configUrl.getApiUrl()) //
				.retrieve() //
				.bodyToMono(ProjecaoPopulacional.class) //
				.onErrorResume(WebClientException.class, e -> Mono.error(ProjecaoException::new));

		return projecao.block();
	}
}
