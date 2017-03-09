package com.swap.common.components;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CommonBeanUtils {

	private static final Logger logger = Logger.getLogger(CommonBeanUtils.class);

	/**
	 * Ignores null values while copying bean properties from source to destination
	 * @param dest
	 * @param source
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void nullAwareBeanCopy(Object dest, Object source)
			{
		try {
			new BeanUtilsBean() {
				@Override
				public void copyProperty(Object dest, String name, Object value)
						throws IllegalAccessException, InvocationTargetException {
					if (value != null) {
						super.copyProperty(dest, name, value);
					}
				}
			}.copyProperties(dest, source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e);
		}
	}

}
