package softuni.mobilelele.domain.models.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;

public class YearInPastOrPresentValidator implements ConstraintValidator<YearInPastOrPresent, Integer> {
    private int minYear;

    @Override
    public void initialize(YearInPastOrPresent constraintAnnotation) {
        minYear = constraintAnnotation.minYear();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        int currentYear = YearMonth.now().getYear();
        return value >= minYear && value <= currentYear;
    }
}
