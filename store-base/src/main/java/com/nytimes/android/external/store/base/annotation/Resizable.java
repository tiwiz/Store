package com.nytimes.android.external.store.base.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicate that an object desires to be resized when app text size changes.  The object can be a
 * TextView, an Object implementing the Resizable interface, or an Object that contains fields
 * which desire to be resized.
 * <pre><code>
 * {@literal @}Resizable(R.id.title) TextView title;
 * </code></pre>
 */
@Inherited
@Retention(RUNTIME) @Target({FIELD, TYPE})
public @interface Resizable {
}