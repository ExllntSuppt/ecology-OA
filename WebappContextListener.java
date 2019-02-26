package com.converter.system;

import javax.servlet.ServletContextEvent;

public class WebappContextListener implements javax.servlet.ServletContextListener {
  public WebappContextListener() {}
  
  public void contextInitialized(ServletContextEvent paramServletContextEvent) {
    WebappContext.init(paramServletContextEvent.getServletContext());
  }
  
  public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
    WebappContext.destroy(paramServletContextEvent.getServletContext());
  }
}