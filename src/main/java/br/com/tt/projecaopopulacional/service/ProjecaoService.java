package br.com.tt.projecaopopulacional.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tt.projecaopopulacional.configuration.utils.DataUtils;
import br.com.tt.projecaopopulacional.domain.dto.ProjecaoPopulacionalDTO;
import br.com.tt.projecaopopulacional.domain.model.ProjecaoPopulacional;

@Slf4j
@Service
public class ProjecaoService {

	@Autowired
	private HttpService httpService;

	@Autowired
	private LogService logService;

	public ProjecaoPopulacionalDTO getProjecao(final String data) {
		ProjecaoPopulacional dadosProjecao = httpService.getInfoProjecao();

		LocalDateTime dataInicial = DataUtils.formatData(dadosProjecao.getHorario(), "dd/MM/yyyy HH:mm:ss");
		LocalDateTime dataProjecao = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);

		Long tempoInicial = dataInicial.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		Long tempoProjecao = dataProjecao.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		Long fatorAumento = Long.valueOf(dadosProjecao.getProjecao().getPeriodoMedio().getIncrementoPopulacional());
		Long populacaoInicial = dadosProjecao.getProjecao().getPopulacao();

		Long tempoDecorrido = tempoProjecao - tempoInicial;
		Long aumentoPopulacional = (long) (tempoDecorrido / (double) fatorAumento);
		Long populacaoProjetada = populacaoInicial + aumentoPopulacional;

		ProjecaoPopulacionalDTO projecao = ProjecaoPopulacionalDTO.builder() //
				.dataPopulacaoAtual(dataInicial) //
				.dataProjecao(dataProjecao) //
				.populacaoAtual(populacaoInicial) //
				.populacaoProjetada(populacaoProjetada) //
				.incrementoPopulacional(aumentoPopulacional) //
				.build();

		logService.registerLog(projecao);
		return projecao;
	}

}
