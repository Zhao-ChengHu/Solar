package com.sojoline.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface SpfConfig {

	boolean encryption() default false;

	boolean save() default true;

	boolean commit() default false;

	boolean preferences() default false;

	boolean global() default true;
}
