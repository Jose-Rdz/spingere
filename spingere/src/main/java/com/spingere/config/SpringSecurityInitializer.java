package com.spingere.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Register the DelegatingFilterProxy to use the springSecurityFilterChain. It avoids writing Filters configuration in web.xml file.
 * @author G13380
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
