package br.com.tt.projecaopopulacional.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.tt.projecaopopulacional.ProjecaopopulacionalApplication;
import br.com.tt.projecaopopulacional.domain.dto.ProjecaoPopulacionalDTO;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("ProjecaoService")
@SpringBootTest(classes = { ProjecaopopulacionalApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProjecaoServiceTest {

	private static final String RESOURCE_URL = "/projecoes";

	@Value("${tt.api.log.path}")
	private String logPath;

	@Autowired
	private TestRestTemplate restTemplate;

	@Nested
	@DisplayName("GET(200 /projecoes")
	class GetSuccess {

		@Test
		@DisplayName("Must return a projection at a future date")
		void getProjecao() {

			LocalDateTime data = LocalDateTime.now().plusYears(2L);

			StringBuilder url = new StringBuilder(RESOURCE_URL);
			url.append("?dataProjecao=").append(data.toString());

			ResponseEntity<ProjecaoPopulacionalDTO> projecao = restTemplate.exchange(url.toString(), HttpMethod.GET, null, ProjecaoPopulacionalDTO.class);

			assertEquals(HttpStatus.OK, projecao.getStatusCode());
			assertTrue(Objects.nonNull(projecao.getBody()));
			assertEquals(data.getDayOfMonth(), projecao.getBody().getDataPopulacaoAtual().getDayOfMonth());
			assertTrue(data.isEqual(projecao.getBody().getDataProjecao()));
		}
	}

	@Nested
	@DisplayName("GET(400) /projecoes")
	static class GetFailure {

	}

	@Test
	@DisplayName("Must not return a projection at a paste date")
	void getProjecao() {

		LocalDateTime data = LocalDateTime.now().minusMonths(2L);

		StringBuilder url = new StringBuilder(RESOURCE_URL);
		url.append("?dataProjecao=").append(data.toString());

		ResponseEntity<ProjecaoPopulacionalDTO> projecao = restTemplate.exchange(url.toString(), HttpMethod.GET, null, ProjecaoPopulacionalDTO.class);

		assertEquals(HttpStatus.BAD_REQUEST, projecao.getStatusCode());
	}

	@AfterAll
	void deleteLogFile() {
		File log = new File(logPath);
		log.delete();
	}
}
