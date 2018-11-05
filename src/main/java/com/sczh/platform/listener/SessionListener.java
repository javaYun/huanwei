package com.sczh.platform.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sczh.platform.framework.utils.SessionContext;

public class SessionListener implements HttpSessionListener {

	  public  static SessionContext sessionContext=SessionContext.getInstance();
	  
	    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	    	sessionContext.AddSession(httpSessionEvent.getSession());
	    }

	    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	        sessionContext.DelSession(httpSessionEvent.getSession());
	    }

}
