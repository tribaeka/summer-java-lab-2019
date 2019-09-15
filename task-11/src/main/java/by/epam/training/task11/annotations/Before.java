package by.epam.training.task11.annotations;

import java.lang.annotation.*;

@Repeatable(BeforeRepeatable.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Before {
    String message();
}
