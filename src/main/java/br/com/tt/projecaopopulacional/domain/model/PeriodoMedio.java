package br.com.tt.projecaopopulacional.domain.model;

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
public class PeriodoMedio {

	private String incrementoPopulacional;

	private String nascimento;

	private String obito;
}
