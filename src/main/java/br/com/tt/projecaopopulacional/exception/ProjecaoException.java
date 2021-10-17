package br.com.tt.projecaopopulacional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.tt.projecaopopulacional.configuration.MessageConstants;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = MessageConstants.HTTP_ERROR)
public class ProjecaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjecaoException() {
		super(MessageConstants.HTTP_ERROR);
	}

}
