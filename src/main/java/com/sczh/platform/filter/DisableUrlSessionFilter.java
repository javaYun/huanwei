package com.sczh.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

public class DisableUrlSessionFilter implements Filter {

	
	   @Override  
	    public void destroy() {  
	    }  
	  
	    @Override  
	    public void doFilter(ServletRequest request, ServletResponse response,  
	            FilterChain chain) throws IOException, ServletException {  
	        if (!(request instanceof HttpServletRequest)) {  
	            chain.doFilter(request, response);  
	            return;  
	        }  
	        HttpServletRequest httpRequest = (HttpServletRequest) request;  
	        HttpServletResponse httpResponse = (HttpServletResponse) response;  
	        if (httpRequest.isRequestedSessionIdFromURL()) {  
	            HttpSession session = httpRequest.getSession();  
	            if (session != null)  
	                session.invalidate();  
	        }  
	        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(  
	                httpResponse) {  
	            public String encodeRedirectUrl(String url) {  
	                return url;  
	            }  
	  
	            public String encodeRedirectURL(String url) {  
	                return url;  
	            }  
	  
	            public String encodeUrl(String url) {  
	                return url;  
	            }  
	  
	            public String encodeURL(String url) {  
	                return url;  
	            }  
	        };  
	        chain.doFilter(request, wrappedResponse);  
	    }  
	  
	    @Override  
	    public void init(FilterConfig filterConfig) throws ServletException {  
	    }  
}
