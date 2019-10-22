package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validator.UserNameValidator;

@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UserNameValidator.class)
public @interface UserNameValidationAnnotation {
	String message() default "The username is not registered. Please contact admin";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
