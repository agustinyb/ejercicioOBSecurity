package com.openbootcamp.ejercicio.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebSecurityConfigurer {


    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }

    @Override
    public void configure(SecurityBuilder builder) throws Exception {
    }
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
      .withUser("user").password("password").roles("USER")
      .and()
      .withUser("admin").password("password").roles("ADMIN");
}

public HttpFirewall looseHttpFirewall(){
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowBackSlash(true);
    firewall.setAllowSemicolon(true);
    return firewall;
}

}