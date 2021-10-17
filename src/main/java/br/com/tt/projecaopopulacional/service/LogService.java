package br.com.tt.projecaopopulacional.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tt.projecaopopulacional.configuration.ProjecaoPopulacionalConfiguration;
import br.com.tt.projecaopopulacional.domain.dto.LogConsultaDTO;
import br.com.tt.projecaopopulacional.domain.dto.ProjecaoPopulacionalDTO;
import br.com.tt.projecaopopulacional.exception.LogClearException;

@Service
public class LogService {

	@Autowired
	private ProjecaoPopulacionalConfiguration logPath;

	public void registerLog(final ProjecaoPopulacionalDTO projecao) {

		File log = new File(logPath.getLogPath());

		try (FileOutputStream logFile = new FileOutputStream(log, true)) {
			log.createNewFile();

			String textLog = LocalDateTime.now() + "," + projecao.getDataProjecao() //
					+ "," + projecao.getPopulacaoAtual() + "," + projecao.getPopulacaoProjetada() //
					+ "," + projecao.getIncrementoPopulacional() + "\n";

			logFile.write(textLog.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<LogConsultaDTO> getLogAcesso() {
		File log = new File(logPath.getLogPath());
		int lines = 10;
		int counter = 0;

		List<LogConsultaDTO> resultadoLog = new ArrayList<>();

		try (ReversedLinesFileReader object = new ReversedLinesFileReader(log)) {

			while (counter < lines) {
				String line = object.readLine();

				if (Objects.isNull(line)) {
					break;
				}
				LogConsultaDTO logAcesso = getLogObject(line);
				resultadoLog.add(logAcesso);
				counter++;
			}
		} catch (IOException e) {
			throw new LogClearException();
		}

		return resultadoLog;
	}

	public LogConsultaDTO getLogObject(final String log) {

		String[] fields = log.split(",");

		return LogConsultaDTO.builder() //
				.horaAcesso(LocalDateTime.parse(fields[0], DateTimeFormatter.ISO_DATE_TIME)) //
				.dataProjecao(LocalDateTime.parse(fields[1], DateTimeFormatter.ISO_DATE_TIME)) //
				.populacaoAntesProjecao(Long.valueOf(fields[2])) //
				.populacaoProjetada(Long.valueOf(fields[3])) //
				.build();
	}
}
