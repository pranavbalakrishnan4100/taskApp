package com.onebox.onebox.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class DefaultFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// can interecept here before controller
		
//		JSONObject inputData=new JSONObject(request.getParameterMap().get("inputData")[0]);
//		String entityName=inputData.keys().next();
//		String className=DefaultEntity.getFullClass(entityName);
//		
//		try {
//			Class<?> myClass = Class.forName(className);
//			Constructor<?> constructor = myClass.getConstructor(JSONObject.class);
//			Object instance = constructor.newInstance(inputData);
//			
//            // Do something with myInstance
//			request.
//		}catch(Exception e) {
//
//		}

		
		chain.doFilter(request, response);
	}
	
}
