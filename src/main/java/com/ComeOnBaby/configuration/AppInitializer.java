package com.ComeOnBaby.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

//        logger.info("Definition UTF-8 Encoding (CharsetFilter())");
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("charsetFilter", new CharsetFilter());
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}

}
