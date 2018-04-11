package com.sojoline.annotation;

/**
 * <pre>
 *     author : 李小勇
 *     time   : 2017/09/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SpfParser {
	Object deserialize(Class clazz, String text);

	String serialize(Object object);
}
