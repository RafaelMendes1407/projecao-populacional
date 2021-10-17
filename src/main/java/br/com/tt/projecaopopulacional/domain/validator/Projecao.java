package br.com.tt.projecaopopulacional.domain.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = { ProjecaoValidator.class })
public @interface Projecao {

	String message() default "{tt.api.data.invalida}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
