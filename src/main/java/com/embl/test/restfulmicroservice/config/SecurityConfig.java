package com.embl.test.restfulmicroservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.embl.test.restfulmicroservice.security.JwtSecurityConfigurer;
import com.embl.test.restfulmicroservice.security.JwtTokenProvider;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs", 
            "/swagger-resources/**", 
            "/configuration/ui",
            "/configuration/security", 
            "/swagger-ui.html",
            "/webjars/**",
            "/h2-console/**"
    };


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
        	.headers().frameOptions().sameOrigin().and()
        	.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/token").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET, "/persons/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/persons/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/persons/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}

