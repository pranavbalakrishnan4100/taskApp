package com.onebox.onebox.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DefaultFilter> loggingFilter() {
        FilterRegistrationBean<DefaultFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DefaultFilter());
        registrationBean.addUrlPatterns("/api/*"); // Define the URL patterns to intercept
        return registrationBean;
    }
}

