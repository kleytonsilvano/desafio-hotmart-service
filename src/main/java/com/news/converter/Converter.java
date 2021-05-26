package com.news.converter;

public interface Converter<T, Serializable> {
	
	public T converter(Serializable obj);

}
