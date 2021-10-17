package br.com.tt.projecaopopulacional.configuration.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

	public static LocalDateTime formatData(final String dataAtual, final String pattern) {
		return LocalDateTime.parse(dataAtual, DateTimeFormatter.ofPattern(pattern));
	}
}
