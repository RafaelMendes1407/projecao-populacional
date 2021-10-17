package br.com.tt.projecaopopulacional.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogConsultaDTO {

	private LocalDateTime horaAcesso;

	private LocalDateTime dataProjecao;

	private Long populacaoAntesProjecao;

	private Long populacaoProjetada;
}
