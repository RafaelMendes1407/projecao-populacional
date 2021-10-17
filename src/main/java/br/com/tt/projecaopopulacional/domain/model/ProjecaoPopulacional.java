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
public class ProjecaoPopulacional {

	private String localidade;

	private String horario;

	private Projecao projecao;
}
