package com.gittx.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@EnableWebMvc
@Configuration
public class ExceptionHandlingConfig {

    //Catch 404
    // https://stackoverflow.com/questions/36733254/spring-boot-rest-how-to-configure-404-resource-not-found/36734193#36734193

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @PostConstruct
    private void configureDispatcherServlet() {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}