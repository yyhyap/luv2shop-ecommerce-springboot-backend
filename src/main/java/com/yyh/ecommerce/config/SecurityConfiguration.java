package com.yyh.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //protect endpoint /api/orders
        http.authorizeRequests()
                // protect the endpoint
                .antMatchers("/api/orders/**")
                // only accessible for authenticated users
                .authenticated()
                .and()
                // add Resource Server support
                .oauth2ResourceServer()
                // for processing JWT bearer token
                .jwt();

        // add CORS filter
        http.cors();

        // force a non-empty response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // disable CSRF since not using cookies for authentication/session tracking
        http.csrf().disable();
    }
}
