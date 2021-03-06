package com.excilys.cdb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configure HttpSecurity");
        http.authorizeRequests()
                .antMatchers("/dashboard").hasAnyRole("ADMIN", "USER")
                .antMatchers("/dashboard/delete").hasAnyRole("ADMIN")
                .antMatchers("/addComputer").hasAnyRole("ADMIN")
                .antMatchers("/editComputer").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/403");

        LOGGER.debug("antMatchers : /resources/**");
        LOGGER.debug("anyRequest : authentificated");
        LOGGER.debug("formLogin : permitAll");
        LOGGER.debug("loginPage : /login");
        LOGGER.debug("defaultSucessUrl : /dashboard");
        LOGGER.debug("usernameParameter : username");
        LOGGER.debug("passwordParameter : password");
        LOGGER.debug("logout : permitAll");
        LOGGER.debug("accessDeniedPage : /errors/403");
    }

    /**
     * Encodes the password.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}