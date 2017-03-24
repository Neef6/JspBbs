package cn.edu.nju.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;



public class BeanUtilities {
	public static void populateBean(Object fromBean, HttpServletRequest request){
		populateBean(fromBean, request.getParameterMap());
	}
	
	public static void populateBean(Object bean, Map propertyMap){
		try {
			BeanUtils.populate(bean, propertyMap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
