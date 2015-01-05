//package cz.fi.muni.pa165.library.web;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.Ordered;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
//* Created by sergii on 04.01.15.
//*/
//@EnableWebMvc
//@ComponentScan("org.springframework.security.samples.mvc")
//public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//
//    // ...
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
//}