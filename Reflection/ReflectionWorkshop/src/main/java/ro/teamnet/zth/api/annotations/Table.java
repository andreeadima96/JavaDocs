package ro.teamnet.zth.api.annotations;

import com.sun.deploy.security.ValidationState;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
    String name() default"";
}
