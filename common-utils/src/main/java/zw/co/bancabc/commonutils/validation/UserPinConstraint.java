package zw.co.bancabc.commonutils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserPinValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPinConstraint {
    String message() default "Invalid.pin.number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}