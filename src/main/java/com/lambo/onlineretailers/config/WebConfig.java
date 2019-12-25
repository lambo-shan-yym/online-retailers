package com.lambo.onlineretailers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created With IntelliJ IDEA. Author: Lin Qili Datetime: 2019-04-16 11:34
 */

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  @Autowired
  UserArgumentResolver userArgumentResolver;

  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(userArgumentResolver);
  }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(
        "/css/**")
        .addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/webjars/**") .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler(
            "/js/**")
            .addResourceLocations("classpath:/js/")
            .addResourceLocations("classpath:/static/js/");

    registry.addResourceHandler(
            "/images/**")
            .addResourceLocations("classpath:/images/")
            .addResourceLocations("classpath:/static/images/");

    registry.addResourceHandler(
            "/picture/**")
            .addResourceLocations("classpath:/picture/")
            .addResourceLocations("classpath:/static/picture/");

    registry.addResourceHandler("/favicon.ico")//favicon.ico
            .addResourceLocations("classpath:/static/img/favicon.ico");

  }
}
