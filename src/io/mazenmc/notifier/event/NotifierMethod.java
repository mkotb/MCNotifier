package io.mazenmc.notifier.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to notify the event system that a certain method is a listener one
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifierMethod {/* Empty class, annotation just to signify that its a notifier method */}
