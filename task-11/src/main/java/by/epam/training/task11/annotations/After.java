package by.epam.training.task11.annotations;

import java.lang.annotation.*;

@Repeatable(AfterRepeatable.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface After {
    String message();
}
