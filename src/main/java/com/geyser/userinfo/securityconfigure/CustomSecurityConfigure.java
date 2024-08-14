package com.geyser.userinfo.securityconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class CustomSecurityConfigure extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// if you not encodr you will get the IIIlegalArugement exception
		auth.inMemoryAuthentication()
		.withUser("user")
		.password("pass")																		
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();//we are used the password intext things

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF protection
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/geyser/user")
		.hasRole("ADMIN")
		.anyRequest().authenticated()
        .and()
        .httpBasic();
		//.formLogin();
	}
}
