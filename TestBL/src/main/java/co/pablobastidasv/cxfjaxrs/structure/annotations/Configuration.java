package co.pablobastidasv.cxfjaxrs.structure.annotations;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Qualifier to specify configuration values stored in primitive types
 *
 * @author pbastidas
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER})
public @interface Configuration {
    @Nonbinding
    String value() default "";

    @Nonbinding
    String type() default "";
}
