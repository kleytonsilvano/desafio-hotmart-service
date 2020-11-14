package com.hotmart.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.hotmart.exceptions.ServerErrorException;

public class ClassConverter {

	public static Object copyProperties(Object dest, Object orig) {

		try {
			BeanUtils.copyProperties(dest , orig);
			return dest;
		} catch(IllegalAccessException | InvocationTargetException e) {
			throw new ServerErrorException(e);
		}
	}

	public static List<Object> copyProperties(List<Object> listDestination, List<Object> listOrigin) {

		try {

			for (Object source: listOrigin ) {
				Object target= new Object();
				BeanUtils.copyProperties(source , target);
				listDestination.add(target);

			}

			return listDestination;
			
		} catch(IllegalAccessException | InvocationTargetException e) {
			throw new ServerErrorException(e);
		}

	}

}
