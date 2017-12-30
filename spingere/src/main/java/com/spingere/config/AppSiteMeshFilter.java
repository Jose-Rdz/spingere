package com.spingere.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

/**
 *
 * @author Jose-Rdz
 */
public class AppSiteMeshFilter extends ConfigurableSiteMeshFilter {

    private static final String DECORADOR_GENERAL = "/WEB-INF/jsp/decorator.jsp";
    private static final String DECORADOR_LOGINOUT = "/WEB-INF/jsp/decorator_loginout.jsp";
    
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", DECORADOR_GENERAL)
                .addDecoratorPath("/", DECORADOR_LOGINOUT)
                .addDecoratorPath("/login", DECORADOR_LOGINOUT)
                .addExcludedPath("/modal/*")
                .addExcludedPath("/error")
                .addExcludedPath("/error/*")
                .addTagRuleBundle(new DivExtractingTagRuleBundle());
    }
    
}
