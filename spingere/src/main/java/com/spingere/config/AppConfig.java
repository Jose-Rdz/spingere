package com.spingere.config;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 *
 * @author G13380
 */
@Configuration
@PropertySource("classpath:/app.properties")
@ComponentScan(basePackages={"com.**.service", "com.**.utils"})
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="em",
        transactionManagerRef = "transactionManager",
        basePackages = "com.**.repository")
@Import(WebSecurityConfig.class)
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    
    private @Autowired Environment env;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() throws NamingException {
        logger.debug("===> creando datasource");
        try {    
            JndiObjectFactoryBean jndiFactoryBean = new JndiObjectFactoryBean();
            jndiFactoryBean.setJndiName(env.getProperty("db.jdbc.name"));
            jndiFactoryBean.setProxyInterface(DataSource.class);
            jndiFactoryBean.afterPropertiesSet();
            return (DataSource) jndiFactoryBean.getObject(); 
        } catch (RuntimeException e) {
            logger.error("No fue posible crear el datasource de la DB...", e);
            return null;
        }
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean em() throws NamingException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("spingerePU");
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPackagesToScan(env.getProperty("entitymanager.packages.to.scan"));
        em.setJpaProperties(getHibernateProperties());
        return em;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() throws NamingException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(em().getObject());
        txManager.setNestedTransactionAllowed(true);
        return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
    
    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        prop.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        prop.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        prop.put(AvailableSettings.USE_SQL_COMMENTS, env.getProperty("hibernate.use_sql_comments"));
        prop.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate5.SpringSessionContext");
        prop.put(AvailableSettings.STATEMENT_BATCH_SIZE, 20);
        prop.put("hibernate.classloading.use_current_tccl_as_parent", false);

        String hibernateCatalog = env.getProperty("hibernate.default_catalog");
        
        if (hibernateCatalog != null && !hibernateCatalog.isEmpty()) {
            prop.put(AvailableSettings.DEFAULT_CATALOG, hibernateCatalog);
        }

        String hibernateSchema = env.getProperty("hibernate.default_schema");
        
        if (hibernateSchema != null && !hibernateSchema.isEmpty()) {
            prop.put(AvailableSettings.DEFAULT_SCHEMA, hibernateSchema);
        }

        return prop;
    }

}
