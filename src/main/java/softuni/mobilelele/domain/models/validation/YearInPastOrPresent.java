package softuni.mobilelele.domain.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = YearInPastOrPresentValidator.class)
public @interface YearInPastOrPresent {

    int minYear();

    String message() default "Invalid year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}