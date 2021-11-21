package ru.inurgalimov.restaurateur.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.inurgalimov.restaurateur.security.userdetails.AuthorizationUserDetailsService;

import java.util.Collections;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_ALL = {
            "/api/v1/auth/login",
            "/api/v1/auth/registration",
            "/api/v1/user/restaurants",
            "/api/v1/user/vote",
            "/api/v1/user/top-restaurants",
            "/api/v1/admin/restaurant",
            "/api/v1/admin/restaurants",
            "/api/v1/admin/menu",
            "/api/v1/admin/menu/{menuId}",
            "/api/v1/admin/restaurant/{restaurantId}"
    };

    private static final String[] IGNORE = {
            "/account-swagger/api-docs",
            "/swagger-ui.html"
    };

    private final AuthorizationUserDetailsService authorizationUserDetailsService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORE);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .addFilterAfter(preAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .authorizeRequests()
                .antMatchers(PERMIT_ALL).permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(authorizationUserDetailsService);
        authenticationProvider.setThrowExceptionWhenTokenRejected(false);

        return authenticationProvider;
    }

    @Override
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    public RequestHeaderAuthenticationFilter preAuthenticationFilter() {
        RequestHeaderAuthenticationFilter preAuthenticationFilter = new RequestHeaderAuthenticationFilter();
        preAuthenticationFilter.setPrincipalRequestHeader(AUTHORIZATION);
        preAuthenticationFilter.setAuthenticationManager(authenticationManager());
        preAuthenticationFilter.setExceptionIfHeaderMissing(false);

        return preAuthenticationFilter;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }
}