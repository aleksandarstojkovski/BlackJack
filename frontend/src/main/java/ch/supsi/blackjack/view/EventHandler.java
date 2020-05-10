package ch.supsi.blackjack.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to declare a method as Event Handler
 * It's used to let the AbstractView invokes the proper Event handler by reflection
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
