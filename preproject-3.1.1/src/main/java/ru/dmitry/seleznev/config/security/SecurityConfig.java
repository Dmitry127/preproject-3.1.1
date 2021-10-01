package ru.dmitry.seleznev.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dmitry.seleznev.config.handler.LoginSuccessHandler;
import ru.dmitry.seleznev.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserServiceImpl userDetailsService, LoginSuccessHandler loginSuccessHandler,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.passwordEncoder = passwordEncoder;

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(loginSuccessHandler)
                .permitAll();

        http.logout()
                .permitAll()
                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/admin").access("hasRole('ADMIN')").anyRequest().authenticated();
    }

}
