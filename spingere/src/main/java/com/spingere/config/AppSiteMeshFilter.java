package com.spingere.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

/**
 *
 * @author g13380
 */
public class AppSiteMeshFilter extends ConfigurableSiteMeshFilter {

    private static final String DECORADOR_GENERAL = "/WEB-INF/jsp/decorator.jsp";
    
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", DECORADOR_GENERAL)
                .addExcludedPath("/modal/*")
                .addExcludedPath("/error")
                .addExcludedPath("/error/*")
                .addTagRuleBundle(new DivExtractingTagRuleBundle());
    }
    
}
