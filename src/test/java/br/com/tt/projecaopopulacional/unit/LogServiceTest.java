package br.com.tt.projecaopopulacional.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import br.com.tt.projecaopopulacional.ProjecaopopulacionalApplication;
import br.com.tt.projecaopopulacional.domain.dto.LogConsultaDTO;
import br.com.tt.projecaopopulacional.domain.dto.ProjecaoPopulacionalDTO;
import br.com.tt.projecaopopulacional.exception.LogClearException;
import br.com.tt.projecaopopulacional.service.LogService;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("LogService")
@SpringBootTest(classes = { ProjecaopopulacionalApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LogServiceTest {

	@Autowired
	private LogService logService;

	private ProjecaoPopulacionalDTO projecao;

	@Value("${tt.api.log.path}")
	private String logPath;

	@BeforeEach
	void createLog() {
		projecao = ProjecaoPopulacionalDTO.builder() //
				.dataPopulacaoAtual(LocalDateTime.now()) //
				.dataProjecao(LocalDateTime.now()) //
				.populacaoAtual(1L) //
				.populacaoProjetada(1L) //
				.incrementoPopulacional(0L) //
				.build();
	}

	@AfterEach
	void deleteLogFile() {
		File log = new File(logPath);
		log.delete();
	}

	@Test
	@DisplayName("Must create the log file")
	void createLogString() {
		logService.registerLog(projecao);
		File log = new File(logPath);
		String register = "";

		try (ReversedLinesFileReader object = new ReversedLinesFileReader(log)) {
			register = object.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		String[] values = register.split(",");

		assertEquals(5, values.length);
		assertEquals(projecao.getDataProjecao().toString(), values[1]);
		assertEquals(projecao.getPopulacaoAtual().toString(), values[2]);
		assertEquals(projecao.getPopulacaoProjetada().toString(), values[3]);
		assertEquals(projecao.getIncrementoPopulacional().toString(), values[4]);
	}

	@Test
	@DisplayName("should return a list with the access log")
	void getAccessLog() {
		logService.registerLog(projecao);
		logService.registerLog(projecao);
		logService.registerLog(projecao);

		List<LogConsultaDTO> logs = logService.getLogAcesso();

		assertTrue(Objects.nonNull(logs));
		assertEquals(3, logs.size());
	}

	@Test
	@DisplayName("It must return a LogConsultaDTO from the log string")
	void parseStringToObject() {
		String log = projecao.getDataPopulacaoAtual() + "," + projecao.getDataProjecao() //
				+ "," + projecao.getPopulacaoAtual() + "," + projecao.getPopulacaoProjetada() //
				+ "," + projecao.getIncrementoPopulacional() + "\n";

		LogConsultaDTO logConsulta = logService.getLogObject(log);

		assertEquals(projecao.getDataProjecao(), logConsulta.getDataProjecao());
		assertEquals(projecao.getPopulacaoAtual(), logConsulta.getPopulacaoAntesProjecao());
		assertEquals(projecao.getPopulacaoProjetada(), logConsulta.getPopulacaoProjetada());
	}

	@Test
	@DisplayName("Should return an exception when no query has been performed in the api")
	void getLogException() {
		assertThrows(LogClearException.class, () -> logService.getLogAcesso());
	}

}
