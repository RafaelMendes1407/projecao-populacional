package br.com.tt.projecaopopulacional.domain.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.tt.projecaopopulacional.configuration.MessageConstants;
import br.com.tt.projecaopopulacional.domain.vo.ProjecaoVO;

public class ProjecaoValidator implements ConstraintValidator<Projecao, ProjecaoVO> {

	@Override
	public boolean isValid(final ProjecaoVO value, final ConstraintValidatorContext context) {
		boolean isValid = true;

		isValid &= isDataValid(value, context);

		return isValid;
	}

	private boolean isDataValid(final ProjecaoVO value, final ConstraintValidatorContext context) {

		LocalDateTime data = LocalDateTime.parse(value.getDataProjecao(), DateTimeFormatter.ISO_DATE_TIME);

		if (data.isBefore(LocalDateTime.now())) {
			context.buildConstraintViolationWithTemplate(MessageConstants.DATA_INVALIDA) //
					.addConstraintViolation();
			return false;

		}
		return true;
	}

}
