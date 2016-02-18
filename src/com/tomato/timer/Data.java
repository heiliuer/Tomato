package com.tomato.timer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Data<T> {
	private Class<?> genericClass;

	public Data() {
		ParameterizedType type = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
		Type trueTypes[] = type.getActualTypeArguments();
		genericClass = trueTypes[0].getClass();
	}

	public final Class<?> getTypeClass() {
		return genericClass;
	}
}
