package com.closememo.admin.config.security;

import com.closememo.admin.service.SecurityAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] IGNORE_WEB_SECURITY = new String[]{
      "/static/**"
  };
  private static final String[] IGNORE_AUTH_URLS = new String[]{
      "/swagger-ui/**", "/swagger-ui.html", "/health-check"
  };

  private final SecurityAccountService securityAccountService;

  public SecurityConfig(SecurityAccountService securityAccountService) {
    this.securityAccountService = securityAccountService;
  }

  @Override
  public void configure(WebSecurity web) {
    web
        .ignoring().antMatchers(IGNORE_WEB_SECURITY);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests((authorizeRequests) ->
            authorizeRequests
                .antMatchers(IGNORE_AUTH_URLS).permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
        )
        .formLogin((formLogin) ->
            formLogin
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin")
        )
        .logout((logout) ->
            logout
                .logoutUrl("/admin/logout")
        );
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityAccountService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
