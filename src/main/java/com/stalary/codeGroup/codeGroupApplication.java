package com.stalary.codeGroup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stalary.codeGroup.controller.filter.ApiAuthenticatingFilter;
import com.stalary.codeGroup.controller.filter.CrossOriginFilter;
import com.stalary.codeGroup.repo.BaseRepoImpl;
import com.stalary.codeGroup.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.sql.DataSource;

/**
 * @Author:Stalary
 * @Description:
 * @Date Created in 下午6:07 17/7/25
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.stalary.codeGroup.repo", repositoryBaseClass = BaseRepoImpl.class)
public class codeGroupApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(codeGroupApplication.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        Object dataSource = ctx.getBean("dataSource");
        Object transactionManager = ctx.getBean("transactionManager");
        Object entityManagerFactory = ctx.getBean("entityManagerFactory");
        System.out.println(dataSource);
        System.out.println(entityManagerFactory);
        System.out.println(transactionManager);
        System.out.println(((JpaTransactionManager) transactionManager).getDataSource());
        System.out.println(((JpaTransactionManager) transactionManager).getEntityManagerFactory());
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new
                org.apache.tomcat.jdbc.pool.DataSource(); // org.apache.tomcat.jdbc.pool.DataSource;(driverClassName);

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(PropertyUtil.get("spring.datasource.url"));
        dataSource.setUsername(PropertyUtil.get("spring.datasource.username"));
        dataSource.setPassword(PropertyUtil.get("spring.datasource.password"));
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1 ");
        dataSource.setTimeBetweenEvictionRunsMillis(36000);

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.stalary.codeGroup.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public FilterRegistrationBean crossOriginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(crossOriginFilter());
        registration.addUrlPatterns("*");
        registration.setName("crossOriginFilter");
        registration.addInitParameter("allowOrigin", "*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    FilterRegistrationBean apiAuthenticatingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(apiAuthenticatingFilter());
        registration.addUrlPatterns("*");
        registration.setName("apiAuthenticatingFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean(name = "crossOriginFilter")
    public Filter crossOriginFilter() {
        return new CrossOriginFilter();
    }

    @Bean(name = "apiAuthenticatingFilter")
    public Filter apiAuthenticatingFilter() {
        return new ApiAuthenticatingFilter();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(codeGroupApplication.class);
    }


    @Autowired(required = true)
    public void configeJackson(ObjectMapper jackson2ObjectMapper) {
        jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        jackson2ObjectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }
}

