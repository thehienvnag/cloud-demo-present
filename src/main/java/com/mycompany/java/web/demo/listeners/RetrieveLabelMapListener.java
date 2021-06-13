package com.mycompany.java.web.demo.listeners;

import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author KhoaPHD
 */
@WebListener()
public class RetrieveLabelMapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ResourceBundle bundle = ResourceBundle.getBundle("labelMap");
        context.setAttribute("LABEL_MAP", bundle);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}