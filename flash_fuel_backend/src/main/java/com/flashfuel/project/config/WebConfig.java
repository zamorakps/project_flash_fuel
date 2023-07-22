package com.flashfuel.project.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider();
    }

    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenFilter(TokenProvider tokenProvider) {
        FilterRegistrationBean<TokenAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAuthenticationFilter(tokenProvider));
        registrationBean.addUrlPatterns("/api/profile", "/api/profile/update", "/api/fuelquote/calculate", "/api/fuelquote/new", "/api/fuelquote/history", "/api/user/profile");
        return registrationBean;
    }
}