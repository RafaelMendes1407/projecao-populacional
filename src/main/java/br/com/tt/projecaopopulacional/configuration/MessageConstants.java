package br.com.tt.projecaopopulacional.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageConstants {

	public static final String DATA_INVALIDA = "A data informada para projeção precisa ser superior a data atual.";

	public static final String LOG_NOT_FOUND = "Não existem consultas anteriores.";

	public static final String HTTP_ERROR = "Falha de comunicação com a API de projeções.";
}
