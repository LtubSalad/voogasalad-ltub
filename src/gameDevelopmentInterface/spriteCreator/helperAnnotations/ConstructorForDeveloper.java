package gameDevelopmentInterface.spriteCreator.helperAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Daniel
 * Add this annotation to a constructor if it only takes primitives or strings. 
 * This tells the developer interface to instantiate components using the annotated constructor.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface ConstructorForDeveloper {

}