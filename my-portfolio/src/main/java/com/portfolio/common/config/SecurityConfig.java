package com.portfolio.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.portfolio.common.security.CustomSuccessHandler;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/my-portfolio", "/admin-template/**", "/web-template/**", "/sign-in",
				"/forgot-password", "/reset-password", "/image-file/**").permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		http.exceptionHandling().accessDeniedPage("/WEB-INF/views/common/403.jsp");

		http.authorizeRequests().and().formLogin().loginPage("/sign-in").loginProcessingUrl("/check-login")
				.successHandler(successHandler()).failureUrl("/sign-in?error").usernameParameter("email")
				.passwordParameter("password").and().logout().logoutSuccessUrl("/sign-in");
	}

	@Bean
	CustomSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}
}
