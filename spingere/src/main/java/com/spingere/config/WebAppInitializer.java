package com.spingere.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author G13380
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        rootContext.refresh();

        sc.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext dispatcherCtx = new AnnotationConfigWebApplicationContext();
        dispatcherCtx.register(DispatcherConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(dispatcherCtx);

        ServletRegistration.Dynamic dispatcher = sc.addServlet("springmvc", servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        sc.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); //JSESSIONID – in the cookie
        
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);
        
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        
        sc.addFilter("characterEncoding", characterEncodingFilter)
                .addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        
        sc.addFilter("siteMeshFilter", new AppSiteMeshFilter())
                .addMappingForUrlPatterns(null, true, "/*");
    }

}
